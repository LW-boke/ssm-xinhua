package club.lw666.service.Impl;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Role;
import club.lw666.mapper.RoleMapper;
import club.lw666.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /*获取所有的角色*/
    @Override
    public List<Role> getRole() {
        return roleMapper.selectAll();
    }

    /*根据角色id和权限id 删除关系*/
    @Override
    public AjaxValueRes delRoleAndPerWidthRidAndPid(Long rId, Long pId) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            roleMapper.delRoleAndPerWidthRidAndPid(rId, pId);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("删除成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("删除失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*根据角色id和权限id 添加关系*/
    @Override
    public AjaxValueRes addRoleAndPerWidthRidAndPid(Long rId, Long pId) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            roleMapper.addRoleAndPerWidthRidAndPid(rId, pId);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("添加成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("添加失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    @Override
    public AjaxValueRes addRole(Role role) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            roleMapper.insert(role);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("添加角色成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("添加角色失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    @Override
    public AjaxValueRes editRole(Role role) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            roleMapper.updateByPrimaryKey(role);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("添加角色成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("添加角色失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*根据角色id删除角色*/
    @Override
    public AjaxValueRes delRole(Long rid) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            /*根据角色Id  删除角色和权限的关系*/
            roleMapper.delRoleAndPerWidthByRid(rid);
            /*删除角色*/
            roleMapper.deleteByPrimaryKey(rid);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("删除角色成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("删除角色失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }
}
