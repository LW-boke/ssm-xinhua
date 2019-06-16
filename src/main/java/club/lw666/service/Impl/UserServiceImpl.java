package club.lw666.service.Impl;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.domain.User;
import club.lw666.mapper.RoleMapper;
import club.lw666.mapper.UserMapper;
import club.lw666.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /*获取所有的用户账号 并且进行分页*/
    @Override
    public PageListRes getArrUsers(QueryVo queryVo) {
        Page<User> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<User> userList = userMapper.selectAll(queryVo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(userList);
        return pageListRes;
    }

    /*增加用户账号*/
    @Override
    public AjaxValueRes addUser(User user) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            String password = user.getPassword();
            /*进行md5 加密*/
            password = new Md5Hash(password, user.getUsername(), 2).toString();
            System.out.println(password);
            user.setPassword(password);
            userMapper.insert(user);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("添加用户账号成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("添加用户账号失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*编辑用户账号*/
    @Override
    public AjaxValueRes editUser(User user) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            userMapper.updateByPrimaryKey(user);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("编辑用户账号成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("编辑用户账号失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    @Override
    public AjaxValueRes delUser(Long id) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            userMapper.deleteByPrimaryKey(id);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("删除用户账号成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("删除用户账号失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    @Override
    public User geuUserWidthByUsername(String username) {
        return userMapper.geuUserWidthByUsername(username);
    }

    /*根据角色id查询角色*/
    @Override
    public String getRoleNameWidthByRoleId(Long rId) {
        return roleMapper.getRoleNameWidthByRoleId(rId);
    }

    /*根据角色id查询权限*/
    @Override
    public List<String> getPermissionNameWidthByRoleId(Long rId) {
        return roleMapper.getPermissionNameWidthByRoleId(rId);
    }
}
