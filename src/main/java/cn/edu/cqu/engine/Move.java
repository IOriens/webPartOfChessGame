package cn.edu.cqu.engine;

import java.awt.Point;

public class Move {
	public Point sp;// start point
	public Point ep;// end point
	public Boolean redWin;

	public Boolean getRedWin() {
		return redWin;
	}

	public Move() {
	}

	public Move(int spx, int spy, int epx, int epy) {
		sp = new Point(spx, spy);
		ep = new Point(epx, epy);
	}

	public void setRedWin(boolean redWin) {
		this.redWin = redWin;
	}

	public String toString() {
		return "(" + sp.x + "," + sp.y + ")-->" + "(" + ep.x + "," + ep.y + ")";
	}
}
