package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.SystemFile;
import org.example.entity.SystemFileExample;

public interface SystemFileDao {
    long countByExample(SystemFileExample example);

    int deleteByExample(SystemFileExample example);

    int deleteByPrimaryKey(Integer fileId);

    int insert(SystemFile row);

    int insertSelective(SystemFile row);

    List<SystemFile> selectByExample(SystemFileExample example);

    SystemFile selectByPrimaryKey(Integer fileId);

    int updateByExampleSelective(@Param("row") SystemFile row, @Param("example") SystemFileExample example);

    int updateByExample(@Param("row") SystemFile row, @Param("example") SystemFileExample example);

    int updateByPrimaryKeySelective(SystemFile row);

    int updateByPrimaryKey(SystemFile row);
}