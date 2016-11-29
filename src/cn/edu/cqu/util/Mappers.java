package cn.edu.cqu.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.edu.cqu.kb.dao.BRuleMapper;
import cn.edu.cqu.kb.dao.CRuleMapper;
import cn.edu.cqu.kb.dao.ChessmanMapper;
import cn.edu.cqu.kb.dao.JRuleMapper;
import cn.edu.cqu.kb.dao.MRuleMapper;
import cn.edu.cqu.kb.dao.PRuleMapper;
import cn.edu.cqu.kb.dao.SRuleMapper;
import cn.edu.cqu.kb.dao.XRuleMapper;

public class Mappers {
	static {
		try {
			String resource = "mybatis.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
			sqlSession = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static SqlSession sqlSession;

	public static ChessmanMapper getChessmanMapper() {
		final ChessmanMapper chessmanMapper = sqlSession.getMapper(ChessmanMapper.class);
		return chessmanMapper;
	}

	public static BRuleMapper getBRuleMapper() {
		final BRuleMapper bRuleMapper = sqlSession.getMapper(BRuleMapper.class);
		return bRuleMapper;
	}

	public static CRuleMapper getCRuleMapper() {
		final CRuleMapper cRuleMapper = sqlSession.getMapper(CRuleMapper.class);
		return cRuleMapper;
	}

	public static JRuleMapper getJRuleMapper() {
		final JRuleMapper jRuleMapper = sqlSession.getMapper(JRuleMapper.class);
		return jRuleMapper;
	}

	public static MRuleMapper getMRuleMapper() {
		final MRuleMapper mRuleMapper = sqlSession.getMapper(MRuleMapper.class);
		return mRuleMapper;
	}

	public static PRuleMapper getPRuleMapper() {
		final PRuleMapper pRuleMapper = sqlSession.getMapper(PRuleMapper.class);
		return pRuleMapper;
	}

	public static SRuleMapper getSRuleMapper() {
		final SRuleMapper sRuleMapper = sqlSession.getMapper(SRuleMapper.class);
		return sRuleMapper;
	}

	public static XRuleMapper getXRuleMapper() {
		final XRuleMapper xRuleMapper = sqlSession.getMapper(XRuleMapper.class);
		return xRuleMapper;
	}

	@Override
	protected void finalize() throws Throwable {
		sqlSession.close();
		super.finalize();
	}
}
