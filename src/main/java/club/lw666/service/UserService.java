package club.lw666.service;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.domain.User;

import java.util.List;

public interface UserService {
    PageListRes getArrUsers(QueryVo queryVo);

    /*增加用户账号*/
    AjaxValueRes addUser(User user);

    /*编辑用户账号*/
    AjaxValueRes editUser(User user);

    /*删除用户账号*/
    AjaxValueRes delUser(Long id);

    /*查询数据库是否有这个用户名*/
    User geuUserWidthByUsername(String username);

    /*根据角色id查询角色*/
    String getRoleNameWidthByRoleId(Long rId);

    /*根据角色id查询权限*/
    List<String> getPermissionNameWidthByRoleId(Long rId);
}
