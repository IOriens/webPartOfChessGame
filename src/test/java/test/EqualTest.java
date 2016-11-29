package test;

import org.junit.Test;

import cn.edu.cqu.kb.dao.BRuleMapper;
import cn.edu.cqu.kb.model.BRule;
import cn.edu.cqu.util.Mappers;

public class EqualTest {

	
	@Test
	public void test(){
		BRuleMapper mapper = Mappers.getBRuleMapper();
		BRule rule1 = mapper.selectByPrimaryKey(1);
		BRule rule2 = mapper.selectByPrimaryKey(1);
		if (rule1.equals(rule2)) {
			System.out.println("equal");
		} else {
			System.out.println("not equal");
		}
	}
}


