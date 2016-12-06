package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import cn.edu.cqu.db.Chessboard;
import cn.edu.cqu.engine.Machine;
import cn.edu.cqu.engine.Move;
import cn.edu.cqu.kb.model.Rule;
import cn.edu.cqu.util.UtilFuncs;

public class ChessBoardTest {
	/**
	 * Êµ¼ÊµÄ²âÊÔÀý×Ó
	 */
	@Test
	public void test() {
		try {
			Scanner sin = new Scanner(System.in);
			boolean end = false;
			Machine machine = new Machine(6, new File("ÆåÅÌ/3.txt"));
			while (!end) {
				System.out.println(machine.getCurrentChessboard());
				Date startTime = new Date();
				Move move = machine.search();
				Date endTime = new Date();
				System.err.println("time cost:" + ((endTime.getTime() - startTime.getTime()) / 1000) + "s");
				System.out.println("ËÑË÷³öµÄ²½Öè£º" + move);
				for (Rule rule : machine.getReasonList())
					System.out.print(rule);
				System.out.println();
				System.out.println(machine.getCurrentChessboard());
				if (move.redWin != null) {
					if (move.redWin) {
						System.out.println("ºì·½Ó®");
					} else {
						System.out.println("ºÚ·½Ó®");
					}
					break;
				}

				String command = sin.nextLine();
				int[] ps = new int[4];
				String[] pss;
				if (command.equals("close")) {
					break;
				} else {
					pss = command.split(" ");
				}
				for (int i = 0; i < ps.length; i++)
					ps[i] = Integer.valueOf(pss[i].trim());
				Move userMove = new Move(ps[0], ps[1], ps[2], ps[3]);
				machine.makeMove(userMove.sp, userMove.ep);
				if (move.redWin != null) {
					if (move.redWin) {
						System.out.println("ºì·½Ó®");
					} else {
						System.out.println("ºÚ·½Ó®");
					}
					break;
				}
			}
			machine.close();
			sin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() throws FileNotFoundException {
		Chessboard chessboard = new Chessboard();
		UtilFuncs.initChessboardTo(new File("ÆåÅÌ/3 - ¸±±¾ (2).txt"), chessboard);
		//chessboard.printInfo();
		System.out.println(chessboard);
		int count = 0;
		while (chessboard.hasRule()) {
			count++;
			System.out.println(chessboard.getNextRule());
		}
		System.err.println(count);

	}

	@Test
	public void test3() throws FileNotFoundException {
		PropertyConfigurator.configure("E:/projects/eclipse workspace/ChineseChess/src/main/resources/log4j.properties");
		Logger log = Logger.getLogger(ChessBoardTest.class);
		log.debug("test");
		log.error("test");
		log.debug("test");

		Chessboard c1 = new Chessboard();
		UtilFuncs.initChessboardTo(new File("ÆåÅÌ/3.txt"), c1);
		Chessboard c2 = new Chessboard();
		UtilFuncs.initChessboardTo(new File("ÆåÅÌ/3.txt"), c2);

	}
}
