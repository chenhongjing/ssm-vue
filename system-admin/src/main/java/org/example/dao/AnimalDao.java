package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Animal;
import org.example.entity.AnimalExample;

public interface AnimalDao {
    long countByExample(AnimalExample example);

    int deleteByExample(AnimalExample example);

    int deleteByPrimaryKey(Integer animalId);

    int insert(Animal row);

    int insertSelective(Animal row);

    List<Animal> selectByExample(AnimalExample example);

    Animal selectByPrimaryKey(Integer animalId);

    int updateByExampleSelective(@Param("row") Animal row, @Param("example") AnimalExample example);

    int updateByExample(@Param("row") Animal row, @Param("example") AnimalExample example);

    int updateByPrimaryKeySelective(Animal row);

    int updateByPrimaryKey(Animal row);
}