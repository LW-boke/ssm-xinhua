package club.lw666.mapper;

import club.lw666.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long rId);

    int insert(Role record);

    Role selectByPrimaryKey(Long rId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    /*根据角色id和权限id 删除关系*/
    void delRoleAndPerWidthRidAndPid(@Param("rId") Long rId, @Param("pId") Long pId);

    /*根据角色id和权限id 添加关系*/
    void addRoleAndPerWidthRidAndPid(@Param("rId") Long rId, @Param("pId") Long pId);

    /*根据角色Id  删除角色和权限的关系*/
    void delRoleAndPerWidthByRid(Long rid);

    /*根据角色id查询角色名称*/
    String getRoleNameWidthByRoleId(Long rId);

    /*根据角色id查询权限*/
    List<String> getPermissionNameWidthByRoleId(Long rId);
}