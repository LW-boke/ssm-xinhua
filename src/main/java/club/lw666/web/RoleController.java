package club.lw666.web;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Role;
import club.lw666.mapper.RoleMapper;
import club.lw666.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoleController {
    @Resource(name = "roleService")
    private RoleService roleService;

    /*跳转角色页面*/
    @RequestMapping("/role")
    public String role() {
        return "role";
    }

    /*获取所有的角色*/
    @RequestMapping("/getRole")
    @ResponseBody
    public List<Role> getRole() {
        return roleService.getRole();
    }

    /*根据角色id和权限id 删除关系*/
    @RequestMapping("/delRoleAndPerWidthRidAndPid")
    @ResponseBody
    public AjaxValueRes delRoleAndPerWidthRidAndPid(Long rId, Long pId) {
        return roleService.delRoleAndPerWidthRidAndPid(rId, pId);
    }

    /*根据角色id和权限id 添加关系*/
    @RequestMapping("/addRoleAndPerWidthRidAndPid")
    @ResponseBody
    public AjaxValueRes addRoleAndPerWidthRidAndPid(Long rId, Long pId) {
        return roleService.addRoleAndPerWidthRidAndPid(rId, pId);
    }

    /*添加角色*/
    @RequestMapping("/addRole")
    @ResponseBody
    public AjaxValueRes addRole(Role role) {
        return roleService.addRole(role);
    }

    /*编辑角色*/
    @RequestMapping("/editRole")
    @ResponseBody
    public AjaxValueRes editRole(Role role) {
        return roleService.editRole(role);
    }

    /*根据角色id删除角色*/
    @RequestMapping("/delRole/{rid}")
    @ResponseBody
    public AjaxValueRes delRole(@PathVariable Long rid) {
        return roleService.delRole(rid);
    }
}
