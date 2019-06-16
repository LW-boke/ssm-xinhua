package club.lw666.mapper;

import club.lw666.domain.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    List<Menu> getMenuTrees();

    int updateByPrimaryKey(Menu record);
}