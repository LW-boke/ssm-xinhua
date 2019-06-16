package club.lw666.web.realm;

import club.lw666.domain.User;
import club.lw666.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.AbstractNativeSessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Resource(name = "userService")
    private UserService userService;

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*获取登录时表单提交的用户名*/
        String username = (String) token.getPrincipal();
        /*查询数据库是否有这个用户名*/
        User user = userService.geuUserWidthByUsername(username);
        System.out.println(user);
        if (user == null) {
            return null;
        }
        /*参数1  主体信息  参数2  凭证密码  参数3 盐  参数4 当前realm名称*/
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(username),
                this.getName());
        return authenticationInfo;
    }

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        /*声明权限集合*/
        List<String> permissions = new ArrayList<>();

        if (user.getAdmin()) {
            permissions.add("*:*");
        } else {
            /*根据角色id查询权限*/
            permissions = userService.getPermissionNameWidthByRoleId(user.getRoles().getRId());
        }
        /*把角色和权限添加到 授权信息中*/
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole(user.getRoles().getRName());
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }


}
