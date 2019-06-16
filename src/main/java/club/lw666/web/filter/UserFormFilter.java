package club.lw666.web.filter;

import club.lw666.domain.AjaxValueRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserFormFilter extends FormAuthenticationFilter {
    /*认证成功执行*/
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        /*设置编码*/
        response.setCharacterEncoding("utf-8");
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        ajaxValueRes.setSuccess(true);
        ajaxValueRes.setMrg("登录成功");
        /*把一个对象转化成对象*/
        String res = new ObjectMapper().writeValueAsString(ajaxValueRes);
        response.getWriter().print(res);
        /*设置false 不执行下一个过滤器*/
        return false;
    }

    /*认证失败执行*/
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        /*设置编码*/
        response.setCharacterEncoding("utf-8");
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        ajaxValueRes.setSuccess(false);
        if (UnknownAccountException.class.getName().equals(e.getClass().getName())) {
            ajaxValueRes.setMrg("账号不存在");
        } else if (IncorrectCredentialsException.class.getName().equals(e.getClass().getName())) {
            ajaxValueRes.setMrg("密码错误");
        } else {
            ajaxValueRes.setMrg("未知错误");
        }
        try {
            /*把对象转化成字符串*/
            String res = new ObjectMapper().writeValueAsString(ajaxValueRes);
            response.getWriter().print(res);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        /*设置false 不执行下一个过滤器*/
        return false;
    }
}
