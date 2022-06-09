package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.SystemLog;
import org.example.entity.SystemLogExample;

public interface SystemLogDao {
    long countByExample(SystemLogExample example);

    int deleteByExample(SystemLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(SystemLog row);

    int insertSelective(SystemLog row);

    List<SystemLog> selectByExample(SystemLogExample example);

    SystemLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("row") SystemLog row, @Param("example") SystemLogExample example);

    int updateByExample(@Param("row") SystemLog row, @Param("example") SystemLogExample example);

    int updateByPrimaryKeySelective(SystemLog row);

    int updateByPrimaryKey(SystemLog row);
}