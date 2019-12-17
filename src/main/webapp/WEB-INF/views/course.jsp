<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程列表</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/static/js/course.js"></script>
    <style>
        .keywordCla {
            margin-left: 100px;
            height: 35px;
            margin-top: 10px;
            width: 200px;
            outline: none;
            padding-left: 5px;
            border-radius: 4px;
            border: 1px solid #e3e3e3;
        }

        .win_course_left {
            width: 50%;
            height: 100%;
            float: left;
        }

        .win_course_right {
            width: 25%;
            height: 100%;
            float: left;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <table id="course_dg"></table>

    <div id="btn_course">
        <shiro:hasPermission name="addCourse">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="add_course">添加课程</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="editCourse">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="edit_course">编辑课程</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="deleCourse">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="del_course">删除课程</a>
        </shiro:hasPermission>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" id="flush_course">重新刷新</a>
        <input type="text" name="keyword" placeholder="请输入要查找的课程名称" class="keywordCla">
        <a href="#" class="easyui-linkbutton " data-options="iconCls:'icon-search'" id="find_course">查找课程</a>

        <shiro:hasPermission name="addAndeditClassCourse">
            <a href="#" class="easyui-linkbutton " data-options="iconCls:'icon-edit'"
               id="add_editClassCourse" style="margin-left: 100px">添加或编辑班级课程</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="addAndeditCourseTeacher">
            <a href="#" class="easyui-linkbutton " data-options="iconCls:'icon-edit'"
               id="add_editCourseTeacher">添加或编辑课程老师</a>
        </shiro:hasPermission>

    </div>
</div>

<%--添加和删除课程面板--%>
<div id="dialog_course">
    <form method="post" id="course_Form">
        <table align="center" style="border-spacing: 0 15px">
            <input type="hidden" name="couId">
            <tr>
                <td>课程名称:</td>
                <td>
                    <input type="text" name="couName" class="easyui-validatebox" data-options="required:true"
                           style="padding-left: 8px"/>
                </td>
            </tr>
            <tr>
                <td>开课时间:</td>
                <td>
                    <input type="text" name="couOpenDate" class="easyui-datebox" required="required"/>
                </td>
            </tr>
            <tr>
                <td>课程归属:</td>
                <td>
                    <input type="text" id="com_teacher" name="profession.proId" placeholder="请选择课程归属"/>
                </td>
            </tr>
            <tr>
                <td>课程介绍:</td>
                <td>
                    <textarea rows="5" cols="30" name="couContent" style="resize: none;outline: none"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--添加编辑班级课程窗口--%>
<div id="dia_course">
    <div class="win_course_left">
        <table id="dg1"></table>
    </div>
    <div class="win_course_left">
        <table id="dg2"></table>
    </div>
</div>

<div id="dia_course2">
    <div class="win_course_right">
        <table id="dg3"></table>
    </div>
    <div class="win_course_right">
        <table id="dg4"></table>
    </div>
    <div class="win_course_right">
        <table id="dg5"></table>
    </div>
    <div class="win_course_right">
        <table id="dg6"></table>
    </div>
</div>
</body>
</html>
