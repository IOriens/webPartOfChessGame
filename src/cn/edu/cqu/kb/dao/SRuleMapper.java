package cn.edu.cqu.kb.dao;

import java.util.List;

import cn.edu.cqu.kb.model.SRule;
import cn.edu.cqu.kb.model.SRuleExample;

public interface SRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SRule record);

    int insertSelective(SRule record);

    List<SRule> selectByExample(SRuleExample example);

    SRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SRule record);

    int updateByPrimaryKey(SRule record);
}