package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.DynamicParameter;
import org.example.entity.DynamicParameterExample;

public interface DynamicParameterDao {
    long countByExample(DynamicParameterExample example);

    int deleteByExample(DynamicParameterExample example);

    int deleteByPrimaryKey(Integer paramId);

    int insert(DynamicParameter row);

    int insertSelective(DynamicParameter row);

    List<DynamicParameter> selectByExampleWithBLOBs(DynamicParameterExample example);

    List<DynamicParameter> selectByExample(DynamicParameterExample example);

    DynamicParameter selectByPrimaryKey(Integer paramId);

    int updateByExampleSelective(@Param("row") DynamicParameter row, @Param("example") DynamicParameterExample example);

    int updateByExampleWithBLOBs(@Param("row") DynamicParameter row, @Param("example") DynamicParameterExample example);

    int updateByExample(@Param("row") DynamicParameter row, @Param("example") DynamicParameterExample example);

    int updateByPrimaryKeySelective(DynamicParameter row);

    int updateByPrimaryKeyWithBLOBs(DynamicParameter row);

    int updateByPrimaryKey(DynamicParameter row);
}