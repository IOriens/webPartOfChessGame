package cn.edu.cqu.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.db.Holder;
import cn.edu.cqu.kb.model.Rule;

/**
 * α-β搜索算法： * 1.小心子节点使用继承的alpha或者beta去更新父节点，这样会造成父节点的alpha和beta一直相等 *
 * 2.子节点的alpha与父节点的beta相同时，更不更新父节点中存储的推理链，要看推理链的相对长度，若父节点更新后的推理链更短，则更新推理链，反之不更新
 * * 3.子节点的beta与父节点的alpha相同时，若父节点更新后的推理链更长，则更新推理链 *
 * 0.使用子节点的bate去更新父节点的alpha，或者alpha去更新beta，这个选择是看子节点所处的深度
 * 4.继承的东西不能往下传，不然下层的真实情况无法传上来（会导致无法更新本层的情况）
 * 
 */
public class AlphaBetaSearch {

	private Chessboard[] cs;
	private int depth, curr;
	private Holder holder;

	// TODO DEBUG
	private FileWriter writer;
	private int id = 0;
	private int level = 3;
	private int pid = 7087;

	public AlphaBetaSearch(int depth, Chessboard init, List<Rule> recommandList, Holder holder) {
		cs = new Chessboard[depth + 1];
		cs[0] = init;
		this.depth = depth;
		curr = 0;
		for (int i = 1; i <= depth; i++)
			cs[i] = new Chessboard();
		cs[0].setCost(0);
		cs[0].setAlpha(Integer.MIN_VALUE);
		cs[0].setBeta(Integer.MAX_VALUE);
		this.holder = holder;

		// TODO DEBUG
		cs[0].setId(-1, -1);
		try {
			writer = new FileWriter(new File("./logs/level" + level + "From" + pid + ".log"));
			if (pid == -1) {
				writer.write("\n根界面推理链:" + cs[0].getReasonListString());
				writer.write("\n level " + curr + cs[0].getState());
				writer.write("\n" + cs[curr].toString() + "\n\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Rule search() {
		holder.initCountInfo();
		boolean notEnd = true;
		int leafLevel = depth;

		while (notEnd) {
			// 未到最底层 & 可以生成下一层 & 满足α<β & 将未被干掉
			if (curr < depth && cs[curr].hasRule() && cs[curr].isAlphaLessThanBeta() && !cs[curr].stop()) {
				curr++;
				int stepCost;
				if (curr != depth) {
					stepCost = cs[curr].genFrom(cs[curr - 1], cs[curr - 1].getNextRule());
					if (cs[curr].stop() || !cs[curr].hasRule())
						leafLevel = curr;
					else
						leafLevel = depth;
				} else {
					stepCost = cs[curr].genLeafFrom(cs[curr - 1], cs[curr - 1].getNextRule());
					leafLevel = depth;
				}

				// 更新从根节点到下一个节点的总收益
				cs[curr].setCost(cs[curr - 1].getCost() + stepCost * (curr % 2 != 0 ? 1 : (-1)));
				// 借助以前的搜索结果
				if (curr <= 4) {
					if (holder.getRule(cs[curr], depth - curr)) {
						while (cs[curr].hasRule()) {
							cs[curr].getNextRule();
						}
					}
				}

				// TODO DEBUG
				id++;
				cs[curr].setId(id, cs[curr - 1].getId());
				if (cs[curr].getId() == pid)
					try {
						writer.write("\n当前界面推理链:" + cs[curr].getReasonListString());
						writer.write("\n level " + curr + cs[curr].getState());
						writer.write("\n" + cs[curr].toString() + "\n\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else {
				if (curr == leafLevel) // 当前层是叶子
					cs[curr].evolution();
				else if (curr <= 4)
					holder.addSituation(cs[curr], depth - curr);
				if (curr != 0) {// 不是0层，则更新上一层
					if (curr % 2 != 0) { // 当前层是奇数层，上一层是偶数层
						int before = cs[curr - 1].getAlpha();
						//curr节点不是非法节点才可以更新，如果是非法节点，选择了之后情况只能更坏
						if (cs[curr].isAlphaLessThanBeta())
						cs[curr - 1].updateAlpha(cs[curr]);
						int after = cs[curr - 1].getAlpha();

						// TODO DEBUG
						if (curr == level && cs[curr].getPid() == pid) {
							try {
								writer.write(before == after ? "\n更新失败" : "\n更新成功");
								writer.write("\n当前界面推理链:" + cs[curr].getReasonListString());
								writer.write("\n level " + curr + cs[curr].getState());
								writer.write("\n父界面推理链:" + cs[curr - 1].getReasonListString());
								writer.write("\n level " + curr + cs[curr - 1].getState());
								writer.write("\n" + cs[curr].toString() + "\n\n");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} else { // 当前层偶数层，上一层是奇数层
						int before = cs[curr - 1].getBeta();
						//curr节点不是非法节点才可以更新，如果是非法节点，选择了之后情况只能更坏
						if (cs[curr].isAlphaLessThanBeta())
							cs[curr - 1].updateBeta(cs[curr]);
						int after = cs[curr - 1].getBeta();

						// TODO DEBUG
						if (curr == level && cs[curr].getPid() == pid) {
							try {
								writer.write(before == after ? "\n更新失败" : "\n更新成功");
								writer.write("\n当前界面推理链:" + cs[curr].getReasonListString());
								writer.write("\n level " + curr + cs[curr].getState());
								writer.write("\n父界面推理链:" + cs[curr - 1].getReasonListString());
								writer.write("\n level " + curr + cs[curr - 1].getState());
								writer.write("\n" + cs[curr].toString() + "\n\n");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} else {// 第0层处理完毕，退出循环
					notEnd = false;
				}
				curr--; // 当前层处理完毕，回到上一层
			}
		}

		// TODO DEBUG
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		holder.printCountInfo();

		if (cs[0].getReasonList().size() == 0)
			return null;
		else
			return cs[0].getReasonList().get(0);
	}
}