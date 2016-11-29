package cn.edu.cqu.util;

import java.awt.Point;

import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.kb.model.Chessman;
import cn.edu.cqu.kb.model.ChessmanType;

public class Functions {

	/*
	 * ���к���һ�� notExist(int x,int y); notExistBlack(int x,int y); notExistRed(int
	 * x,int y); in(int x,int y); notExistinX(int x1,int x2,int y);
	 * notExistinY(int x,int y1,int y2); inRedHome(int x,int y); inBlackHome(int
	 * x,int y); notFacetoFace(int x,int y,int c_x,int c_y); facetoface(int
	 * x,int y,int c_x,int c_y); inBlack(int x,int y); inRed(int x, int y);
	 * ExistOneInX(int x1,int x2,int y); ExistOneInY(int x,int y1,int y2);
	 * moveTo(int x,int y,int c_x,int c_y);
	 */

	// ��(x,y)�������̻��߸������´�������ʱ���ؼ٣����û�г��������Ҳ��������ӣ��򷵻���
	public static boolean notExist(Chessboard cb, int x, int y) {

		if (!in(x, y) || cb.chessboard[x][y] != null) {
			return false;
		} else {
			return true;
		}

	}

	// ��(x,y)�������̻��߸������´��ںڷ�����ʱ���ؼ٣����û�г���������û�кڷ����ӣ��򷵻��� �ٶ�1-16Ϊ�췽
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

	// ��(x,y)�������̻��߸������´��ں췽����ʱ���ؼ٣����û�г���������û�к췽���ӣ��򷵻���
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

	// ���꣨x,y�������̷�Χ�� ���� 9*10
	public static boolean in(int x, int y) {
		if (x < 0 || x > 8 || y < 0 || y > 9) {
			return false;
		}
		return true;
	}

	// ���ڷ�Χ(x1,y)~(x2,y)֮��û�����ӵ�ʱ�򣬷����棬��(x1,y)����(x2,y)�������̻��߷�Χ�ڴ�������ʱ���ؼ١���x1<x2��
	public static boolean notExistinX(Chessboard cb, int x1, int x2, int y) {
		// y������c_y���
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

	// ���ڷ�Χ(x,y1)~(x,y2)֮��û�����ӵ�ʱ�򣬷����棬��(x,y1)����(x,y2)�������̻��߷�Χ�ڴ�������ʱ���ؼ١���y1<y2��
	public static boolean notExistinY(Chessboard cb, int x, int y1, int y2) {
		// x������c_x���
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

	// ���������ں췽���ָ��ڣ��򷵻���,�ٶ��췽����
	public static boolean inRedHome(int x, int y) {

		if (x <= 5 && x >= 3 && y >= 0 && y <= 3) {
			return true;
		}
		return false;
	}

	// ���������ںڷ����ָ��ڣ��򷵻���
	public static boolean inBlackHome(int x, int y) {

		if (x <= 5 && x >= 3 && y >= 7 && y <= 9) {
			return true;
		}
		return false;
	}

	// ��������˫��˧��û��ֱ�Ӽ��棬�򷵻��棬������ (x,y)�ƶ���(c_x,c_y)�ƶ�ǰ
	public static boolean notFacetoFace(Chessboard cb, Chessman man, int x, int y) {
		if (man.getIsred()) {
			for (int i = y + 1; i <= 9; i++) {
				Chessman man2 = cb.chessboard[x][i];
				if (man2 != null && !(man2.getIsred() && man2.getChessType() == ChessmanType.��)) {
					if (man2.getChessType() == ChessmanType.��) {
						return false;
					} else {
						return true;
					}
				}
			}
		} else {
			for (int i = y - 1; i >= 0; i--) {
				Chessman man2 = cb.chessboard[x][i];
				if (man2 != null && !(!man2.getIsred() && man2.getChessType() == ChessmanType.��)) {
					if (man2.getChessType() == ChessmanType.��) {
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
	 * @return if �� �����ˣ��򷵻���
	 */
	public static boolean faceToFace(Chessboard board) {
		Point jp1, jp2;
		jp1 = board.getPositionOf(Chessman.�콫);// �콫
		jp2 = board.getPositionOf(Chessman.�ڽ�);// �ڽ�
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

	// �Ƿ��ںڷ������Ƿ���true
	public static boolean inBlack(int x, int y) {
		if (y >= 5 && y <= 9 && x >= 0 && x <= 8) {
			return true;
		}

		return false;

	}

	// �Ƿ��ں췽�����Ƿ���true
	public static boolean inRed(int x, int y) {
		if (inBlack(x, y)) {
			return false;
		}
		return true;
	}

	// (x1,y) (x2,y)֮���Ƿ�ֻ��һ�����ӣ��Ƿ�����
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

	// (x,y1) (x,y2)֮���Ƿ�ֻ��һ�����ӣ��Ƿ�����
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

	// �ƶ����� (x,y)�ƶ���(c_x,c_y)�ƶ�ǰ
	public static Chessboard moveTo(Chessboard cb, int x, int y, int c_x, int c_y) {
		cb.chessboard[x][y] = cb.chessboard[c_x][c_y];
		cb.chessboard[c_x][c_y] = null;
		return cb;
	}
}
