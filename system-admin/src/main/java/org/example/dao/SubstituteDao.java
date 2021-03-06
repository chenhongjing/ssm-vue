package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Substitute;
import org.example.entity.SubstituteExample;

public interface SubstituteDao {
    long countByExample(SubstituteExample example);

    int deleteByExample(SubstituteExample example);

    int deleteByPrimaryKey(Integer substituteId);

    int insert(Substitute row);

    int insertSelective(Substitute row);

    List<Substitute> selectByExample(SubstituteExample example);

    Substitute selectByPrimaryKey(Integer substituteId);

    int updateByExampleSelective(@Param("row") Substitute row, @Param("example") SubstituteExample example);

    int updateByExample(@Param("row") Substitute row, @Param("example") SubstituteExample example);

    int updateByPrimaryKeySelective(Substitute row);

    int updateByPrimaryKey(Substitute row);
}