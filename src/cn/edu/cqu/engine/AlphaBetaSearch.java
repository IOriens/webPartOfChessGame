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
	}

	public Rule search() {
		boolean notEnd = true;
		while (notEnd) {
			// δ����ײ� & ����������һ�� & �����<�� & ��δ���ɵ�
			if (curr < depth && cs[curr].hasRule() && cs[curr].isAlphaLessThanBeta() && !cs[curr].stop()) {
				// TODO ������Ҷ�ӽڵ��ʱ����Ҫ��genFrom����û�õ�����
				int stepCost = cs[curr + 1].genFrom(cs[curr], cs[curr].getNextRule());
				// ���´Ӹ��ڵ㵽��һ���ڵ��������
				cs[curr + 1].setCost(cs[curr].getCost() + stepCost * curr % 2 == 0 ? 1 : (-1));

				if (recommandList.size() > 0)// ��������Ƽ�������·���������õ�һ������
					cs[curr + 1].promoteRule(recommandList.remove(0));

				curr++;
			} else {
				if (curr == depth) // ��ǰ�������һ��
					cs[curr].evolution();
				else if (curr % 2 == 0) // ��ǰ����ż����
					cs[curr].updateAlpha(cs[curr + 1]);
				else // ��ǰ����������
					cs[curr].updateBeta(cs[curr + 1]);
				curr--; // ��ǰ�㴦����ϣ��ص���һ��
				if (curr == -1)// ��0�㴦����ϣ��˳�ѭ��
					notEnd = false;
			}
		}
		// TODO �������������������ô����
		return cs[0].getReasonList().getFirst();
	}
}