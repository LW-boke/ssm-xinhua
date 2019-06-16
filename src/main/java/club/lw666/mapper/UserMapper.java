package club.lw666.mapper;

import club.lw666.domain.QueryVo;
import club.lw666.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll(QueryVo queryVo);

    int updateByPrimaryKey(User record);

    /*根据账号 查询用户*/
    User geuUserWidthByUsername(String username);
    /*根据角色id查询角色*/
    List<String> getRoleWidthByRoleId(Long rId);


}