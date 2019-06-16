<%--
  Created by IntelliJ IDEA.
  User: aaa
  Date: 2019/3/20
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <%@include file="/static/common/common.jsp" %>
    <link href="static/css/login.css" rel="stylesheet" type="text/css"/>
    <script>
        $(function () {
            $("[type='button']").click(function () {
                $("#login_form").form("submit", {
                    url: "/login",
                    success: function (data) {
                        console.log(data);
                        data = JSON.parse(data);
                        /*登录成功*/
                        if (data.success) {
                            alert(data.mrg);
                            window.location.href = "index.jsp";
                        } else {
                            alert(data.mrg);
                        }
                    }
                })
            });
        });

    </script>
</head>
<body>
<div class="head">
    <img src="static/images/logon.png" alt="">
    <span>学生后台管理系统</span>
    <h3>欢迎登录</h3>
</div>
<div class="content">
    <div class="content_login">
        <form class="loginForm" method="post" id="login_form">
            <h3>账户登录</h3>
            <div class="itme">
                <label>
                    <input type="text" placeholder="请输入您的账号" name="username" class="easyui-validatebox"
                           data-options="required:true" value="123">
                </label>
            </div>
            <div class="itme">
                <label style="background:url('/static/images/password.png')no-repeat center rgba(51, 51, 51, 0.43) ">
                    <input type="password" placeholder="请输入您的密码" name="password" value="123">
                </label>
            </div>
            <div class="itme" style="border: 0">
                <input type="button" value="登   录"
                       style="width: 100%;left: 0;background: #e4393c;color: #fff;border: 2px solid #e85356;cursor:pointer "/>
            </div>
        </form>
    </div>
</div>

<div class="buttom">
    <div style="width:100%;height:60px;background:#333333;color:#fff;text-align:center;padding:10px 0">
        <div class="container">
            <h5 style="font-size:13px;line-height:20px">地址：湖南省长沙市天心区经济开发区中意二路678号 邮编：410118 </h5>
            <h5 style="font-size:12px;line-height:20px">COPYRIGHT © HuNan XINHUA COMPUTER COLLEGE ALL RIGHTS RESERVED.
                <a href="https://www.miitbeian.gov.cn" style="color:#fff">湘ICP备14006741号</a></h5>
            <h5 style="font-size:12px;line-height:20px">版权所有：长沙新华电脑学院</h5>
        </div>

        <div style="width:100%;margin:0 auto; padding:8px 0;background:rgba(51,51,51,0)">
            <a target="_blank" href="https://www.beian.gov.cn/portal/registerSystemInfo?recordcode=43010302000580"
               style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img
                    src="${pageContext.request.contextPath}/static/images/ba.png" style="float:left;">
                <p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">湘公网安备
                    43010302000580号</p></a>
        </div>

    </div>
</div>
</body>
</html>
