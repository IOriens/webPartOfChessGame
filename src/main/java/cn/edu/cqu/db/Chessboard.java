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
 * @author quan Ӧ�����Ϊֻ�߻������Ĺ���
 */
/**
 * @author quan
 *
 */
public class Chessboard {
	private static final String TAG = "Chessboard";
	private static final Logger logger = Logger.getLogger(Chessboard.class);

	public Chessman[][] chessboard = new Chessman[9][10];
	private HashMap<Integer, Point> location = new HashMap<>();// �������

	private LinkedList<Rule> redRules, blackRules;// ��ǰ���̿���ʹ�õĹ���
	private int nextRedRule = 0, nextBlackRule = 0;// ָ����һ����ִ�еĹ���

	private LinkedList<Rule> reasonList;// ������
	private Integer alpha, beta;// �� �� ֵ:��ǰ�����alpha��betaֵ������ȫ�ֵ�
	private Integer cost;// �Ӹ����浽��ǰ����Ĵ���
	private Rule sonRule;// �Ӿ���ʹ�õĹ���

	private ChessmanMapper cMapper;
	/**
	 * �Ƿ�ú췽��
	 */
	private boolean redStep;
	private boolean stop;

	private Point sp, ep;// ��㡢�յ�
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
		this.redStep = true;// ��������
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
						logger.error("��ʼ������ʱ�����ֲ����ڵ����ӱ��" + cb[x][y]);
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
	 * ���son.beta��alpha�������alpha��������
	 * 
	 * @param son
	 *            ������
	 */
	public void updateAlpha(Chessboard son) {
		if (son.beta != Integer.MAX_VALUE && son.beta > alpha) {
			alpha = son.beta;
			updateReasonList(son.reasonList);
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
	 * ���son.alpha��betaС�������beta��������
	 * 
	 * @param son
	 *            ������
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
	 * ʹ�ù���Ӹ��������ɣ���������ʱʹ�ã�������alpha��beta��ֵ���������̣����¿��õĹ��򣻸���costֵ��������һ����˭��;����location
	 * 
	 * @param father
	 *            ������
	 * @param rule
	 *            �����浽�Ӿ���ʹ�õĹ���
	 * @return step cost
	 */
	public int genFrom(Chessboard father, Rule rule) {
		// 4.������һ����˭��
		if (father.redStep) {
			redStep = false;
			nextBlackRule = 0;
		} else {
			redStep = true;
			nextRedRule = 0;
		}
		reasonList.clear();

		// ����location
		location.clear();
		location.putAll(father.location);

		// 1.����alpha��beta��ֵ
		alpha = father.alpha;
		beta = father.beta;
		// 2.�������̺�cost
		int stepCost = updateChessboard(father, rule);
		// 3.���¹��򣺷��߶������ӵĹ��� + �߶����ӵĹ���
		updateAvailableRules(father);
		return stepCost;
	}

	public int genFrom(Chessboard father, Point sp, Point ep) {
		// 4.������һ����˭��
		if (father.redStep) {
			redStep = false;
			nextBlackRule = 0;
		} else {
			redStep = true;
			nextRedRule = 0;
		}
		reasonList.clear();

		// ����location
		location.clear();
		location.putAll(father.location);

		// 1.����alpha��beta��ֵ
		alpha = father.alpha;
		beta = father.beta;
		// 2.�������̺�cost
		int stepCost = updateChessboard(father, sp, ep);
		// 3.���¹��򣺷��߶������ӵĹ��� + �߶����ӵĹ���
		updateAvailableRules(father);
		return stepCost;
	}

	private void updateAvailableRules(Chessboard father) {
		LinkedList<Chessman> uMans = getUpdateList();
		uMans.add(man);
		// �̳оɹ���
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
		// �����Ҫ���µĹ���
		for (Chessman chessman : uMans)
			if (chessman.getIsred()) {
				UtilFuncs.addRulesOf(this, chessman, redRules);
			} else {
				UtilFuncs.addRulesOf(this, chessman, blackRules);
			}
	}

	private LinkedList<Chessman> getUpdateList() {
		LinkedList<Chessman> uMans = new LinkedList<>();// ��Ҫ���µ�
		// �ᱻ�赲�������Ƴ��赲�������ӣ��������ڡ������ࡢ����������
		// ����Ĺ���
		// 1.�� ������ֱ�߾��������������ϣ����� �з���ֱ�߾��������������ϣ�����
		// 1.�� ������ֱ�߾���һ���������ϣ����� �з���ֱ�߾���һ���������ϣ�����
		for (int x = 0; x < chessboard.length; x++) {
			Chessman sman = chessboard[x][sp.y];
			Chessman eman = chessboard[x][ep.y];
			if (sman != null && (sman.getChessType() == ChessmanType.�� || sman.getChessType() == ChessmanType.��)) {
				int minDistance = sman.getChessType() == ChessmanType.�� ? 2 : 1;
				int distance = getChessDistanceInX(x, sp.x, sp.y);
				if (distance > minDistance && !uMans.contains(sman))
					uMans.add(sman);
			}
			if (eman != null && (eman.getChessType() == ChessmanType.�� || eman.getChessType() == ChessmanType.��)) {
				int minDistance = eman.getChessType() == ChessmanType.�� ? 2 : 1;
				int distance = getChessDistanceInX(x, ep.x, ep.y);
				if (distance > minDistance && !uMans.contains(eman))
					uMans.add(eman);
			}
		}
		for (int y = 0; y < chessboard[0].length; y++) {
			Chessman sman = chessboard[sp.x][y];
			Chessman eman = chessboard[ep.x][y];
			if (sman != null && (sman.getChessType() == ChessmanType.�� || sman.getChessType() == ChessmanType.��)) {
				int minDistance = sman.getChessType() == ChessmanType.�� ? 2 : 1;
				int distance = getChessDistanceInY(y, sp.y, sp.x);
				if (distance > minDistance && !uMans.contains(sman))
					uMans.add(sman);
			}
			if (eman != null && (eman.getChessType() == ChessmanType.�� || eman.getChessType() == ChessmanType.��)) {
				int minDistance = eman.getChessType() == ChessmanType.�� ? 2 : 1;
				int distance = getChessDistanceInY(y, ep.y, ep.x);
				if (distance > minDistance && !uMans.contains(eman))
					uMans.add(eman);
			}
			// ͬһ�����ϵĽ�
			if (sman != null && sman.getChessType() == ChessmanType.�� && !uMans.contains(sman))
				uMans.add(sman);
			if (eman != null && eman.getChessType() == ChessmanType.�� && !uMans.contains(eman))
				uMans.add(eman);
		}

		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				try {
					int x = sp.x + i, y = sp.y + j;
					Chessman man = chessboard[x][y];
					switch (man.getChessType()) {
					case ��:
						break;// ǰ���Ѿ��㶨��
					case ��:
						break;// ǰ���Ѿ��㶨��
					case ��:// 1.�� ����������һ���������Ҳ��ڽ����������� �з�������һ��������
						if ((i == -1 || i == -2) && j == 0 && !isEnemy(man))// ����
							addTo(uMans, man);
						break;
					case ��:// 1.�� ���������ڱ�ǰ�� �з�����Ӱ��
						if (!isEnemy(man) && i == 0 && Math.abs(j) == 1)
							addTo(uMans, man);
						break;
					case ��:// 1.�� �����������������ң��սǣ������ �з��������������ң��սǣ�
						int distance = Math.abs(i) + Math.abs(j);
						if (distance == 1)// �ս�
							addTo(uMans, man);
						if (!isEnemy(man) && distance == 3)
							addTo(uMans, man);
						break;
					case ʿ:// 1.ʿ ����������ʿб��һ������ �з�����Ӱ��
						if (!isEnemy(man) && (i * j == 1 || i * j == -1))
							addTo(uMans, man);
						break;
					case ��:// 1.�� �������������б���������� �з����������б��һ������
						if (i * j == 1 || i * j == -1)// б��һ������
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
					case ��:
						break;// ǰ���Ѿ��㶨��
					case ��:
						break;// ǰ���Ѿ��㶨��
					case ��:// 1.�� ����������һ���������Ҳ��ڽ����������� �з�������һ��������
						if ((i == -1 || i == -2) && j == 0 && !isEnemy(man))// ����
							addTo(uMans, man);
						break;
					case ��:// 1.�� ���������ڱ�ǰ�� �з�����Ӱ��
						if (!isEnemy(man) && i == 0 && Math.abs(j) == 1)
							addTo(uMans, man);
						break;
					case ��:// 1.�� �����������������ң��սǣ������ �з��������������ң��սǣ�
						int distance = Math.abs(i) + Math.abs(j);
						if (distance == 1)// �ս�
							addTo(uMans, man);
						if (!isEnemy(man) && distance == 3)
							addTo(uMans, man);
						break;
					case ʿ:// 1.ʿ ����������ʿб��һ������ �з�����Ӱ��
						if (!isEnemy(man) && (i * j == 1 || i * j == -1))
							addTo(uMans, man);
						break;
					case ��:// 1.�� �������������б���������� �з����������б��һ������
						if (i * j == 1 || i * j == -1)// б��һ������
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
			if (redStep)// ��ǰ����ú��ߣ�����һ�������Ǻ���
				ep = location.get(Chessman.�콫);
			else
				ep = location.get(Chessman.�ڽ�);
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
		// TODO reasonList��Ҫȥ����
		if ((rule instanceof JRule) && ((JRule) rule).getEatanother() != null) {
			sp = father.location.get(rule.getChessid());
			if (!father.redStep)// ��ǰ����ú��ߣ�����һ�������Ǻ���
				ep = father.location.get(Chessman.�콫);
			else
				ep = father.location.get(Chessman.�ڽ�);
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
	 * ����Ƿ����δ��ִ�еĹ���
	 * 
	 * @return �������Ҵ���δ��ִ�еĹ����棻�������ڻ��߲�����δ��ִ�еĹ��򣺼�
	 */
	public boolean hasRule() {
		if (redStep) {
			return nextRedRule < redRules.size();
		} else
			return nextBlackRule < blackRules.size();
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
	 * �������㵽��Ͳ�Ĵ��ۣ������� alpha=cost beta=cost
	 */
	public void evolution() {
		alpha = cost;
		beta = cost;
	}

	/**
	 * ��ĳ�������õ���ǰ��ִ��
	 */
	public void promoteRule(Rule rule) {
		if (redStep) {
			if (redRules.contains(rule)) {
				redRules.remove(redRules.indexOf(rule));
				redRules.add(0, rule);
			} else
				logger.error("�Ƽ����г����������в����ڵĹ���");
		} else {
			if (blackRules.contains(rule)) {
				blackRules.remove(blackRules.indexOf(rule));
				blackRules.add(0, rule);
			} else
				logger.error("�Ƽ����г����������в����ڵĹ���");
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
					String type1 = man.getIsred() ? "�t" : "��";
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
