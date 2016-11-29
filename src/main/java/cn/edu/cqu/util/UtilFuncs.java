package cn.edu.cqu.util;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.kb.model.BRule;
import cn.edu.cqu.kb.model.CRule;
import cn.edu.cqu.kb.model.Chessman;
import cn.edu.cqu.kb.model.JRule;
import cn.edu.cqu.kb.model.MRule;
import cn.edu.cqu.kb.model.PRule;
import cn.edu.cqu.kb.model.Rule;
import cn.edu.cqu.kb.model.SRule;
import cn.edu.cqu.kb.model.XRule;

public class UtilFuncs {

	/**
	 * 添加当前棋盘下某棋子的可用规则到container
	 * 
	 * @param man
	 *            某棋子
	 * @param container
	 *            可用规则列表
	 */
	public static void addRulesOf(Chessboard board, Chessman man, List<Rule> container) {
		List<Rule> rules = man.getRules();
		switch (man.getChessType()) {
		case 兵:
			for (Rule rule : rules)
				if (isRuleSatisfy(board, (BRule) rule, man))
					container.add(rule);
			break;

		case 士:
			for (Rule rule : rules)
				if (isRuleSatisfy(board, (SRule) rule, man))
					container.add(rule);
			break;

		case 将:
			for (Rule rule : rules)
				if (isRuleSatisfy(board, (JRule) rule, man))
					container.add(rule);
			break;

		case 炮:
			for (Rule rule : rules)
				if (isRuleSatisfy(board, (PRule) rule, man))
					container.add(rule);
			break;

		case 相:
			for (Rule rule : rules)
				if (isRuleSatisfy(board, (XRule) rule, man))
					container.add(rule);
			break;

		case 车:
			for (Rule rule : rules)
				if (isRuleSatisfy(board, (CRule) rule, man))
					container.add(rule);
			break;

		case 马:
			for (Rule rule : rules)
				if (isRuleSatisfy(board, (MRule) rule, man))
					container.add(rule);
			break;
		}
	}

	private static boolean isRuleSatisfy(Chessboard board, MRule rule, Chessman man) {
		Point point = board.getPositionOf(man.getChessid());
		Point targetP = new Point(point.x + rule.getMovetoX(), point.y + rule.getMovetoY());
		Point notexistP = new Point(point.x + rule.getNotexistX(), point.y + rule.getNotexistY());
		if (man.getIsred()) {
			return Functions.notExist(board, notexistP.x, notexistP.y) && Functions.notExistRed(board, targetP.x, targetP.y);
		} else {
			return Functions.notExist(board, notexistP.x, notexistP.y) && Functions.notExistBlack(board, targetP.x, targetP.y);
		}
	}

	private static boolean isRuleSatisfy(Chessboard board, CRule rule, Chessman man) {
		Point point = board.getPositionOf(man.getChessid());
		Point targetP = new Point(point.x + rule.getMovetoX(), point.y + rule.getMovetoY());
		if (rule.getNotexistinxX1() != null && rule.getExistblackX() == null && rule.getExistredX() == null) {// X方向红棋、黑棋直行
			int max = max(point.x + rule.getNotexistinxX1(), point.x + rule.getNotexistinxX2());
			int min = min(point.x + rule.getNotexistinxX1(), point.x + rule.getNotexistinxX2());
			return Functions.notExistinX(board, min, max, point.y);
		} else if (rule.getNotexistinyY1() != null && rule.getExistblackX() == null && rule.getExistredX() == null) {// Y方向直行
			int max = max(point.y + rule.getNotexistinyY1(), point.y + rule.getNotexistinyY2());
			int min = min(point.y + rule.getNotexistinyY1(), point.y + rule.getNotexistinyY2());
			return Functions.notExistinY(board, point.x, min, max);
		} else if (rule.getNotexistinxX1() != null && (rule.getExistblackX() != null || rule.getExistredX() != null)) {// X方向吃子
			int max = max(point.x + rule.getNotexistinxX1(), point.x + rule.getNotexistinxX2());
			int min = min(point.x + rule.getNotexistinxX1(), point.x + rule.getNotexistinxX2());
			if (man.getIsred())
				return Functions.notExistinX(board, min, max, point.y) && Functions.existBlack(board, targetP.x, targetP.y);
			else
				return Functions.notExistinX(board, min, max, point.y) && Functions.existRed(board, targetP.x, targetP.y);
		} else {// Y方向吃子
			int max = max(point.y + rule.getNotexistinyY1(), point.y + rule.getNotexistinyY2());
			int min = min(point.y + rule.getNotexistinyY1(), point.y + rule.getNotexistinyY2());
			if (man.getIsred()) {
				boolean value = Functions.notExistinY(board, point.x, min, max) && Functions.existBlack(board, targetP.x, targetP.y);
				return value;
			} else
				return Functions.notExistinY(board, point.x, min, max) && Functions.existRed(board, targetP.x, targetP.y);
		}
	}

	private static boolean isRuleSatisfy(Chessboard board, PRule rule, Chessman man) {
		Point point = board.getPositionOf(man.getChessid());
		Point targetP = new Point(point.x + rule.getMovetoX(), point.y + rule.getMovetoY());

		if (rule.getNotexistinxX1() != null) {// X方向直行
			int max = max(point.x + rule.getNotexistinxX1(), point.x + rule.getNotexistinxX2());
			int min = min(point.x + rule.getNotexistinxX1(), point.x + rule.getNotexistinxX2());
			return Functions.notExistinX(board, min, max, point.y);
		} else if (rule.getNotexistinyY1() != null) {// Y方向直行
			int max = max(point.y + rule.getNotexistinyY1(), point.y + rule.getNotexistinyY2());
			int min = min(point.y + rule.getNotexistinyY1(), point.y + rule.getNotexistinyY2());
			return Functions.notExistinY(board, point.x, min, max);
		} else if (rule.getExistoneinxX1() != null) {// X方向吃子
			int max = max(point.x + rule.getExistoneinxX1(), point.x + rule.getExistoneinxX2());
			int min = min(point.x + rule.getExistoneinxX1(), point.x + rule.getExistoneinxX2());
			if (man.getIsred())
				return Functions.ExistOneInX(board, min, max, point.y) && Functions.existBlack(board, targetP.x, targetP.y);
			else
				return Functions.ExistOneInX(board, min, max, point.y) && Functions.existRed(board, targetP.x, targetP.y);
		} else {// Y方向吃子
			int max = max(point.y + rule.getExistoneinyY1(), point.y + rule.getExistoneinyY2());
			int min = min(point.y + rule.getExistoneinyY1(), point.y + rule.getExistoneinyY2());
			if (man.getIsred())
				return Functions.ExistOneInY(board, point.x, min, max) && Functions.existBlack(board, targetP.x, targetP.y);
			else
				return Functions.ExistOneInY(board, point.x, min, max) && Functions.existRed(board, targetP.x, targetP.y);
		}
	}

	private static boolean isRuleSatisfy(Chessboard board, BRule rule, Chessman man) {
		Point point = board.getPositionOf(man.getChessid());
		Point targetP = new Point(point.x + rule.getMovetoX(), point.y + rule.getMovetoY());
		if (man.getIsred()) {
			if (rule.getInblackX() == null) {
				return Functions.notExistRed(board, targetP.x, targetP.y);// 直行
			} else {
				return Functions.notExistRed(board, targetP.x, targetP.y) && Functions.inBlack(point.x, point.y);// 横行
			}
		} else {
			if (rule.getInredX() == null) {
				return Functions.notExistBlack(board, targetP.x, targetP.y);// 直行
			} else {
				return Functions.notExistBlack(board, targetP.x, targetP.y) && Functions.inRed(point.x, point.y);// 横行
			}
		}
	}

	private static boolean isRuleSatisfy(Chessboard board, SRule rule, Chessman man) {
		Point point = board.getPositionOf(man.getChessid());
		Point targetP = new Point(point.x + rule.getMovetoX(), point.y + rule.getMovetoY());
		if (man.getIsred()) {
			return Functions.inRedHome(targetP.x, targetP.y) && Functions.notExistRed(board, targetP.x, targetP.y);
		} else {
			return Functions.inBlackHome(targetP.x, targetP.y) && Functions.notExistBlack(board, targetP.x, targetP.y);
		}
	}

	private static boolean isRuleSatisfy(Chessboard board, JRule rule, Chessman man) {
		Point point = board.getPositionOf(man.getChessid());
		if (man.getIsred()) {
			if (rule.getFacetoface() == null || rule.getFacetoface() == false) {
				Point targetP = new Point(point.x + rule.getMovetoX(), point.y + rule.getMovetoY());
				return Functions.inRedHome(targetP.x, targetP.y) && Functions.notExistRed(board, targetP.x, targetP.y);
			} else {
				return Functions.faceToFace(board);
			}
		} else {
			if (rule.getFacetoface() == null || rule.getFacetoface() == false) {
				Point targetP = new Point(point.x + rule.getMovetoX(), point.y + rule.getMovetoY());
				return Functions.inBlackHome(targetP.x, targetP.y) && Functions.notExistBlack(board, targetP.x, targetP.y);
			} else {
				return Functions.faceToFace(board);
			}
		}
	}

	private static boolean isRuleSatisfy(Chessboard board, XRule rule, Chessman man) {
		Point point = board.getPositionOf(man.getChessid());
		Point targetP = new Point(point.x + rule.getMovetoX(), point.y + rule.getMovetoY());
		Point notexistP = new Point(point.x + rule.getNotexistX(), point.y + rule.getNotexistY());
		if (man.getIsred()) {
			return Functions.notExist(board, notexistP.x, notexistP.y) && Functions.notExistRed(board, targetP.x, targetP.y) && Functions.inRed(targetP.x, targetP.y);
		} else {
			return Functions.notExist(board, notexistP.x, notexistP.y) && Functions.notExistBlack(board, targetP.x, targetP.y) && Functions.inBlack(targetP.x, targetP.y);
		}
	}

	private static int max(int i, int j) {
		return i > j ? i : j;
	}

	private static int min(int i, int j) {
		return i > j ? j : i;
	}

	public static void initChessboardTo(File cFile, Chessboard init) throws FileNotFoundException {
		int[][] chessboard = new int[9][10];
		Scanner sin = new Scanner(cFile);
		for (int y = chessboard[0].length - 1; y >= 0; y--) {
			String string = sin.nextLine();
			String[] strings = string.split(",");
			for (int x = 0; x < chessboard.length; x++) {
				chessboard[x][y] = Integer.valueOf(strings[x]);
			}
		}

		if (sin.hasNextLine() && sin.nextLine().equals("黑先"))
			init.initTo(chessboard, false);
		else
			init.initTo(chessboard, true);
	}
}
