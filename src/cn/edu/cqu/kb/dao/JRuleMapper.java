package cn.edu.cqu.kb.dao;

import cn.edu.cqu.kb.model.JRule;
import cn.edu.cqu.kb.model.JRuleExample;
import java.util.List;

public interface JRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JRule record);

    int insertSelective(JRule record);

    List<JRule> selectByExample(JRuleExample example);

    JRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JRule record);

    int updateByPrimaryKey(JRule record);
}