package cn.edu.cqu.kb.dao;

import cn.edu.cqu.kb.model.PRule;
import cn.edu.cqu.kb.model.PRuleExample;
import java.util.List;

public interface PRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PRule record);

    int insertSelective(PRule record);

    List<PRule> selectByExample(PRuleExample example);

    PRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PRule record);

    int updateByPrimaryKey(PRule record);
}