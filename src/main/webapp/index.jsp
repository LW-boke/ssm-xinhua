<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>学生管理系统</title>
    <%@include file="/static/common/common.jsp" %>
    <link href="static/css/index.css" rel="stylesheet" type="text/css"/>
    <script src="static/js/index.js"></script>
</head>
<body class="easyui-layout">
<%--上部--%>
<div data-options="region:'north',split:true,border:false" style="height:90px;">
    <div class="head-left">
        <img src="static/images/logon.png" alt="">
    </div>
    <div class="head-right">
        <span>Hi <font color="red"><shiro:principal property="name"/></font> </span>
        <a href="/logOut">注销登录</a>
    </div>
</div>

<%--左部--%>
<div data-options="region:'west',split:true" style="width:200px;">
    <%--分类--%>
    <div id="accordion" class="easyui-accordion" style="width:300px;height:200px;">
        <div title="学校系统" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
            <ul id="tree_student"></ul>
        </div>

        <shiro:hasAnyRoles name="校长,副校长">
            <div title="其他" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
                <ul id="tree_else"></ul>
            </div>
        </shiro:hasAnyRoles>
    </div>

</div>
<%--中部--%>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <%--选项卡--%>
    <div id="tabs" class="easyui-tabs" style="width:500px;height:250px;">

    </div>

</div>
</body>


</html>
