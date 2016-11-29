package cn.edu.cqu.engine;

import java.awt.Point;

public class Move {
	public Point sp;// start point
	public Point ep;// end point

	public String toString() {
		return "(" + sp.x + "," + sp.y + ")-->" + "(" + ep.x + "," + ep.y + ")";
	}
}
