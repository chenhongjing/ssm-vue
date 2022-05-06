package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Menu;
import org.example.entity.MenuExample;

public interface MenuDao {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu row);

    int insertSelective(Menu row);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("row") Menu row, @Param("example") MenuExample example);

    int updateByExample(@Param("row") Menu row, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu row);

    int updateByPrimaryKey(Menu row);
}