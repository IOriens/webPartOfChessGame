package cn.edu.cqu.engine;

import cn.edu.cqu.db.Chessboard;

/*
 * ¦Á-¦ÂËÑË÷Ëã·¨
 * 
 * */
public class absearch {

	private Chessboard[] cs;
	private int depth, curr;

	public absearch(int depth, Chessboard init) {
		cs = new Chessboard[depth + 1];
		cs[0] = init;
		this.depth = depth;
		curr = 0;

		for (int i = 1; i <= depth; i++)
			cs[i] = new Chessboard();
	}

	public void search() {
		while (cs[0].hasRule()) {
			if (curr < depth && cs[curr].hasRule() && cs[curr].isAlphaLessThanBeta()) {
				cs[curr + 1].copyFrom(cs[curr]);
				Object rule = cs[curr].getNextRule();
				cs[curr + 1].execute(rule);
				curr++;
			} else {
				cs[curr].evolution();
				if ((curr - 1) % 2 != 0)
					cs[curr - 1].updateBeta(cs[curr]);
				else
					cs[curr - 1].updateAlpha(cs[curr]);
				curr--;
			}
		}
	}
}