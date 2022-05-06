package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.OtherParameter;
import org.example.entity.OtherParameterExample;

public interface OtherParameterDao {
    long countByExample(OtherParameterExample example);

    int deleteByExample(OtherParameterExample example);

    int deleteByPrimaryKey(Integer paramId);

    int insert(OtherParameter row);

    int insertSelective(OtherParameter row);

    List<OtherParameter> selectByExampleWithBLOBs(OtherParameterExample example);

    List<OtherParameter> selectByExample(OtherParameterExample example);

    OtherParameter selectByPrimaryKey(Integer paramId);

    int updateByExampleSelective(@Param("row") OtherParameter row, @Param("example") OtherParameterExample example);

    int updateByExampleWithBLOBs(@Param("row") OtherParameter row, @Param("example") OtherParameterExample example);

    int updateByExample(@Param("row") OtherParameter row, @Param("example") OtherParameterExample example);

    int updateByPrimaryKeySelective(OtherParameter row);

    int updateByPrimaryKeyWithBLOBs(OtherParameter row);

    int updateByPrimaryKey(OtherParameter row);
}