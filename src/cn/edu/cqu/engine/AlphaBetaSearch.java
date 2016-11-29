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
	}

	public Rule search() {
		boolean notEnd = true;
		while (notEnd) {
			// 未到最底层 & 可以生成下一层 & 满足α<β & 将未被干掉
			if (curr < depth && cs[curr].hasRule() && cs[curr].isAlphaLessThanBeta() && !cs[curr].stop()) {
				// TODO 当到达叶子节点的时候不需要再genFrom生成没用的数据
				int stepCost = cs[curr + 1].genFrom(cs[curr], cs[curr].getNextRule());
				// 更新从根节点到下一个节点的总收益
				cs[curr + 1].setCost(cs[curr].getCost() + stepCost * curr % 2 == 0 ? 1 : (-1));

				if (recommandList.size() > 0)// 如果存在推荐的搜索路径，则设置第一个搜索
					cs[curr + 1].promoteRule(recommandList.remove(0));

				curr++;
			} else {
				if (curr == depth) // 当前层是最后一层
					cs[curr].evolution();
				else if (curr % 2 == 0) // 当前层是偶数层
					cs[curr].updateAlpha(cs[curr + 1]);
				else // 当前层是奇数层
					cs[curr].updateBeta(cs[curr + 1]);
				curr--; // 当前层处理完毕，回到上一层
				if (curr == -1)// 第0层处理完毕，退出循环
					notEnd = false;
			}
		}
		// TODO 如果不存在推理链，怎么处理
		return cs[0].getReasonList().getFirst();
	}
}