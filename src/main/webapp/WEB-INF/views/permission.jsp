<%--
  Created by IntelliJ IDEA.
  User: aaa
  Date: 2019/3/19
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限管理</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/static/js/permission.js"></script>
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
<table id="dg_permission"></table>
<div id="btn_user">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-left: 10px"
       id="add_permission">添加权限</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="edit_Permission">编辑权限</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="del_Permission">删除权限</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" id="flush_Permission">重新刷新</a>
    <input type="text" name="keyword" class="keywordCla" placeholder="请输入权限的名称"
           style="margin-left: 200px;outline: none">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="find_Permission">查找权限</a>
</div>
<%--增改面板--%>
<div id="dialog_permission">
    <form method="post" id="permission_Form">
        <table align="center" style="border-spacing: 0 15px">
            <input type="hidden" name="pId">
            <tr>
                <td>权限名称:</td>
                <td><input type="text" name="pName" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr id="password_tr">
                <td>权限路径:</td>
                <td><input type="text" name="pResource"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
