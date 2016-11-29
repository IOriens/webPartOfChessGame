package cn.edu.cqu.kb.dao;

import java.util.List;

import cn.edu.cqu.kb.model.XRule;
import cn.edu.cqu.kb.model.XRuleExample;

public interface XRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XRule record);

    int insertSelective(XRule record);

    List<XRule> selectByExample(XRuleExample example);

    XRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XRule record);

    int updateByPrimaryKey(XRule record);
}