package cn.edu.cqu.kb.dao;

import java.util.List;

import cn.edu.cqu.kb.model.CRule;
import cn.edu.cqu.kb.model.CRuleExample;

public interface CRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CRule record);

    int insertSelective(CRule record);

    List<CRule> selectByExample(CRuleExample example);

    CRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CRule record);

    int updateByPrimaryKey(CRule record);
}