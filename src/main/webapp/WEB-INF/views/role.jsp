<%--
  Created by IntelliJ IDEA.
  User: aaa
  Date: 2019/3/19
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/static/js/role.js"></script>
    <style>
        .ul_role {
            margin: 100px auto;
            padding: 0;
            overflow: hidden;
        }

        .ul_role li {
            line-height: 60px;
            list-style: none;
            text-align: left;
            background: url("/static/images/icon-arrow-right.png") no-repeat right;
            margin-top: 10px;
        }

        .ul_role li a {
            font-size: 16px;
            text-decoration: none;
            color: #fcfcfc;
            margin-left: 20px;
            display: block;
            width: 100%;
        }

        .ul_role li a:hover {
            text-decoration: underline;
        }

        .ul_role li .elect {
            color: red;
        }

        .dg_left {
            width: 50%;
            height: 100%;
            float: left;
        }

        .dg_left h3 {
            text-align: center;
            font-size: 16px;
        }

        .menu_top a {
            margin-left: 40px;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:80px">
    <div style="margin-top: 20px" class="menu_top">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-left: 180px"
           id="add_Role">添加角色</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="edit_Role">编辑角色</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="del_Role">删除角色</a>
    </div>
</div>
<div data-options="region:'center'">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west'" style="width:180px;background: #333333">
            <ul class="ul_role">
            </ul>
        </div>
        <div data-options="region:'center'">
            <div class="dg_left">
                <h3 style="color: red;">已有的权限</h3>
                <table id="dg_role"></table>
            </div>
            <div class="dg_left">
                <h3 style="color: #333333">未有的权限</h3>
                <table id="dg_role2"></table>
            </div>
        </div>
    </div>
</div>
<div id="role_dialog">
    <form method="post" id="role_Form">
        <table align="center" style="border-spacing: 0 15px">
            <input type="hidden" name="rId">
            <tr>
                <td>角色编号:</td>
                <td><input type="text" name="rNum"/></td>
            </tr>
            <tr id="password_tr">
                <td>角色名称:</td>
                <td><input type="text" name="rName" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
