package club.lw666.service.Impl;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.Permission;
import club.lw666.domain.QueryVo;
import club.lw666.mapper.PermissionMapper;
import club.lw666.service.PermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    /*获取所有的权限   并且进行分页*/
    @Override
    public PageListRes getPermissions(QueryVo queryVo) {
        Page<Permission> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Permission> permissions = permissionMapper.selectAll(queryVo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(permissions);
        return pageListRes;
    }

    @Override
    public AjaxValueRes addPermission(Permission permission) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            permissionMapper.insert(permission);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("添加权限成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("添加权限失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*编辑权限*/
    @Override
    public AjaxValueRes editPermission(Permission permission) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            permissionMapper.updateByPrimaryKey(permission);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("编辑权限成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("编辑权限失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*删除权限*/
    @Override
    public AjaxValueRes delPermission(Long pId) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            /*根据权限id  删除和角色的关系*/
            permissionMapper.delRoleAndPerWidthByPid(pId);
            permissionMapper.deleteByPrimaryKey(pId);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("删除权限成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("删除权限失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*根据角色id查询权限*/
    @Override
    public List<Permission> getPermissionWidthByRoleId(Long rid) {
        return permissionMapper.getPermissionWidthByRoleId(rid);
    }

    /*查出所有的权限 除开有的权限*/
    @Override
    public List<Permission> getPermissionChuRoleId(Long[] arrPid) {
        return permissionMapper.getPermissionChuRoleId(arrPid);
    }


}
