package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

import cn.edu.cqu.engine.Machine;
import cn.edu.cqu.engine.Move;

public class ChessBoardTest {
	/**
	 * 实际的测试例子
	 */
	@Test
	public void test() {
		try {
			Scanner sin = new Scanner(System.in);
			boolean end = false;
			Machine machine = new Machine(2, new File("棋盘/7.txt"));
			while (!end) {
				
				
				
					
//				int[] ps = new int[4];
//				for (int i = 0; i < ps.length; i++)
//					ps[i] = sin.nextInt();
//				Move userMove = new Move(ps[0], ps[1], ps[2], ps[3]);
				Move userMove = new Move(5, 0, 4, 0);
//				5 0 4 0
				machine.makeMove(userMove.sp, userMove.ep);
//				
				
				
				System.out.println(machine.getCurrentChessboard());
				Move move = machine.search();
				System.out.println("搜索出的步骤：" + move);
				
				
//				JSONObject obj = new JSONObject();
//				obj.put("array2D", machine.getCurrentChessboard().to2DArray()[0])
				System.out.println(JSONValue.toJSONString(machine.getCurrentChessboard().toJSON()));
				
				if (move.redWin != null) {
					if (move.redWin) {
						System.out.println("红方赢");
					} else {
						System.out.println("黑方赢");
					}
					break;
				}
				
				
				
//				
//				if (move.redWin != null) {
//					if (move.redWin) {
//						System.out.println("红方赢");
//					} else {
//						System.out.println("黑方赢");
//					}
//					break;
//				}
			}
			sin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void fileTest() {
		File file = new File("src");
		System.out.println(file.getAbsolutePath());
	}
}
