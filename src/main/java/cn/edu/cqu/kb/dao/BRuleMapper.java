package cn.edu.cqu.kb.dao;

import java.util.List;

import cn.edu.cqu.kb.model.BRule;
import cn.edu.cqu.kb.model.BRuleExample;

public interface BRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BRule record);

    int insertSelective(BRule record);

    List<BRule> selectByExample(BRuleExample example);

    BRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BRule record);

    int updateByPrimaryKey(BRule record);
}