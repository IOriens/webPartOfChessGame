package cn.edu.cqu.engine;

import java.util.List;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.kb.model.Rule;

/**
 * α-β搜索算法
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
			// 未到最底层 & 可以生成下一层 & 满足α<β & 将未被干掉
			if (curr < depth && cs[curr].hasRule() && cs[curr].isAlphaLessThanBeta() && !cs[curr].stop()) {
				if (recommandList.size() > 0)// 如果存在推荐的搜索路径，则设置第一个搜索
					cs[curr].promoteRule(recommandList.remove(0));
				
				curr++;
				// TODO 当到达叶子节点的时候不需要再genFrom生成没用的数据
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
				// 更新从根节点到下一个节点的总收益
				cs[curr].setCost(cs[curr - 1].getCost() + stepCost * (curr % 2 != 0 ? 1 : (-1)));
			} else {
				// TODO 有cost但是没有下一层的中间节点用来更新alpha或者beta时无效
				if (curr == leafLevel) // 当前层是叶子
					cs[curr].evolution();
				if (curr != 0) {// 不是0层，则更新上一层
					if (curr % 2 != 0) // 当前层是奇数层，上一层是偶数层
						cs[curr - 1].updateAlpha(cs[curr]);
					else // 当前层偶数层，上一层是奇数层
						cs[curr - 1].updateBeta(cs[curr]);
				} else {// 第0层处理完毕，退出循环
					notEnd = false;
				}
				curr--; // 当前层处理完毕，回到上一层
			}
		}
		if (cs[0].getReasonList().size() == 0)
			return null;
		else
			return cs[0].getReasonList().getFirst();
	}
}