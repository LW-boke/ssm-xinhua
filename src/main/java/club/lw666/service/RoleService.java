package club.lw666.service;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Role;

import java.util.List;

public interface RoleService {
    /*获取所有的角色*/
    List<Role> getRole();

    /*根据角色id和权限id 删除关系*/
    AjaxValueRes delRoleAndPerWidthRidAndPid(Long rId, Long pId);

    /*根据角色id和权限id 添加关系*/
    AjaxValueRes addRoleAndPerWidthRidAndPid(Long rId, Long pId);

    /*添加角色*/
    AjaxValueRes addRole(Role role);

    /*编辑角色*/
    AjaxValueRes editRole(Role role);

    /*根据角色id删除角色*/
    AjaxValueRes delRole(Long rid);
}
