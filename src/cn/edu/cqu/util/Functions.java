package cn.edu.cqu.util;

import java.awt.Point;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.kb.model.Chessman;
import cn.edu.cqu.kb.model.ChessmanType;

public class Functions {

	/*
	 * 所有函数一览 notExist(int x,int y); notExistBlack(int x,int y); notExistRed(int
	 * x,int y); in(int x,int y); notExistinX(int x1,int x2,int y);
	 * notExistinY(int x,int y1,int y2); inRedHome(int x,int y); inBlackHome(int
	 * x,int y); notFacetoFace(int x,int y,int c_x,int c_y); facetoface(int
	 * x,int y,int c_x,int c_y); inBlack(int x,int y); inRed(int x, int y);
	 * ExistOneInX(int x1,int x2,int y); ExistOneInY(int x,int y1,int y2);
	 * moveTo(int x,int y,int c_x,int c_y);
	 */

	// 当(x,y)超出棋盘或者该坐标下存在棋子时返回假，如果没有超出棋盘且不存在棋子，则返回真
	public static boolean notExist(Chessboard cb, int x, int y) {

		if (!in(x, y) || cb.chessboard[x][y] != null) {
			return false;
		} else {
			return true;
		}

	}

	// 当(x,y)超出棋盘或者该坐标下存在黑方棋子时返回假，如果没有超出棋盘且没有黑方棋子，则返回真 假定1-16为红方
	public static boolean notExistBlack(Chessboard cb, int x, int y) {
		if (in(x, y)) {
			if (cb.chessboard[x][y] == null) {
				return true;
			} else {
				if (cb.chessboard[x][y].getIsred())
					return true;
			}

		}
		return false;
	}

	public static boolean existBlack(Chessboard cb, int x, int y) {
		if (in(x, y) && cb.chessboard[x][y] != null && cb.chessboard[x][y].getIsred() == false) {
			return true;
		} else
			return false;
	}

	public static boolean existRed(Chessboard cb, int x, int y) {
		if (in(x, y) && cb.chessboard[x][y] != null && cb.chessboard[x][y].getIsred()) {
			return true;
		} else
			return false;
	}

	// 当(x,y)超出棋盘或者该坐标下存在红方棋子时返回假，如果没有超出棋盘且没有红方棋子，则返回真
	public static boolean notExistRed(Chessboard cb, int x, int y) {
		if (in(x, y)) {
			if (cb.chessboard[x][y] == null) {
				return true;
			} else {
				if (!cb.chessboard[x][y].getIsred())
					return true;
			}

		}
		return false;
	}

	// 坐标（x,y）在棋盘范围内 棋盘 9*10
	public static boolean in(int x, int y) {
		if (x < 0 || x > 8 || y < 0 || y > 9) {
			return false;
		}
		return true;
	}

	// 当在范围(x1,y)~(x2,y)之间没有棋子的时候，返回真，当(x1,y)或者(x2,y)超出棋盘或者范围内存在棋子时返回假。（x1<x2）
	public static boolean notExistinX(Chessboard cb, int x1, int x2, int y) {
		// y可以用c_y替代
		if (!in(x1, y) || !in(x2, y)) {
			return false;
		}

		if (x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}

		for (int i = x1 + 1; i < x2; i++) {
			if (!notExist(cb, i, y)) {
				return false;
			}
		}

		return true;
	}

	// 当在范围(x,y1)~(x,y2)之间没有棋子的时候，返回真，当(x,y1)或者(x,y2)超出棋盘或者范围内存在棋子时返回假。（y1<y2）
	public static boolean notExistinY(Chessboard cb, int x, int y1, int y2) {
		// x可以用c_x替代
		if (!in(x, y1) || !in(x, y2)) {
			return false;
		}

		if (y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}

		for (int i = y1 + 1; i < y2; i++) {
			if (!notExist(cb, x, i)) {
				return false;
			}
		}

		return true;
	}

	// 所下坐标在红方米字格内，则返回真,假定红方在下
	public static boolean inRedHome(int x, int y) {

		if (x <= 5 && x >= 3 && y >= 0 && y <= 3) {
			return true;
		}
		return false;
	}

	// 所下坐标在黑方米字格内，则返回真
	public static boolean inBlackHome(int x, int y) {

		if (x <= 5 && x >= 3 && y >= 7 && y <= 9) {
			return true;
		}
		return false;
	}

	// 所下坐标双方帅将没有直接见面，则返回真，红在下 (x,y)移动后，(c_x,c_y)移动前
	public static boolean notFacetoFace(Chessboard cb, Chessman man, int x, int y) {
		if (man.getIsred()) {
			for (int i = y + 1; i <= 9; i++) {
				Chessman man2 = cb.chessboard[x][i];
				if (man2 != null && !(man2.getIsred() && man2.getChessType() == ChessmanType.将)) {
					if (man2.getChessType() == ChessmanType.将) {
						return false;
					} else {
						return true;
					}
				}
			}
		} else {
			for (int i = y - 1; i >= 0; i--) {
				Chessman man2 = cb.chessboard[x][i];
				if (man2 != null && !(!man2.getIsred() && man2.getChessType() == ChessmanType.将)) {
					if (man2.getChessType() == ChessmanType.将) {
						return false;
					} else {
						return true;
					}
				}
			}
		}
		return true;
	}

	/**
	 * @return if 将 见面了，则返回真
	 */
	public static boolean faceToFace(Chessboard board) {
		Point jp1, jp2;
		jp1 = board.getPositionOf(Chessman.红将);// 红将
		jp2 = board.getPositionOf(Chessman.黑将);// 黑将
		if (jp1.x != jp2.x)
			return false;
		else {
			boolean face = true;
			for (int y = jp1.y + 1; y < jp2.y; y++) {
				if (board.chessboard[jp1.x][y] != null) {
					face = false;
					break;
				}
			}
			return face;
		}
	}

	// 是否在黑方区域，是返回true
	public static boolean inBlack(int x, int y) {
		if (y >= 5 && y <= 9 && x >= 0 && x <= 8) {
			return true;
		}

		return false;

	}

	// 是否在红方区域，是返回true
	public static boolean inRed(int x, int y) {
		if (inBlack(x, y)) {
			return false;
		}
		return true;
	}

	// (x1,y) (x2,y)之间是否只有一个棋子，是返回真
	public static boolean ExistOneInX(Chessboard cb, int x1, int x2, int y) {
		if (!in(x1, y) || !in(x2, y)) {
			return false;
		}

		if (x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		int n = 0;
		for (int i = x1; i <= x2; i++) {
			if (!notExist(cb, i, y)) {
				if (n == 0)
					n++;
				else
					return false;
			}
		}
		if (n == 1)
			return true;
		else
			return false;
	}

	// (x,y1) (x,y2)之间是否只有一个棋子，是返回真
	public static boolean ExistOneInY(Chessboard cb, int x, int y1, int y2) {
		if (!in(x, y1) || !in(x, y2)) {
			return false;
		}

		if (y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		int n = 0;
		for (int i = y1; i <= y2; i++) {
			if (!notExist(cb, x, i)) {
				if (n == 0)
					n++;
				else
					return false;
			}
		}
		if (n == 1)
			return true;
		else
			return false;
	}

	// 移动棋子 (x,y)移动后，(c_x,c_y)移动前
	public static Chessboard moveTo(Chessboard cb, int x, int y, int c_x, int c_y) {
		cb.chessboard[x][y] = cb.chessboard[c_x][c_y];
		cb.chessboard[c_x][c_y] = null;
		return cb;
	}
}
