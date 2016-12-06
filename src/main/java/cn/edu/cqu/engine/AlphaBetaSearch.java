package cn.edu.cqu.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.db.Holder;
import cn.edu.cqu.kb.model.Rule;

/**
 * ��-�������㷨�� * 1.С���ӽڵ�ʹ�ü̳е�alpha����betaȥ���¸��ڵ㣬��������ɸ��ڵ��alpha��betaһֱ��� *
 * 2.�ӽڵ��alpha�븸�ڵ��beta��ͬʱ���������¸��ڵ��д洢����������Ҫ������������Գ��ȣ������ڵ���º�����������̣����������������֮������
 * * 3.�ӽڵ��beta�븸�ڵ��alpha��ͬʱ�������ڵ���º������������������������� *
 * 0.ʹ���ӽڵ��bateȥ���¸��ڵ��alpha������alphaȥ����beta�����ѡ���ǿ��ӽڵ����������
 * 4.�̳еĶ����������´�����Ȼ�²����ʵ����޷����������ᵼ���޷����±���������
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
				writer.write("\n������������:" + cs[0].getReasonListString());
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
			// δ����ײ� & ����������һ�� & �����<�� & ��δ���ɵ�
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

				// ���´Ӹ��ڵ㵽��һ���ڵ��������
				cs[curr].setCost(cs[curr - 1].getCost() + stepCost * (curr % 2 != 0 ? 1 : (-1)));
				// ������ǰ���������
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
						writer.write("\n��ǰ����������:" + cs[curr].getReasonListString());
						writer.write("\n level " + curr + cs[curr].getState());
						writer.write("\n" + cs[curr].toString() + "\n\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else {
				if (curr == leafLevel) // ��ǰ����Ҷ��
					cs[curr].evolution();
				else if (curr <= 4)
					holder.addSituation(cs[curr], depth - curr);
				if (curr != 0) {// ����0�㣬�������һ��
					if (curr % 2 != 0) { // ��ǰ���������㣬��һ����ż����
						int before = cs[curr - 1].getAlpha();
						//curr�ڵ㲻�ǷǷ��ڵ�ſ��Ը��£�����ǷǷ��ڵ㣬ѡ����֮�����ֻ�ܸ���
						if (cs[curr].isAlphaLessThanBeta())
						cs[curr - 1].updateAlpha(cs[curr]);
						int after = cs[curr - 1].getAlpha();

						// TODO DEBUG
						if (curr == level && cs[curr].getPid() == pid) {
							try {
								writer.write(before == after ? "\n����ʧ��" : "\n���³ɹ�");
								writer.write("\n��ǰ����������:" + cs[curr].getReasonListString());
								writer.write("\n level " + curr + cs[curr].getState());
								writer.write("\n������������:" + cs[curr - 1].getReasonListString());
								writer.write("\n level " + curr + cs[curr - 1].getState());
								writer.write("\n" + cs[curr].toString() + "\n\n");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} else { // ��ǰ��ż���㣬��һ����������
						int before = cs[curr - 1].getBeta();
						//curr�ڵ㲻�ǷǷ��ڵ�ſ��Ը��£�����ǷǷ��ڵ㣬ѡ����֮�����ֻ�ܸ���
						if (cs[curr].isAlphaLessThanBeta())
							cs[curr - 1].updateBeta(cs[curr]);
						int after = cs[curr - 1].getBeta();

						// TODO DEBUG
						if (curr == level && cs[curr].getPid() == pid) {
							try {
								writer.write(before == after ? "\n����ʧ��" : "\n���³ɹ�");
								writer.write("\n��ǰ����������:" + cs[curr].getReasonListString());
								writer.write("\n level " + curr + cs[curr].getState());
								writer.write("\n������������:" + cs[curr - 1].getReasonListString());
								writer.write("\n level " + curr + cs[curr - 1].getState());
								writer.write("\n" + cs[curr].toString() + "\n\n");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} else {// ��0�㴦����ϣ��˳�ѭ��
					notEnd = false;
				}
				curr--; // ��ǰ�㴦����ϣ��ص���һ��
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