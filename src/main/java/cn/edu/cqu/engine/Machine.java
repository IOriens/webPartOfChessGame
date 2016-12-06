package cn.edu.cqu.engine;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.db.Holder;
import cn.edu.cqu.kb.model.Rule;
import cn.edu.cqu.util.UtilFuncs;

public class Machine {
	private LinkedList<Chessboard> history;
	private int depth;
	private Holder holder;

	/**
	 * @param depth
	 *            搜索深度：可以用来控制难度
	 * @param chessboardFile
	 *            棋盘文件，棋盘文件放置在了 ./棋盘/*.txt
	 * @throws FileNotFoundException
	 */
	public Machine(int depth, File chessboardFile) throws FileNotFoundException {
		holder = Holder.getInstance(chessboardFile.getAbsolutePath());

		history = new LinkedList<>();
		Chessboard init = new Chessboard();
		history.add(init);
		this.depth = depth;
		UtilFuncs.initChessboardTo(chessboardFile, init);
	}

	/**
	 * 如果是替机器搜索，则直接返回结果；如果是替人搜索，则需要再次调用此函数，完成机器的步骤
	 * 
	 * @return 返回搜索之后做出的选择，可以从中得到是否已经分出胜负
	 */
	public Move search() {
		Chessboard father = history.getLast();
		List<Rule> recommandList = new LinkedList<>();
		if (history.size() >= 3)
			recommandList.addAll(history.get(history.size() - 1).getReasonList());

		AlphaBetaSearch abSearch = new AlphaBetaSearch(depth, father, recommandList, holder);
		Rule ruleToUse = abSearch.search();

		// Rule ruleToUse = null;
		// for (int currDepth = 2; currDepth < depth; currDepth = currDepth + 2)
		// {
		// AlphaBetaSearch abSearch = new AlphaBetaSearch(depth, father,
		// recommandList);
		// ruleToUse = abSearch.search();
		// recommandList.clear();
		// recommandList.addAll(father.getReasonList());
		// }

		// create a Move
		Move move = new Move();

		if (ruleToUse != null) {
			Chessboard son = new Chessboard();
			son.genFrom(father, ruleToUse);
			history.add(son);

			move = son.getMove();

			if (son.stop()) {
				move.setRedWin(father.isRedStep());
			}
		} else {
			move.sp = new Point(0, 0);
			move.ep = new Point(0, 0);
			move.setRedWin(!father.isRedStep());
		}

		return move;
	}

	public List<Rule> getReasonList() {
		if (history.size() >= 2) {
			return history.get(history.size() - 2).getReasonList();
		} else
			return null;
	}

	public Chessboard makeMove(Point sp, Point ep) {
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

	public void close() {
		holder.writeToFile();
	}

	public Chessboard getCurrentChessboard() {
		return history.getLast();
	}
	
	/**
	 * 帮助人完成分析
	 * 
	 * @return 返回搜索之后做出的选择，可以从中得到是否已经分出胜负
	 */
	public Move helpSearch() {
		Chessboard father = history.getLast();
		List<Rule> recommandList = new LinkedList<>();
		AlphaBetaSearch abSearch = new AlphaBetaSearch(depth, father, recommandList, holder);
		Rule ruleToUse = abSearch.search();

		// create a Move
		Move move = new Move();

		if (ruleToUse != null) {
			Chessboard son = new Chessboard();
			son.genFrom(father, ruleToUse);
			move = son.getMove();

			if (son.stop()) {
				move.setRedWin(father.isRedStep());
			}
		} else {
			move.sp = new Point(0, 0);
			move.ep = new Point(0, 0);
			move.setRedWin(!father.isRedStep());
		}
		return move;
	}
	
}
