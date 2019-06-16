<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>年级类别</title>
    <%@include file="/static/common/common.jsp" %>
    <link href="${pageContext.request.contextPath}/static/css/grade.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/grade.js"></script>
</head>
<body class="easyui-layout">
<%--下--%>
<div data-options="region:'south',split:true" style="height:150px;background: #333333">
    <div class="container">
        <h5 style="font-size:13px;line-height:20px">地址：湖南省长沙市天心区经济开发区中意二路678号 邮编：410118 </h5>
        <h5 style="font-size:12px;line-height:20px">COPYRIGHT © HuNan XINHUA COMPUTER COLLEGE ALL RIGHTS RESERVED. <a
                href="https://www.miitbeian.gov.cn" style="color:#fff">湘ICP备14006741号</a></h5>
        <h5 style="font-size:12px;line-height:20px">版权所有：长沙新华电脑学院</h5>
    </div>
</div>
<%--左--%>
<div data-options="region:'west'" style="width:200px;">
    <div id="grade_accordion" class="easyui-accordion" style="width:300px;height:200px;">
        <c:forEach items="${grades}" var="grade">
            <div title="${grade.graName}" data-options="iconCls:'icon-redo'" data-id="${grade.graNum}"
                 style="overflow:auto;padding:10px;">
                <ul class="tmpconter">
                </ul>
            </div>
        </c:forEach>
    </div>
</div>
<%--中--%>
<div data-options="region:'center'">
    <table id="grade_dg"></table>
</div>
</body>
</html>
