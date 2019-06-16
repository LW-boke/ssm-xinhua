<%--
  Created by IntelliJ IDEA.
  User: aaa
  Date: 2019/3/15
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>老师列表</title>
    <%@include file="/static/common/common.jsp" %>
    <link href="${pageContext.request.contextPath}/static/css/teacher.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/js/teacher.js"></script>
</head>
<body class="easyui-layout">
<%--上部--%>
<div data-options="region:'north'" style="height: 50px;background: #cacaca;overflow: hidden">
    <div id="menu">
        <ul>
            <li><a href="#" class="elect">全部</a></li>
            <c:forEach items="${professionList}" var="pro">
                <li><a href="#" data-id="${pro.proId}">${pro.proName}</a></li>
            </c:forEach>
            <li class="last_li">
                <input type="text" class="" placeholder="请输入老师的名称">
                <button id="find_teacher" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找老师
                </button>
                <button id="find_teacher2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">
                    刷新数据
                </button>
            </li>
        </ul>
    </div>
</div>
<%--左部--%>
<div data-options="region:'west'" style="width:150px;background: rgba(22,0,36,0.06)">
    <ul id="leftMenu">
        <shiro:hasPermission name="addTeacher">
            <li>
                <a id="add_teacher" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加老师</a>
            </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="editTeacher">
            <li>
                <a id="edit_teacher" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑老师</a>
            </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="deleTeacher">
            <li>
                <a id="del_teacher" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除老师</a>
            </li>
        </shiro:hasPermission>
        <li>
            <a id="show_course" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查看课程</a>
        </li>
    </ul>
</div>
<%--下部--%>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <table id="teacher_dg">
    </table>
</div>
<%--增改模板--%>
<div id="dia_teacher">
    <form method="post" id="teacher_form">
        <table align="center" style="border-spacing: 0 20px">
            <input type="hidden" name="teaId">
            <tr>
                <td>老师名称:</td>
                <td><input type="text" name="teaName" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    <label>
                        <input type="radio" name="teaSex" value="1" checked> 男 &nbsp;&nbsp;&nbsp;
                    </label>
                    <label>
                        <input type="radio" value="0" name="teaSex"> 女
                    </label>
                </td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input type="text" name="teaAge" class="easyui-datebox" required="required"/></td>
            </tr>
            <tr>
                <td>入校时间:</td>
                <td><input type="text" name="teaEnrol" class="easyui-datebox" required="required"/></td>
            </tr>
            <tr>
                <td>联系方式:</td>
                <td>
                    <input type="text" name="teaPhone">
                </td>
            </tr>
            <tr>
                <td>家庭地址:</td>
                <td>
                    <input type="text" name="teaSite" placeholder="请输入家庭地址"/>
                </td>
            </tr>
            <tr>
                <td>专业名称:</td>
                <td>
                    <input type="text" name="profession.proId" placeholder="请选择专业名称" id="pro_com"/>
                </td>
            </tr>
            <tr>
                <td>老师简介:</td>
                <td>
                    <textarea rows="4" role="10" name="teaAbout"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--查看课程面板--%>
<div id="win_course">
    <table id="dg_course">

    </table>
</div>
</body>

</html>
