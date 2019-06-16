package club.lw666.web;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.domain.User;
import club.lw666.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    /*跳转到用户管理页面*/
    @RequestMapping("/user")
    public String user() {
        return "user";
    }

    /*获取所有的用户账号 并且进行分页*/
    @RequestMapping("/getArrUsers")
    @ResponseBody
    public PageListRes getArrUsers(QueryVo queryVo) {
        System.out.println(queryVo);
        return userService.getArrUsers(queryVo);
    }

    /*增加用户账号*/
    @RequestMapping("/addUser")
    @ResponseBody
    public AjaxValueRes addUser(User user) {
        return userService.addUser(user);
    }

    /*编辑用户账号*/
    @RequestMapping("/editUser")
    @ResponseBody
    public AjaxValueRes editUser(User user) {
        return userService.editUser(user);
    }

    /*删除用户账号*/
    @RequestMapping("/delUser/{id}")
    @ResponseBody
    public AjaxValueRes delUser(@PathVariable Long id) {
        return userService.delUser(id);
    }
}
