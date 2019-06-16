package club.lw666.mapper;

import club.lw666.domain.Permission;
import club.lw666.domain.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long pId);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long pId);

    List<Permission> selectAll(QueryVo queryVo);

    int updateByPrimaryKey(Permission record);

    /*根据角色id查询权限*/
    List<Permission> getPermissionWidthByRoleId(@Param("rid") Long rid);

    /*查出所有的权限 除开有的权限*/
    List<Permission> getPermissionChuRoleId(@Param("arrPid") Long[] arrPid);

    /*根据权限id  删除和角色的关系*/
    void delRoleAndPerWidthByPid(Long pId);
}