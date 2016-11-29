package cn.edu.cqu.kb.dao;

import java.util.List;

import cn.edu.cqu.kb.model.MRule;
import cn.edu.cqu.kb.model.MRuleExample;

public interface MRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MRule record);

    int insertSelective(MRule record);

    List<MRule> selectByExample(MRuleExample example);

    MRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MRule record);

    int updateByPrimaryKey(MRule record);
}