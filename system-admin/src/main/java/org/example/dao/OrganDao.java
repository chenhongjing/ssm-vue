package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Organ;
import org.example.entity.OrganExample;
import org.example.entity.OrganWithBLOBs;

public interface OrganDao {
    long countByExample(OrganExample example);

    int deleteByExample(OrganExample example);

    int deleteByPrimaryKey(Integer organId);

    int insert(OrganWithBLOBs row);

    int insertSelective(OrganWithBLOBs row);

    List<OrganWithBLOBs> selectByExampleWithBLOBs(OrganExample example);

    List<Organ> selectByExample(OrganExample example);

    OrganWithBLOBs selectByPrimaryKey(Integer organId);

    int updateByExampleSelective(@Param("row") OrganWithBLOBs row, @Param("example") OrganExample example);

    int updateByExampleWithBLOBs(@Param("row") OrganWithBLOBs row, @Param("example") OrganExample example);

    int updateByExample(@Param("row") Organ row, @Param("example") OrganExample example);

    int updateByPrimaryKeySelective(OrganWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(OrganWithBLOBs row);

    int updateByPrimaryKey(Organ row);
}