<%--
  Created by IntelliJ IDEA.
  User: aaa
  Date: 2019/3/18
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>学生列表</title>
    <%@include file="/static/common/common.jsp" %>
    <link href="${pageContext.request.contextPath}/static/css/student.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/student.js"></script>
</head>
<body class="easyui-layout">

<div data-options="region:'north'" style="height:80px; background-color: #333333;">
    <h1 style="color:#fcfcfc;text-align: center"> 学生列表</h1>
</div>

<div data-options="region:'center'">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',collapsed:true" style="width:180px">
            <div class="menu_left">
                <h3>年级列表</h3>
                <ul>
                    <c:forEach items="${grades}" var="gra">
                        <li><a href="#" data-id="${gra.graNum}">${gra.graName}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div data-options="region:'center'">
            <table id="student_dg"></table>
            <div id="btn_student">
                <shiro:hasPermission name="addStudent">
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-left: 10px"
                       id="add_student">
                        添加学生
                    </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="editStudent">
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="edit_student">
                        编辑学生
                    </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="deleStudent">
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="del_student">
                        删除学生
                    </a>
                </shiro:hasPermission>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" id="flush_student">重新刷新</a>
                <input type="text" name="keyword" class="keywordCla" placeholder="请输入学生的名称">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="findStudent">查找学生</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="margin-left: 50px"
                   id="down_studentExcel">下载Excel</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="updateExcel">上传Excel</a>
            </div>
        </div>
    </div>
</div>
<%--增改模板--%>
<div id="dia_student">
    <form method="post" id="student_form">
        <table align="center" style="border-spacing: 0 20px">
            <input type="hidden" name="stuId">
            <tr>
                <td>学生名称:</td>
                <td><input type="text" name="stuName" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    <label>
                        <input type="radio" name="stuSex" value="1" checked> 男 &nbsp;&nbsp;&nbsp;
                    </label>
                    <label>
                        <input type="radio" value="0" name="stuSex"> 女
                    </label>
                </td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input type="text" name="stuAge" class="easyui-datebox" required="required"/></td>
            </tr>
            <tr>
                <td>入校时间:</td>
                <td><input type="text" name="stuEnrol" class="easyui-datebox" required="required"/></td>
            </tr>
            <tr>
                <td>联系方式:</td>
                <td>
                    <input type="text" name="stuPhone">
                </td>
            </tr>
            <tr>
                <td>家庭地址:</td>
                <td>
                    <input type="text" name="stuSite" placeholder="请输入家庭地址"/>
                </td>
            </tr>
            <tr>
                <td>专业名称:</td>
                <td>
                    <input type="text" name="profession.proId" placeholder="请选择专业名称" id="pro_com"/>
                </td>
            </tr>
            <tr>
                <td>所在班级:</td>
                <td>
                    <input type="text" id="cla_cc" name="clazz.claId" placeholder="请选择所在的班级"/>
                </td>
            </tr>
            <tr>
                <td>是否在校:</td>
                <td>
                    <label>
                        <input type="radio" name="stuProgress" value="1" checked> 在校 &nbsp;&nbsp;&nbsp;
                    </label>
                    <label>
                        <input type="radio" name="stuProgress" value="0"> 离校
                    </label>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--上传Excel对话框--%>
<div id="dialog_excel">
    <form method="post" enctype="multipart/form-data" id="upload_studentExcel">
        <tabel>
            <tr>
                <td><input type="file" name="excelFile" style="width: 180px; margin-top: 20px; margin-left: 50px;"></td>
                <td><a href="javascript:void(0);" id="downloadTml">下载模板</a></td>
            </tr>
        </tabel>
    </form>
</div>
</body>
</html>
