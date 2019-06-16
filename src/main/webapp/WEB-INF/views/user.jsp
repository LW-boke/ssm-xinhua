<%--
  Created by IntelliJ IDEA.
  User: aaa
  Date: 2019/3/19
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/static/js/user.js"></script>
    <style>
        .keywordCla {
            margin-left: 100px;
            height: 35px;
            margin-top: 10px;
            width: 200px;
            outline: none;
        }
    </style>
</head>
<body>
<table id="dg_user"></table>
<div id="btn_user">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-left: 10px"
       id="add_user">添加班级</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="edit_user">编辑班级</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="del_user">删除班级</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" id="flush_user">重新刷新</a>
    <input type="text" name="keyword" class="keywordCla" placeholder="请输入用户账号或者姓名"
           style="margin-left: 200px;outline: none">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="find_user">查找班级</a>
</div>
<%--增改面板--%>
<div id="dialog_user">
    <form method="post" id="user_Form">
        <table align="center" style="border-spacing: 0 15px">
            <input type="hidden" name="id">
            <tr>
                <td>账号:</td>
                <td><input type="text" name="username" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr id="password_tr">
                <td>密码:</td>
                <td><input type="text" name="password"/></td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>角色:</td>
                <td>
                    <input type="text" id="role_Com" name="roles.rId" placeholder="请选择角色"/>
                </td>
            </tr>
            <tr>
                <td>是否管理员:</td>
                <td>
                    <label>
                        <input type="radio" value="1" name="admin"/>是
                    </label>
                    <label>
                        <input type="radio" value="0" name="admin" checked />否
                    </label>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
