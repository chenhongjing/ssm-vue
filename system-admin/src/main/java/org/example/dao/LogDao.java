package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Log;
import org.example.entity.LogExample;

public interface LogDao {
    long countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(Log row);

    int insertSelective(Log row);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("row") Log row, @Param("example") LogExample example);

    int updateByExample(@Param("row") Log row, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log row);

    int updateByPrimaryKey(Log row);
}