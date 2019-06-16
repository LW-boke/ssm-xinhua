package club.lw666.web;

import club.lw666.domain.*;
import club.lw666.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
public class PermissionController {
    @Resource(name = "permissionService")
    private PermissionService permissionService;

    /*跳转到权限管理*/
    @RequestMapping("/permission")
    public String permission() {
        return "permission";
    }

    /*获取所有的权限   并且进行分页*/
    @RequestMapping("/getPermissions")
    @ResponseBody
    public PageListRes getPermissions(QueryVo queryVo) {
        return permissionService.getPermissions(queryVo);
    }

    /*添加权限*/
    @RequestMapping("/addPermission")
    @ResponseBody
    public AjaxValueRes addPermission(Permission permission) {
        return permissionService.addPermission(permission);
    }

    /*编辑权限*/
    @RequestMapping("/editPermission")
    @ResponseBody
    public AjaxValueRes editPermission(Permission permission) {
        return permissionService.editPermission(permission);
    }

    /*删除权限*/
    @RequestMapping("/delPermission/{pId}")
    @ResponseBody
    public AjaxValueRes delPermission(@PathVariable Long pId) {
        return permissionService.delPermission(pId);
    }

    /*根据角色id查询权限*/
    @RequestMapping("/getPermissionWidthByRoleId")
    @ResponseBody
    public List<Permission> getPermissionWidthByRoleId(@RequestParam(value = "rId", required = false) Long rid) {
        System.out.println(rid);
        return permissionService.getPermissionWidthByRoleId(rid);
    }

    /*查出所有的权限 除开有的权限*/
    @RequestMapping("/getPermissionChuRoleId")
    @ResponseBody
    public List<Permission> getPermissionChuRoleId(@RequestParam(value = "arrPid[]", required = false) Long arrPid[]) {
        System.out.println(Arrays.toString(arrPid));
        return permissionService.getPermissionChuRoleId(arrPid);
    }
}
