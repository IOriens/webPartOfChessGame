package cn.edu.cqu.db;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.cqu.engine.Move;
import cn.edu.cqu.kb.dao.ChessmanMapper;
import cn.edu.cqu.kb.model.Chessman;
import cn.edu.cqu.kb.model.ChessmanType;
import cn.edu.cqu.kb.model.JRule;
import cn.edu.cqu.kb.model.Rule;
import cn.edu.cqu.util.Mappers;
import cn.edu.cqu.util.UtilFuncs;

/**
 * @author quan 应该设计为只走机器方的规则
 */
/**
 * @author quan
 *
 */
public class Chessboard {
	@SuppressWarnings("unused")
	private static final String TAG = "Chessboard";
	private static final Logger logger = Logger.getLogger(Chessboard.class);

	public Chessman[][] chessboard = new Chessman[9][10];

	private int x, y;// 当前搜索的棋子的坐标
	private int nextRule;// 下一条规则
	private Point[] position = new Point[40];

	private ArrayList<Rule> reasonList;// 推理链
	private int alpha, beta;// α β 值:当前局面的alpha和beta值，并非全局的
	private int cost;// 从根局面到当前局面的代价
	private Rule sonRule;// 子局面使用的规则
	private Rule promoteRule;// 子局面使用的规则

	private ChessmanMapper cMapper;

	private boolean redStep;// 是否该红方走
	private boolean extendAlpha, extendBeta;// 是否是继承的

	private Point sp, ep;// 起点、终点
	private Chessman man;
	private Chessman target;

	public Chessboard() {
		alpha = Integer.MIN_VALUE;
		beta = Integer.MAX_VALUE;
		cost = 0;
		reasonList = new ArrayList<>();
		cMapper = Mappers.getChessmanMapper();
		this.redStep = true;// 红棋走先
	}

	public void initTo(int cb[][], boolean redStep) {
		reasonList.clear();

		LinkedList<Chessman> uMans = new LinkedList<>();
		this.redStep = redStep;
		for (int x = 0; x < chessboard.length; x++) {
			for (int y = 0; y < chessboard[x].length; y++) {
				if (cb[x][y] > 0) {
					Chessman man = cMapper.selectByPrimaryKey(cb[x][y]);
					if (man != null) {
						setPositionOf(man.getChessid(), new Point(x, y));
						chessboard[x][y] = man;
						uMans.add(man);
					} else {
						logger.error("初始化棋盘时，出现不存在的棋子编号" + cb[x][y]);
					}
				}
			}
		}
	}

	/**
	 * 如果son.beta比alpha大，则更新alpha和推理链;如果son.beta与alpha相等，但是son的推理链更短，则更新推理链；如果son.alpha是继承得来的，则不用于更新
	 * 
	 * @param son
	 *            子棋盘
	 */
	public void updateAlpha(Chessboard son) {
		// TODO 被继承来的alpha更新了beta
		if (son.extendBeta)
			return;
		if (son.beta != Integer.MAX_VALUE && son.beta >= alpha) {
			if (son.beta > alpha) {
				alpha = son.beta;
				updateReasonList(son.reasonList);
				extendAlpha = false;
			} else if (reasonList.size() == 0 || son.getReasonList().size() + 1 < reasonList.size()) {
				// 在更新Alpha的时候，相等的情况下，儿子的推理链比父亲的推理链短，则使用儿子的推理链（因为此步是该我方选择的步骤，我方一定会选择最短的）
				updateReasonList(son.getReasonList());
				extendAlpha = false;
			}
			// TODO 儿子的推理链和父亲的一样长怎么办
		}
	}

	/**
	 * 更新本局面的推理链：当前局面的推理链=子局面使用的规则 + 子局面的推理链
	 * 
	 * @param sonList
	 *            子局面的推理链
	 */
	private void updateReasonList(List<Rule> sonList) {
		reasonList.clear();
		if (sonRule != null)// 当为原始局面的时候
			reasonList.add(sonRule);
		reasonList.addAll(sonList);
	}

	/**
	 * 如果son.alpha比beta小，则更新beta和推理链;如果son.alpha与beta相等，但是son的推理链更长，则更新推理链;如果son.alpha是继承得来的，则不用于更新
	 * 
	 * @param son
	 *            子棋盘
	 */
	public void updateBeta(Chessboard son) {
		if (son.extendAlpha)
			return;
		if (son.alpha != Integer.MIN_VALUE && son.alpha <= beta) {
			if (son.alpha < beta) {
				beta = son.alpha;
				updateReasonList(son.reasonList);
				extendBeta = false;
			} else if (reasonList.size() == 0 || son.getReasonList().size() + 1 > reasonList.size()) {
				// 在更新Beta的时候，相等的情况下，儿子的推理链比父亲的推理链长，则使用儿子的推理链（因为此步是该敌方选择的步骤，敌方一定会选择让我们达到相同收益的最长步骤）
				updateReasonList(son.getReasonList());
				extendBeta = false;
			}
			// TODO 儿子的推理链和父亲的一样长怎么办
		}
	}

	/**
	 * @return if alpha<=beta then return true;
	 */
	public boolean isAlphaLessThanBeta() {
		return alpha <= beta;
	}

	/**
	 * 使用规则从父局面生成（机器推理时使用）：传递alpha、beta的值；更新棋盘；更新可用的规则；更新cost值；更新下一步该谁走;更新location
	 * 
	 * @param father
	 *            父局面
	 * @param rule
	 *            父局面到子局面使用的规则
	 * @return step cost
	 */
	public int genFrom(Chessboard father, Rule rule) {
		if ((rule instanceof JRule) && ((JRule) rule).getEatanother() != null) {
			sp = father.getPositionOf(rule.getChessid());
			if (redStep)// 当前局面该红走，则上一个局面是黑走
				ep = father.getPositionOf(Chessman.红将);
			else
				ep = father.getPositionOf(Chessman.黑将);
		} else {
			sp = father.getPositionOf(rule.getChessid());
			ep = new Point(sp.x + rule.getMovetoX(), sp.y + rule.getMovetoY());
		}

		return genFrom(father, sp, ep);
	}

	/**
	 * 使用规则从父局面生成（机器推理时使用）：传递alpha、beta的值；更新棋盘；更新可用的规则；更新cost值；更新下一步该谁走;更新location
	 * 
	 * @param father
	 *            父局面
	 * @param rule
	 *            父局面到子局面使用的规则
	 * @return step cost
	 */
	public int genLeafFrom(Chessboard father, Rule rule) {
		if ((rule instanceof JRule) && ((JRule) rule).getEatanother() != null) {
			sp = father.getPositionOf(rule.getChessid());
			if (redStep)// 当前局面该红走，则上一个局面是黑走
				ep = father.getPositionOf(Chessman.红将);
			else
				ep = father.getPositionOf(Chessman.黑将);
		} else {
			sp = father.getPositionOf(rule.getChessid());
			ep = new Point(sp.x + rule.getMovetoX(), sp.y + rule.getMovetoY());
		}
		Chessman man = father.chessboard[ep.x][ep.y];
		if (man != null)
			cost = man.getScore();
		else
			cost = 0;
		return cost;
	}

	public int genFrom(Chessboard father, Point sp, Point ep) {
		// 4.更新下一步该谁走
		if (father.redStep) {
			redStep = false;
		} else
			redStep = true;
		nextRule = 0;
		x = 0;
		y = 0;
		reasonList.clear();

		// 更新location
		for (int i = 0; i < position.length; i++)
			position[i] = father.position[i];

		// 1.传递alpha、beta的值
		if (father.extendAlpha == false) {
			alpha = father.alpha;
			extendAlpha = true;
		} else {
			alpha = Integer.MIN_VALUE;
			extendAlpha = false;
		}
		if (father.extendBeta == false) {
			beta = father.beta;
			extendBeta = true;
		} else {
			beta = Integer.MAX_VALUE;
			extendBeta = false;
		}
		// 2.更新棋盘和cost
		int stepCost = updateChessboard(father, sp, ep);
		return stepCost;
	}

	/**
	 * the ability of this function is like moveTo
	 * 
	 * @param father
	 * @param sp
	 *            start point
	 * @param ep
	 *            end point
	 * @return step cost
	 */
	private int updateChessboard(Chessboard father, Point sp, Point ep) {
		this.sp = sp;
		this.ep = ep;

		int stepCost = 0;

		for (int i = 0; i < chessboard.length; i++) {
			for (int j = 0; j < chessboard[i].length; j++)
				chessboard[i][j] = father.chessboard[i][j];
		}
		target = chessboard[ep.x][ep.y];
		man = chessboard[sp.x][sp.y];
		if (target != null) {
			setPositionOf(target.getChessid(), null);
			stepCost = target.getScore();
		}
		setPositionOf(man.getChessid(), ep);
		chessboard[sp.x][sp.y] = null;
		chessboard[ep.x][ep.y] = man;
		return stepCost;
	}

	/**
	 * 检查是否存在未被执行的规则
	 * 
	 * @return 将存在且存在未被执行的规则：真；将不存在或者不存在未被执行的规则：假
	 */
	public boolean hasRule() {
		if (promoteRule != null) {
			Chessman man = chessboard[getPositionOf(promoteRule.getChessid()).x][getPositionOf(promoteRule.getChessid()).y];
			if (UtilFuncs.isRuleSatisfy(this, promoteRule, man))
				return true;
		}

		for (; x < chessboard.length; x++) {
			for (; y < chessboard[0].length; y++) {
				Chessman man = chessboard[x][y];
				if (man != null) {
					if (redStep && man.getIsred()) {// 红棋 红走
						ArrayList<Rule> rules = man.getRules();
						for (; nextRule < rules.size(); nextRule++) {
							if (UtilFuncs.isRuleSatisfy(this, rules.get(nextRule), man))
								return true;
						}
						nextRule = 0;// 探测下一个棋子的第一条规则
					} else if (!redStep && !man.getIsred()) {// 黑棋 黑走
						ArrayList<Rule> rules = man.getRules();
						for (; nextRule < rules.size(); nextRule++) {
							if (UtilFuncs.isRuleSatisfy(this, rules.get(nextRule), man))
								return true;
						}
						nextRule = 0;// 探测下一个棋子的第一条规则
					}
				}
			}
			y = 0;
		}
		return false;
	}

	/**
	 * @return 如果本局面下，有一个将被干掉，则游戏到此结束了
	 */
	public boolean stop() {
		if (target != null && target.getChessType() == ChessmanType.将)
			return true;
		else
			return false;
	}

	public Rule getNextRule() {
		if (promoteRule != null) {
			sonRule = promoteRule;
			promoteRule = null;
		} else {
			Chessman mChessman = chessboard[x][y];
			ArrayList<Rule> rules = mChessman.getRules();
			sonRule = rules.get(nextRule);
			nextRule++;
		}
		return sonRule;
	}

	/**
	 * 评估顶层到最低层的代价，并设置 alpha=cost beta=cost
	 */
	public void evolution() {
		alpha = cost;
		beta = cost;
		// TODO 正确性待检验
		extendAlpha = false;
		extendBeta = false;
	}

	/**
	 * 将某条规则拿到最前面执行
	 */
	public void promoteRule(Rule rule) {
		if (position[rule.getChessid()] != null)
			promoteRule = rule;
		else
			System.err.println("正在promote一条不能使用的规则" + rule.toString() + "\n" + this);
	}

	public ArrayList<Rule> getReasonList() {
		return reasonList;
	}

	public String getReasonListString() {
		String string = "";
		for (Rule rule : reasonList)
			string += rule.toString() + " ";
		return string;
	}

	public Point getPositionOf(int chessId) {
		return position[chessId];
	}

	public Move getMove() {
		Move move = new Move();
		move.sp = sp;
		move.ep = ep;
		return move;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getAlpha() {
		return alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
		// TODO 正确性待检验
		extendAlpha = false;
	}

	public Integer getBeta() {
		return beta;
	}

	public void setBeta(Integer beta) {
		this.beta = beta;
		// TODO 正确性待检验
		extendBeta = false;
	}

	public boolean isRedStep() {
		return redStep;
	}

	public void setRedStep(boolean redStep) {
		this.redStep = redStep;
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int y = chessboard[0].length - 1; y >= 0; y--) {
			for (int x = 0; x < chessboard.length; x++) {
				Chessman man = chessboard[x][y];
				if (man == null) {
					string.append("\t,");
				} else {
					String type1 = man.getIsred() ? "紅" : "黑";
					string.append("\t" + type1 + man.getChessType().toString() + "");
				}
			}
			string.append("\n");
		}
		return string.toString();
	}

	private void setPositionOf(int chessId, Point point) {
		position[chessId] = point;
	}

	public final String getKey() {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < chessboard.length; i++) {
			for (int j = 0; j < chessboard[0].length; j++) {
				Chessman chessman = chessboard[i][j];
				if (chessman != null)
					sBuilder.append(chessman.getChessid() + " ");
				else
					sBuilder.append(" ");
			}
		}
		sBuilder.append(isRedStep());
		final String key = sBuilder.toString();
		return key;
	}

	public void setReasonList(List<Rule> reasonList2) {
		reasonList.clear();
		reasonList.addAll(reasonList2);
	}

	// TODO DEBUG
	public String getState() {
		return " alpha=" + alpha + ", beta=" + beta + ", cost=" + cost + ", extendAlpha=" + extendAlpha + ", extendBeta=" + extendBeta + ", id=" + id + ", pid=" + pid;
	}

	private int id = 0, pid;

	public int getId() {
		return id;
	}

	public int getPid() {
		return pid;
	}

	public void setId(int id2, int id3) {
		id = id2;
		pid = id3;
	}

	public final boolean isExtendAlpha() {
		return extendAlpha;
	}

	public final void setExtendAlpha(boolean extendAlpha) {
		this.extendAlpha = extendAlpha;
	}

	public final boolean isExtendBeta() {
		return extendBeta;
	}

	public final void setExtendBeta(boolean extendBeta) {
		this.extendBeta = extendBeta;
	}

}
