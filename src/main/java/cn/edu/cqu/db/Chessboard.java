package cn.edu.cqu.db;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import cn.edu.cqu.engine.Move;
import cn.edu.cqu.kb.dao.ChessmanMapper;
import cn.edu.cqu.kb.model.Chessman;
import cn.edu.cqu.kb.model.ChessmanExample;
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
	private static final String TAG = "Chessboard";
	private static final Logger logger = Logger.getLogger(Chessboard.class);

	public Chessman[][] chessboard = new Chessman[9][10];
	private HashMap<Integer, Point> location = new HashMap<>();// 点的坐标

	private LinkedList<Rule> redRules, blackRules;// 当前棋盘可以使用的规则
	private int nextRedRule = 0, nextBlackRule = 0;// 指向下一条待执行的规则

	private LinkedList<Rule> reasonList;// 推理链
	private Integer alpha, beta;// α β 值:当前局面的alpha和beta值，并非全局的
	private Integer cost;// 从根局面到当前局面的代价
	private Rule sonRule;// 子局面使用的规则

	private ChessmanMapper cMapper;
	/**
	 * 是否该红方走
	 */
	private boolean redStep;
	private boolean stop;

	private Point sp, ep;// 起点、终点
	private Chessman man;
	private Chessman target;

	public Chessboard() {
		alpha = Integer.MIN_VALUE;
		beta = Integer.MAX_VALUE;
		cost = 0;
		redRules = new LinkedList<>();
		blackRules = new LinkedList<>();
		reasonList = new LinkedList<>();
		cMapper = Mappers.getChessmanMapper();
		this.redStep = true;// 红棋走先
	}

	public void initTo(int cb[][], boolean redStep) {
		redRules.clear();
		blackRules.clear();
		reasonList.clear();
		location.clear();

		LinkedList<Chessman> uMans = new LinkedList<>();
		this.redStep = redStep;
		for (int x = 0; x < chessboard.length; x++) {
			for (int y = 0; y < chessboard[x].length; y++) {
				if (cb[x][y] > 0) {
					Chessman man = cMapper.selectByPrimaryKey(cb[x][y]);
					if (man != null) {
						location.put(man.getChessid(), new Point(x, y));
						chessboard[x][y] = man;
						uMans.add(man);
					} else {
						logger.error("初始化棋盘时，出现不存在的棋子编号" + cb[x][y]);
					}
				}
			}
		}
		for (Chessman man : uMans) {
			if (man.getIsred())
				UtilFuncs.addRulesOf(this, man, redRules);
			else
				UtilFuncs.addRulesOf(this, man, blackRules);
		}
	}

	/**
	 * 如果son.beta比alpha大，则更新alpha和推理链
	 * 
	 * @param son
	 *            子棋盘
	 */
	public void updateAlpha(Chessboard son) {
		if (son.beta != Integer.MAX_VALUE && son.beta > alpha) {
			alpha = son.beta;
			updateReasonList(son.reasonList);
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
	 * 如果son.alpha比beta小，则更新beta和推理链
	 * 
	 * @param son
	 *            子棋盘
	 */
	public void updateBeta(Chessboard son) {
		if (son.alpha != Integer.MIN_VALUE && son.alpha < beta) {
			beta = son.alpha;
			updateReasonList(son.reasonList);
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
		// 4.更新下一步该谁走
		if (father.redStep) {
			redStep = false;
			nextBlackRule = 0;
		} else {
			redStep = true;
			nextRedRule = 0;
		}
		reasonList.clear();

		// 更新location
		location.clear();
		location.putAll(father.location);

		// 1.传递alpha、beta的值
		alpha = father.alpha;
		beta = father.beta;
		// 2.更新棋盘和cost
		int stepCost = updateChessboard(father, rule);
		// 3.更新规则：非走动的棋子的规则 + 走动棋子的规则
		updateAvailableRules(father);
		return stepCost;
	}

	public int genFrom(Chessboard father, Point sp, Point ep) {
		// 4.更新下一步该谁走
		if (father.redStep) {
			redStep = false;
			nextBlackRule = 0;
		} else {
			redStep = true;
			nextRedRule = 0;
		}
		reasonList.clear();

		// 更新location
		location.clear();
		location.putAll(father.location);

		// 1.传递alpha、beta的值
		alpha = father.alpha;
		beta = father.beta;
		// 2.更新棋盘和cost
		int stepCost = updateChessboard(father, sp, ep);
		// 3.更新规则：非走动的棋子的规则 + 走动棋子的规则
		updateAvailableRules(father);
		return stepCost;
	}

	private void updateAvailableRules(Chessboard father) {
		LinkedList<Chessman> uMans = getUpdateList();
		uMans.add(man);
		// 继承旧规则
		redRules.clear();
		for (Rule oldRule : father.redRules) {
			boolean add = true;
			for (Chessman uman : uMans) {
				if (oldRule.getChessid() == uman.getChessid()) {
					add = false;
					break;
				}
			}
			if (target != null && oldRule.getChessid() == target.getChessid())
				add = false;
			if (add == true)
				redRules.add(oldRule);
		}

		blackRules.clear();
		for (Rule oldRule : father.blackRules) {
			boolean add = true;
			for (Chessman uman : uMans) {
				if (oldRule.getChessid() == uman.getChessid()) {
					add = false;
					break;
				}
			}
			if (target != null && oldRule.getChessid() == target.getChessid())
				add = false;
			if (add == true)
				blackRules.add(oldRule);
		}
		// 添加需要更新的规则
		for (Chessman chessman : uMans)
			if (chessman.getIsred()) {
				UtilFuncs.addRulesOf(this, chessman, redRules);
			} else {
				UtilFuncs.addRulesOf(this, chessman, blackRules);
			}
	}

	private LinkedList<Chessman> getUpdateList() {
		LinkedList<Chessman> uMans = new LinkedList<>();// 需要更新的
		// 会被阻挡（或者移除阻挡）的棋子：车、马、炮、将、相、己方的棋子
		// 规则的规则：
		// 1.炮 己方：直线距离两个棋子以上，不变 敌方：直线距离两个棋子以上，不变
		// 1.车 己方：直线距离一个棋子以上，不变 敌方：直线距离一个棋子以上，不变
		for (int x = 0; x < chessboard.length; x++) {
			Chessman sman = chessboard[x][sp.y];
			Chessman eman = chessboard[x][ep.y];
			if (sman != null && (sman.getChessType() == ChessmanType.炮 || sman.getChessType() == ChessmanType.车)) {
				int minDistance = sman.getChessType() == ChessmanType.炮 ? 2 : 1;
				int distance = getChessDistanceInX(x, sp.x, sp.y);
				if (distance > minDistance && !uMans.contains(sman))
					uMans.add(sman);
			}
			if (eman != null && (eman.getChessType() == ChessmanType.炮 || eman.getChessType() == ChessmanType.车)) {
				int minDistance = eman.getChessType() == ChessmanType.炮 ? 2 : 1;
				int distance = getChessDistanceInX(x, ep.x, ep.y);
				if (distance > minDistance && !uMans.contains(eman))
					uMans.add(eman);
			}
		}
		for (int y = 0; y < chessboard[0].length; y++) {
			Chessman sman = chessboard[sp.x][y];
			Chessman eman = chessboard[ep.x][y];
			if (sman != null && (sman.getChessType() == ChessmanType.炮 || sman.getChessType() == ChessmanType.车)) {
				int minDistance = sman.getChessType() == ChessmanType.炮 ? 2 : 1;
				int distance = getChessDistanceInY(y, sp.y, sp.x);
				if (distance > minDistance && !uMans.contains(sman))
					uMans.add(sman);
			}
			if (eman != null && (eman.getChessType() == ChessmanType.炮 || eman.getChessType() == ChessmanType.车)) {
				int minDistance = eman.getChessType() == ChessmanType.炮 ? 2 : 1;
				int distance = getChessDistanceInY(y, ep.y, ep.x);
				if (distance > minDistance && !uMans.contains(eman))
					uMans.add(eman);
			}
			// 同一竖线上的将
			if (sman != null && sman.getChessType() == ChessmanType.将 && !uMans.contains(sman))
				uMans.add(sman);
			if (eman != null && eman.getChessType() == ChessmanType.将 && !uMans.contains(eman))
				uMans.add(eman);
		}

		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				try {
					int x = sp.x + i, y = sp.y + j;
					Chessman man = chessboard[x][y];
					switch (man.getChessType()) {
					case 炮:
						break;// 前面已经搞定了
					case 车:
						break;// 前面已经搞定了
					case 将:// 1.将 己方：不在一条竖线上且不在将的上下左右 敌方：不在一条竖线上
						if ((i == -1 || i == -2) && j == 0 && !isEnemy(man))// 左右
							addTo(uMans, man);
						break;
					case 兵:// 1.兵 己方：不在兵前方 敌方：不影响
						if (!isEnemy(man) && i == 0 && Math.abs(j) == 1)
							addTo(uMans, man);
						break;
					case 马:// 1.马 己方：不在上下左右（拐角）和马口 敌方：不在上下左右（拐角）
						int distance = Math.abs(i) + Math.abs(j);
						if (distance == 1)// 拐角
							addTo(uMans, man);
						if (!isEnemy(man) && distance == 3)
							addTo(uMans, man);
						break;
					case 士:// 1.士 己方：不在士斜角一个点内 敌方：不影响
						if (!isEnemy(man) && (i * j == 1 || i * j == -1))
							addTo(uMans, man);
						break;
					case 相:// 1.相 己方：不在相的斜角两个点内 敌方：不在相的斜角一个点内
						if (i * j == 1 || i * j == -1)// 斜角一个点内
							addTo(uMans, man);
						if (!isEnemy(man) && (i * j == 4 || i * j == -4))
							addTo(uMans, man);
						break;
					}
				} catch (Exception e) {
				}
				try {
					int x = ep.x + i, y = ep.y + j;
					Chessman man = chessboard[x][y];
					switch (man.getChessType()) {
					case 炮:
						break;// 前面已经搞定了
					case 车:
						break;// 前面已经搞定了
					case 将:// 1.将 己方：不在一条竖线上且不在将的上下左右 敌方：不在一条竖线上
						if ((i == -1 || i == -2) && j == 0 && !isEnemy(man))// 左右
							addTo(uMans, man);
						break;
					case 兵:// 1.兵 己方：不在兵前方 敌方：不影响
						if (!isEnemy(man) && i == 0 && Math.abs(j) == 1)
							addTo(uMans, man);
						break;
					case 马:// 1.马 己方：不在上下左右（拐角）和马口 敌方：不在上下左右（拐角）
						int distance = Math.abs(i) + Math.abs(j);
						if (distance == 1)// 拐角
							addTo(uMans, man);
						if (!isEnemy(man) && distance == 3)
							addTo(uMans, man);
						break;
					case 士:// 1.士 己方：不在士斜角一个点内 敌方：不影响
						if (!isEnemy(man) && (i * j == 1 || i * j == -1))
							addTo(uMans, man);
						break;
					case 相:// 1.相 己方：不在相的斜角两个点内 敌方：不在相的斜角一个点内
						if (i * j == 1 || i * j == -1)// 斜角一个点内
							addTo(uMans, man);
						if (!isEnemy(man) && (i * j == 4 || i * j == -4))
							addTo(uMans, man);
						break;
					}
				} catch (Exception e) {
				}
			}
		}
		return uMans;
	}

	private void addTo(List<Chessman> list, Chessman man) {
		if (!list.contains(man))
			list.add(man);
	}

	private boolean isEnemy(Chessman man) {
		if ((man.getIsred() & !redStep) || (!man.getIsred() & redStep))
			return true;
		else
			return false;
	}

	private int updateChessboard(Chessboard father, Rule rule) {
		if ((rule instanceof JRule) && ((JRule) rule).getEatanother() != null) {
			sp = location.get(rule.getChessid());
			if (redStep)// 当前局面该红走，则上一个局面是黑走
				ep = location.get(Chessman.红将);
			else
				ep = location.get(Chessman.黑将);
		} else {
			sp = location.get(rule.getChessid());
			ep = new Point(sp.x + rule.getMovetoX(), sp.y + rule.getMovetoY());
		}
		return updateChessboard(father, sp, ep);
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
			location.remove(target.getChessid());
			stepCost = target.getScore();
		}
		location.put(man.getChessid(), ep);
		chessboard[sp.x][sp.y] = null;
		chessboard[ep.x][ep.y] = man;
		return stepCost;
	}

	public int updateLeafChessboard(Chessboard father, Rule rule) {
		// TODO reasonList需要去更新
		if ((rule instanceof JRule) && ((JRule) rule).getEatanother() != null) {
			sp = father.location.get(rule.getChessid());
			if (!father.redStep)// 当前局面该红走，则上一个局面是黑走
				ep = father.location.get(Chessman.红将);
			else
				ep = father.location.get(Chessman.黑将);
		} else {
			sp = father.location.get(rule.getChessid());
			ep = new Point(sp.x + rule.getMovetoX(), sp.y + rule.getMovetoY());
		}
		target = father.chessboard[ep.x][ep.y];
		man = father.chessboard[sp.x][sp.y];

		if (target == null)
			return 0;
		else
			return target.getScore();
	}

	private int getChessDistanceInX(int x1, int x2, int y) {
		int startX = x1 > x2 ? x2 : x1;
		int endX = x1 > x2 ? x1 : x2;
		int count = 0;
		for (int j = startX + 1; j < endX; j++)
			if (chessboard[j][y] != null)
				count++;
		return count;
	}

	private int getChessDistanceInY(int y1, int y2, int x) {
		int startY = y1 > y2 ? y2 : y1;
		int endY = y1 > y2 ? y1 : y2;
		int count = 0;
		for (int j = startY + 1; j < endY; j++)
			if (chessboard[x][j] != null)
				count++;
		return count;
	}

	/**
	 * 检查是否存在未被执行的规则
	 * 
	 * @return 将存在且存在未被执行的规则：真；将不存在或者不存在未被执行的规则：假
	 */
	public boolean hasRule() {
		if (redStep) {
			return nextRedRule < redRules.size();
		} else
			return nextBlackRule < blackRules.size();
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
		if (redStep) {
			sonRule = redRules.get(nextRedRule);
			nextRedRule++;
			return sonRule;
		} else {
			sonRule = blackRules.get(nextBlackRule);
			nextBlackRule++;
			return sonRule;
		}
	}

	/**
	 * 评估顶层到最低层的代价，并设置 alpha=cost beta=cost
	 */
	public void evolution() {
		alpha = cost;
		beta = cost;
	}

	/**
	 * 将某条规则拿到最前面执行
	 */
	public void promoteRule(Rule rule) {
		if (redStep) {
			if (redRules.contains(rule)) {
				redRules.remove(redRules.indexOf(rule));
				redRules.add(0, rule);
			} else
				logger.error("推荐链中出现推理链中不存在的规则");
		} else {
			if (blackRules.contains(rule)) {
				blackRules.remove(blackRules.indexOf(rule));
				blackRules.add(0, rule);
			} else
				logger.error("推荐链中出现推理链中不存在的规则");
		}
	}

	public LinkedList<Rule> getReasonList() {
		return reasonList;
	}

	public Point getPositionOf(int chessId) {
		if (location.containsKey(chessId)) {
			return location.get(chessId);
		} else
			return null;
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
	}

	public Integer getBeta() {
		return beta;
	}

	public void setBeta(Integer beta) {
		this.beta = beta;
	}

	public int getNextRedRule() {
		return nextRedRule;
	}

	public void setNextRedRule(int nextRedRule) {
		this.nextRedRule = nextRedRule;
	}

	public int getNextBlackRule() {
		return nextBlackRule;
	}

	public void setNextBlackRule(int nextBlackRule) {
		this.nextBlackRule = nextBlackRule;
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
					// string.append("\t" +  man.getChessid() + "");
				}
			}
			string.append("\n");
		}
		return string.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	public String toJSON() {
		
		JSONArray chess2DBoard = new JSONArray();	
		
		for (int y = chessboard[0].length - 1; y >= 0; y--) {
			JSONArray childJsonArray = new JSONArray();
			for (int x = 0; x < chessboard.length; x++) {
				Chessman man = chessboard[x][y];
				if(man == null) {
					childJsonArray.add(0);
				} else {
					childJsonArray.add(man.getChessid());
				}				 				
			}
			chess2DBoard.add(childJsonArray);
		}
		return chess2DBoard.toJSONString();
	}
}
