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
 * @author quan Ӧ�����Ϊֻ�߻������Ĺ���
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

	private int x, y;// ��ǰ���������ӵ�����
	private int nextRule;// ��һ������
	private Point[] position = new Point[40];

	private ArrayList<Rule> reasonList;// ������
	private int alpha, beta;// �� �� ֵ:��ǰ�����alpha��betaֵ������ȫ�ֵ�
	private int cost;// �Ӹ����浽��ǰ����Ĵ���
	private Rule sonRule;// �Ӿ���ʹ�õĹ���
	private Rule promoteRule;// �Ӿ���ʹ�õĹ���

	private ChessmanMapper cMapper;

	private boolean redStep;// �Ƿ�ú췽��
	private boolean extendAlpha, extendBeta;// �Ƿ��Ǽ̳е�

	private Point sp, ep;// ��㡢�յ�
	private Chessman man;
	private Chessman target;

	public Chessboard() {
		alpha = Integer.MIN_VALUE;
		beta = Integer.MAX_VALUE;
		cost = 0;
		reasonList = new ArrayList<>();
		cMapper = Mappers.getChessmanMapper();
		this.redStep = true;// ��������
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
						logger.error("��ʼ������ʱ�����ֲ����ڵ����ӱ��" + cb[x][y]);
					}
				}
			}
		}
	}

	/**
	 * ���son.beta��alpha�������alpha��������;���son.beta��alpha��ȣ�����son�����������̣�����������������son.alpha�Ǽ̳е����ģ������ڸ���
	 * 
	 * @param son
	 *            ������
	 */
	public void updateAlpha(Chessboard son) {
		// TODO ���̳�����alpha������beta
		if (son.extendBeta)
			return;
		if (son.beta != Integer.MAX_VALUE && son.beta >= alpha) {
			if (son.beta > alpha) {
				alpha = son.beta;
				updateReasonList(son.reasonList);
				extendAlpha = false;
			} else if (reasonList.size() == 0 || son.getReasonList().size() + 1 < reasonList.size()) {
				// �ڸ���Alpha��ʱ����ȵ�����£����ӵ��������ȸ��׵��������̣���ʹ�ö��ӵ�����������Ϊ�˲��Ǹ��ҷ�ѡ��Ĳ��裬�ҷ�һ����ѡ����̵ģ�
				updateReasonList(son.getReasonList());
				extendAlpha = false;
			}
			// TODO ���ӵ��������͸��׵�һ������ô��
		}
	}

	/**
	 * ���±����������������ǰ�����������=�Ӿ���ʹ�õĹ��� + �Ӿ����������
	 * 
	 * @param sonList
	 *            �Ӿ����������
	 */
	private void updateReasonList(List<Rule> sonList) {
		reasonList.clear();
		if (sonRule != null)// ��Ϊԭʼ�����ʱ��
			reasonList.add(sonRule);
		reasonList.addAll(sonList);
	}

	/**
	 * ���son.alpha��betaС�������beta��������;���son.alpha��beta��ȣ�����son�������������������������;���son.alpha�Ǽ̳е����ģ������ڸ���
	 * 
	 * @param son
	 *            ������
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
				// �ڸ���Beta��ʱ����ȵ�����£����ӵ��������ȸ��׵�������������ʹ�ö��ӵ�����������Ϊ�˲��Ǹõз�ѡ��Ĳ��裬�з�һ����ѡ�������Ǵﵽ��ͬ���������裩
				updateReasonList(son.getReasonList());
				extendBeta = false;
			}
			// TODO ���ӵ��������͸��׵�һ������ô��
		}
	}

	/**
	 * @return if alpha<=beta then return true;
	 */
	public boolean isAlphaLessThanBeta() {
		return alpha <= beta;
	}

	/**
	 * ʹ�ù���Ӹ��������ɣ���������ʱʹ�ã�������alpha��beta��ֵ���������̣����¿��õĹ��򣻸���costֵ��������һ����˭��;����location
	 * 
	 * @param father
	 *            ������
	 * @param rule
	 *            �����浽�Ӿ���ʹ�õĹ���
	 * @return step cost
	 */
	public int genFrom(Chessboard father, Rule rule) {
		if ((rule instanceof JRule) && ((JRule) rule).getEatanother() != null) {
			sp = father.getPositionOf(rule.getChessid());
			if (redStep)// ��ǰ����ú��ߣ�����һ�������Ǻ���
				ep = father.getPositionOf(Chessman.�콫);
			else
				ep = father.getPositionOf(Chessman.�ڽ�);
		} else {
			sp = father.getPositionOf(rule.getChessid());
			ep = new Point(sp.x + rule.getMovetoX(), sp.y + rule.getMovetoY());
		}

		return genFrom(father, sp, ep);
	}

	/**
	 * ʹ�ù���Ӹ��������ɣ���������ʱʹ�ã�������alpha��beta��ֵ���������̣����¿��õĹ��򣻸���costֵ��������һ����˭��;����location
	 * 
	 * @param father
	 *            ������
	 * @param rule
	 *            �����浽�Ӿ���ʹ�õĹ���
	 * @return step cost
	 */
	public int genLeafFrom(Chessboard father, Rule rule) {
		if ((rule instanceof JRule) && ((JRule) rule).getEatanother() != null) {
			sp = father.getPositionOf(rule.getChessid());
			if (redStep)// ��ǰ����ú��ߣ�����һ�������Ǻ���
				ep = father.getPositionOf(Chessman.�콫);
			else
				ep = father.getPositionOf(Chessman.�ڽ�);
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
		// 4.������һ����˭��
		if (father.redStep) {
			redStep = false;
		} else
			redStep = true;
		nextRule = 0;
		x = 0;
		y = 0;
		reasonList.clear();

		// ����location
		for (int i = 0; i < position.length; i++)
			position[i] = father.position[i];

		// 1.����alpha��beta��ֵ
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
		// 2.�������̺�cost
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
	 * ����Ƿ����δ��ִ�еĹ���
	 * 
	 * @return �������Ҵ���δ��ִ�еĹ����棻�������ڻ��߲�����δ��ִ�еĹ��򣺼�
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
					if (redStep && man.getIsred()) {// ���� ����
						ArrayList<Rule> rules = man.getRules();
						for (; nextRule < rules.size(); nextRule++) {
							if (UtilFuncs.isRuleSatisfy(this, rules.get(nextRule), man))
								return true;
						}
						nextRule = 0;// ̽����һ�����ӵĵ�һ������
					} else if (!redStep && !man.getIsred()) {// ���� ����
						ArrayList<Rule> rules = man.getRules();
						for (; nextRule < rules.size(); nextRule++) {
							if (UtilFuncs.isRuleSatisfy(this, rules.get(nextRule), man))
								return true;
						}
						nextRule = 0;// ̽����һ�����ӵĵ�һ������
					}
				}
			}
			y = 0;
		}
		return false;
	}

	/**
	 * @return ����������£���һ�������ɵ�������Ϸ���˽�����
	 */
	public boolean stop() {
		if (target != null && target.getChessType() == ChessmanType.��)
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
	 * �������㵽��Ͳ�Ĵ��ۣ������� alpha=cost beta=cost
	 */
	public void evolution() {
		alpha = cost;
		beta = cost;
		// TODO ��ȷ�Դ�����
		extendAlpha = false;
		extendBeta = false;
	}

	/**
	 * ��ĳ�������õ���ǰ��ִ��
	 */
	public void promoteRule(Rule rule) {
		if (position[rule.getChessid()] != null)
			promoteRule = rule;
		else
			System.err.println("����promoteһ������ʹ�õĹ���" + rule.toString() + "\n" + this);
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
		// TODO ��ȷ�Դ�����
		extendAlpha = false;
	}

	public Integer getBeta() {
		return beta;
	}

	public void setBeta(Integer beta) {
		this.beta = beta;
		// TODO ��ȷ�Դ�����
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
					String type1 = man.getIsred() ? "�t" : "��";
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
