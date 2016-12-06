package test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import cn.edu.cqu.kb.dao.BRuleMapper;
import cn.edu.cqu.kb.dao.CRuleMapper;
import cn.edu.cqu.kb.dao.ChessmanMapper;
import cn.edu.cqu.kb.dao.JRuleMapper;
import cn.edu.cqu.kb.dao.MRuleMapper;
import cn.edu.cqu.kb.dao.PRuleMapper;
import cn.edu.cqu.kb.dao.SRuleMapper;
import cn.edu.cqu.kb.dao.XRuleMapper;
import cn.edu.cqu.kb.model.BRule;
import cn.edu.cqu.kb.model.CRule;
import cn.edu.cqu.kb.model.Chessman;
import cn.edu.cqu.kb.model.ChessmanExample;
import cn.edu.cqu.kb.model.JRule;
import cn.edu.cqu.kb.model.MRule;
import cn.edu.cqu.kb.model.PRule;
import cn.edu.cqu.kb.model.SRule;
import cn.edu.cqu.kb.model.XRule;
import cn.edu.cqu.util.Mappers;

public class MapperTest {

	@Test
	public void ChessmanMapperTest() throws IOException {

		ChessmanMapper mapper = Mappers.getChessmanMapper();
		Chessman chessman = mapper.selectByPrimaryKey(1);
		List<Chessman> chessmans = mapper.selectByExample(new ChessmanExample());
		if (chessman == null)
			System.out.println("null");
		else
			System.out.println(chessman.toString());
		if (chessmans.size() == 0)
			System.out.println("0");
		else
			System.out.println(chessmans.size());
	}

	@Test
	public void BRuleTest() {
		BRuleMapper mapper = Mappers.getBRuleMapper();
		BRule rule = mapper.selectByPrimaryKey(1);
		if (rule == null) {
			System.out.println("null");
		} else {
			System.out.println("rule 1 exist");
		}
	}

	@Test
	public void CRuleTest() {
		CRuleMapper mapper = Mappers.getCRuleMapper();
		CRule rule = mapper.selectByPrimaryKey(1);
		if (rule == null) {
			System.out.println("null");
		} else {
			System.out.println("rule 1 exist");
		}
	}

	@Test
	public void JRuleTest() {
		JRuleMapper mapper = Mappers.getJRuleMapper();
		JRule rule = mapper.selectByPrimaryKey(1);
		if (rule == null) {
			System.out.println("null");
		} else {
			System.out.println("rule 1 exist");
		}
	}

	@Test
	public void PRuleTest() {
		PRuleMapper mapper = Mappers.getPRuleMapper();
		PRule rule = mapper.selectByPrimaryKey(1);
		if (rule == null) {
			System.out.println("null");
		} else {
			System.out.println("rule 1 exist");
		}
	}

	@Test
	public void MRuleTest() {
		MRuleMapper mapper = Mappers.getMRuleMapper();
		MRule rule = mapper.selectByPrimaryKey(1);
		if (rule == null) {
			System.out.println("null");
		} else {
			System.out.println("rule 1 exist");
		}
	}

	@Test
	public void SRuleTest() {
		SRuleMapper mapper = Mappers.getSRuleMapper();
		SRule rule = mapper.selectByPrimaryKey(1);
		if (rule == null) {
			System.out.println("null");
		} else {
			System.out.println("rule 1 exist");
		}
	}

	@Test
	public void XRuleTest() {
		XRuleMapper mapper = Mappers.getXRuleMapper();
		XRule rule = mapper.selectByPrimaryKey(1);
		if (rule == null) {
			System.out.println("null");
		} else {
			System.out.println("rule 1 exist");
		}
	}
}
