package cn.edu.cqu.engine;

import java.util.List;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.kb.model.Rule;

/**
 * ��-�������㷨
 * 
 */
public class AlphaBetaSearch {

	private Chessboard[] cs;
	private int depth, curr;
	private List<Rule> recommandList;

	public AlphaBetaSearch(int depth, Chessboard init, List<Rule> recommandList) {
		cs = new Chessboard[depth + 1];
		cs[0] = init;
		this.depth = depth;
		curr = 0;
		this.recommandList = recommandList;
		for (int i = 1; i <= depth; i++)
			cs[i] = new Chessboard();
		cs[0].setCost(0);
		cs[0].setAlpha(Integer.MIN_VALUE);
		cs[0].setBeta(Integer.MAX_VALUE);
	}

	public Rule search() {
		boolean notEnd = true;
		int leafLevel = depth;
		while (notEnd) {
			// δ����ײ� & ����������һ�� & �����<�� & ��δ���ɵ�
			if (curr < depth && cs[curr].hasRule() && cs[curr].isAlphaLessThanBeta() && !cs[curr].stop()) {
				if (recommandList.size() > 0)// ��������Ƽ�������·���������õ�һ������
					cs[curr].promoteRule(recommandList.remove(0));
				
				curr++;
				// TODO ������Ҷ�ӽڵ��ʱ����Ҫ��genFrom����û�õ�����
				int stepCost;
				if (curr != depth) {
					stepCost = cs[curr].genFrom(cs[curr - 1], cs[curr - 1].getNextRule());
					if (cs[curr].stop() || !cs[curr].hasRule())
						leafLevel = curr;
					else
						leafLevel = depth;
				} else {
					// stepCost = cs[curr].updateLeafChessboard(cs[curr - 1],
					// cs[curr - 1].getNextRule());
					stepCost = cs[curr].genFrom(cs[curr - 1], cs[curr - 1].getNextRule());
					leafLevel = depth;
				}
				// ���´Ӹ��ڵ㵽��һ���ڵ��������
				cs[curr].setCost(cs[curr - 1].getCost() + stepCost * (curr % 2 != 0 ? 1 : (-1)));
			} else {
				// TODO ��cost����û����һ����м�ڵ���������alpha����betaʱ��Ч
				if (curr == leafLevel) // ��ǰ����Ҷ��
					cs[curr].evolution();
				if (curr != 0) {// ����0�㣬�������һ��
					if (curr % 2 != 0) // ��ǰ���������㣬��һ����ż����
						cs[curr - 1].updateAlpha(cs[curr]);
					else // ��ǰ��ż���㣬��һ����������
						cs[curr - 1].updateBeta(cs[curr]);
				} else {// ��0�㴦����ϣ��˳�ѭ��
					notEnd = false;
				}
				curr--; // ��ǰ�㴦����ϣ��ص���һ��
			}
		}
		if (cs[0].getReasonList().size() == 0)
			return null;
		else
			return cs[0].getReasonList().getFirst();
	}
}