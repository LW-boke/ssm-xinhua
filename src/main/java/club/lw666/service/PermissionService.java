package club.lw666.service;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.Permission;
import club.lw666.domain.QueryVo;

import java.util.List;

public interface PermissionService {
    /*获取所有的权限   并且进行分页*/
    PageListRes getPermissions(QueryVo queryVo);

    /*添加权限*/
    AjaxValueRes addPermission(Permission permission);

    /*编辑权限*/
    AjaxValueRes editPermission(Permission permission);

    /*删除权限*/
    AjaxValueRes delPermission(Long pId);

    /*根据角色id查询权限*/
    List<Permission> getPermissionWidthByRoleId(Long rid);

    /*查出所有的权限 除开有的权限*/
    List<Permission> getPermissionChuRoleId(Long[] arrPid);
}
