package test;

import java.io.FileNotFoundException;

import org.junit.Test;

import cn.edu.cqu.engine.Machine;
import cn.edu.cqu.engine.Move;

public class ChessBoardTest {
	@Test
	public void test() {
		try {
			Machine machine = new Machine(2, true);
			Move move = machine.search();
			System.out.println("�������Ĳ��裺" + move);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
