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
		ChessmanType type=ChessmanType.��;
		System.out.println(type.name());
	}
}


//// �̳��Ϲ���:�����ӻ����û�й����������Զ������
//for (Rule oRule : father.rules) {
//	Point p = location.get(oRule.getChessid());
//	if (oRule.getChessid() == man.getChessid() || (target != null && oRule.getChessid() == target.getChessid()))
//		continue;
//	switch (oRule.getType()) {
//	case ��:
//	case ��:
//	case ��:
//		if (p.x != sp.x && p.y != sp.y && p.x != ep.x && p.y != ep.y) {
//			rules.add(oRule);
//		} else if (!uMans.contains(oRule.getChessid()))
//			uMans.add(chessboard[p.x][p.y]);
//		break;
//	case ��:
//		if ((chessboard[p.x][p.y].getIsred() & !isMachineRed) || (!chessboard[p.x][p.y].getIsred() & isMachineRed)) // �Ǽ���
//			break;
//	case ��:
//		int diff1 = Math.abs((p.x - sp.x) + (p.y - sp.y));
//		int diff2 = Math.abs((p.x - ep.x) + (p.y - ep.y));
//		if (diff1 == 1 || diff2 == 1) {// ����������,������Ҫ���¼�������Ƿ����
//			if (!uMans.contains(oRule.getChessid()))
//				uMans.add(chessboard[p.x][p.y]);
//		} else // �����������ң���������ֱ��ʹ��
//			rules.add(oRule);
//		break;
//	case ʿ:
//		if ((chessboard[p.x][p.y].getIsred() & !isMachineRed) || (!chessboard[p.x][p.y].getIsred() & isMachineRed)) // �Ǽ���
//			break;
//	case ��:
//		int diffx1 = Math.abs(p.x - sp.x);
//		int diffx2 = Math.abs(p.x - ep.x);
//		int diffy1 = Math.abs(p.y - sp.y);
//		int diffy2 = Math.abs(p.y - ep.y);
//		if ((diffx1 == 1 && diffy1 == 1) || (diffx2 == 1 && diffy2 == 1)) {// �Խ�����
//			if (!uMans.contains(oRule.getChessid()))
//				uMans.add(chessboard[p.x][p.y]);
//		} else
//			rules.add(oRule);
//		break;
//	}
//}
//uMans.add(man);
//
//// ����¹���
//for (Chessman chessman : uMans)
//	addRulesOf(chessman, rules);
