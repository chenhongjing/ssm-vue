package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Organ;
import org.example.entity.OrganExample;

public interface OrganDao {
    long countByExample(OrganExample example);

    int deleteByExample(OrganExample example);

    int deleteByPrimaryKey(Integer organId);

    int insert(Organ row);

    int insertSelective(Organ row);

    List<Organ> selectByExample(OrganExample example);

    Organ selectByPrimaryKey(Integer organId);

    int updateByExampleSelective(@Param("row") Organ row, @Param("example") OrganExample example);

    int updateByExample(@Param("row") Organ row, @Param("example") OrganExample example);

    int updateByPrimaryKeySelective(Organ row);

    int updateByPrimaryKey(Organ row);
}