package club.lw666.web;

import club.lw666.domain.AjaxValueRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class PermissionAdvice {
    @ExceptionHandler(value = AuthorizationException.class)
    public void handleShiroEcxeption(HandlerMethod method, HttpServletResponse response) throws IOException {  //  HandlerMethod   发送异常的方法
        /*
            通过HandlerMethod 可以获取方法上面的注释
            如果是ajax请求 那么就会有ResponseBody注释
            则没有 就不是ajax请求
         */
        /*获取报错方法上面是否有ResponseBody注释  有则是ajax请求*/
        ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
        System.out.println(responseBody);
        if (responseBody == null) {
            /*重定向*/
            response.sendRedirect("PermissionErr.jsp");
        } else {
            /*设置编码*/
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            AjaxValueRes ajaxValueRes = new AjaxValueRes();
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("权限不够");
            /*把对象转化成字符串*/
            String res = new ObjectMapper().writeValueAsString(ajaxValueRes);
            response.getWriter().print(res);
        }
    }
}
