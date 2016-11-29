package cn.edu.cqu.kb.dao;

import java.util.List;

import cn.edu.cqu.kb.model.Chessman;
import cn.edu.cqu.kb.model.ChessmanExample;

public interface ChessmanMapper {
    int deleteByPrimaryKey(Integer chessid);

    int insert(Chessman record);

    int insertSelective(Chessman record);

    List<Chessman> selectByExample(ChessmanExample example);

    Chessman selectByPrimaryKey(Integer chessid);

    int updateByPrimaryKeySelective(Chessman record);

    int updateByPrimaryKey(Chessman record);
}