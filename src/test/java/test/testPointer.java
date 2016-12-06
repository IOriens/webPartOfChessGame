package test;

import java.awt.Point;

public class testPointer {

	public static void main(String[] args) {
		Point p1,p2,p3;
		p1=new Point(1, 2);
		p2=new Point(2, 3);
		p3=p1;
		System.out.println(p3);
		p3=p2;
		System.out.println(p3);
		System.out.println(p1);
	}

}
