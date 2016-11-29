package test;

import java.awt.Point;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.edu.cqu.kb.model.Chessman;
import cn.edu.cqu.kb.model.ChessmanType;
import cn.edu.cqu.kb.model.Rule;

public class MyTest {
	
	@Test
	public void test() throws IOException {
		String resource = "mybatis.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = null;
		try {
			session = factory.openSession();
//			Person person = (Person) session.selectOne("com.xiamen.mapper.PersonMapper.getPersonById", 1);
//			if (person == null)
//				System.out.println("null");
//			else
//				System.out.println(person.toString());
		} finally {
			session.close();
		}
		try {
			session = factory.openSession();
//			PersonMapper mapper = session.getMapper(PersonMapper.class);
//			Person person = mapper.getPersonById(1);
//			if (person == null)
//				System.out.println("null");
//			else
//				System.out.println(person.toString());
		} finally {
			session.close();
		}
	}
	
	@Test
	public void test2(){
		ChessmanType type=ChessmanType.兵;
		System.out.println(type.name());
	}
}


//// 继承老规则:这样子会造成没有规则的棋子永远不会走
//for (Rule oRule : father.rules) {
//	Point p = location.get(oRule.getChessid());
//	if (oRule.getChessid() == man.getChessid() || (target != null && oRule.getChessid() == target.getChessid()))
//		continue;
//	switch (oRule.getType()) {
//	case 炮:
//	case 车:
//	case 将:
//		if (p.x != sp.x && p.y != sp.y && p.x != ep.x && p.y != ep.y) {
//			rules.add(oRule);
//		} else if (!uMans.contains(oRule.getChessid()))
//			uMans.add(chessboard[p.x][p.y]);
//		break;
//	case 兵:
//		if ((chessboard[p.x][p.y].getIsred() & !isMachineRed) || (!chessboard[p.x][p.y].getIsred() & isMachineRed)) // 非己方
//			break;
//	case 马:
//		int diff1 = Math.abs((p.x - sp.x) + (p.y - sp.y));
//		int diff2 = Math.abs((p.x - ep.x) + (p.y - ep.y));
//		if (diff1 == 1 || diff2 == 1) {// 在上下左右,，则需要重新计算规则是否可行
//			if (!uMans.contains(oRule.getChessid()))
//				uMans.add(chessboard[p.x][p.y]);
//		} else // 不在上下左右，则规则可以直接使用
//			rules.add(oRule);
//		break;
//	case 士:
//		if ((chessboard[p.x][p.y].getIsred() & !isMachineRed) || (!chessboard[p.x][p.y].getIsred() & isMachineRed)) // 非己方
//			break;
//	case 相:
//		int diffx1 = Math.abs(p.x - sp.x);
//		int diffx2 = Math.abs(p.x - ep.x);
//		int diffy1 = Math.abs(p.y - sp.y);
//		int diffy2 = Math.abs(p.y - ep.y);
//		if ((diffx1 == 1 && diffy1 == 1) || (diffx2 == 1 && diffy2 == 1)) {// 对角线上
//			if (!uMans.contains(oRule.getChessid()))
//				uMans.add(chessboard[p.x][p.y]);
//		} else
//			rules.add(oRule);
//		break;
//	}
//}
//uMans.add(man);
//
//// 添加新规则
//for (Chessman chessman : uMans)
//	addRulesOf(chessman, rules);
