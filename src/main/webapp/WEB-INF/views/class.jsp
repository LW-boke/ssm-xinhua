<%--
  Created by IntelliJ IDEA.
  User: aaa
  Date: 2019/3/14
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/static/js/class.js"></script>
    <style>
        .keywordCla {
            margin-left: 100px;
            height: 35px;
            margin-top: 10px;
            width: 200px;
            outline: none;
            border-radius: 4px;
            border: 1px solid #e3e3e3;
        }

        #dialog_addCls {
            font-size: 16px;
            text-align: center;
        }

        input {
            outline: none;
        }

    </style>
</head>
<body>
<table id="datagrid_class"></table>
<div id="btn_class">
    <shiro:hasPermission name="addClass">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-left: 10px" id="add_Class">添加班级</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="editCalss">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="edit_Class">编辑班级</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="deleCalss">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="del_Class">删除班级</a>
    </shiro:hasPermission>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" id="flush">重新刷新</a>
    <input type="text" name="keyword" class="keywordCla" placeholder="请输入班级的名称">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="findClas">查找班级</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="margin-left: 100px"
       id="show_course">查看班级课程</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="show_student">查看班级学生</a>
</div>
<%--添加班级--%>
<div id="dialog_addCls">
    <form method="post" id="class_Form">
        <table align="center" style="border-spacing: 0 15px">
            <input type="hidden" name="claId">
            <tr>
                <td>班级名称:</td>
                <td><input type="text" name="claName" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>应有人数:</td>
                <td><input type="text" name="claNumber"/></td>
            </tr>
            <tr>
                <td>开班时间:</td>
                <td><input type="text" name="claOpenDate" class="easyui-datebox" required="required"/></td>
            </tr>
            <tr>
                <td>课程归属:</td>
                <td>
                    <input type="text" id="gra_Com" name="grade.graNum" placeholder="请选择课程归属"/>
                </td>
            </tr>
            <tr>
                <td>班主任:</td>
                <td>
                    <input type="text" id="tea_Com" name="teacher.teaId" placeholder="请选择班主任"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--查看班级课程--%>
<div id="win_cou">
    <table id="dialog_cou_data"></table>
</div>
<%--查看班级学生--%>
<div id="dialog_student">
    <table id="dialog_student_data"></table>
</div>
</body>
</html>
