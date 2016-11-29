package cn.edu.cqu.engine;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.kb.model.Rule;
import cn.edu.cqu.util.UtilFuncs;

public class Machine {
	private LinkedList<Chessboard> history;
	private int depth;
	private boolean isMachineRed;// ���û����Ƿ��Ǻ췽

	public Machine(int depth, boolean isMachineRed) throws FileNotFoundException {
		history = new LinkedList<>();
		Chessboard init = new Chessboard();
		history.add(init);
		this.depth = depth;
		this.isMachineRed = isMachineRed;

		UtilFuncs.initChessboardTo(1,init);
	}

	/**
	 * ������������������ֱ�ӷ��ؽ�����������������������Ҫ�ٴε��ô˺�������ɻ����Ĳ���
	 * 
	 * @return ��������֮��������ѡ��
	 */
	public Move search() {
		Chessboard father = history.getLast();
		AlphaBetaSearch abSearch = new AlphaBetaSearch(depth, father, history.getLast().getReasonList());
		Rule ruleToUse = abSearch.search();

		Chessboard son = new Chessboard();
		son.genFrom(father, ruleToUse);
		history.add(son);

		// create a Move
		Move move = new Move();
		Point sp = father.getPositionOf(ruleToUse.getChessid());
		Point ep = new Point(sp.x + ruleToUse.getMovetoX(), sp.y + ruleToUse.getMovetoY());
		move.sp = sp;
		move.ep = ep;
		return move;
	}

	public List<Rule> getReasonList() {
		if (history.size() >= 3) {
			return history.get(history.size() - 2).getReasonList();
		} else
			return null;
	}

	public Chessboard userMakeMove(Point sp, Point ep) {
		Chessboard chessboard = new Chessboard();
		chessboard.genFrom(history.getLast(), sp, ep);
		history.add(chessboard);
		return chessboard;
	}

	public Chessboard moveBack() {
		if (history.size() >= 3) {
			history.removeLast();
			history.removeLast();
		}
		return history.getLast();
	}

}
