$(function () {
    /*加载老师数据*/
    $("#teacher_dg").datagrid({
        url: '/teachersPage',
        width: "auto",
        columns: [[
            {field: 'teaName', title: '老师名称', width: 50, align: 'center'},
            {
                field: 'teaSex', title: '性别', width: 50, align: 'center', formatter: function (value) {
                    if (value) {
                        return "男";
                    } else {
                        return "女";
                    }
                }
            },
            {
                field: 'teaAge', title: '年龄', width: 50, align: 'center', formatter: function (value) {
                    if (value) {
                        /*获取当前时间*/
                        var date = new Date();
                        /*获取当前年份*/
                        var year = date.getFullYear();
                        /*获取字符串中第一次出现的数的索引*/
                        var i = value.indexOf("-");
                        /*截取第几个到第几个的字符串*/
                        var bgYear = value.substr(0, i); // 获取年份
                        /*转成数字类型*/
                        bgYear = parseInt(bgYear);
                        return year - bgYear;
                    } else {
                    }
                    return "";
                }
            },
            {field: 'teaEnrol', title: '入校时间', width: 50, align: 'center'},
            {field: 'teaPhone', title: '联系方式', width: 100, align: 'center'},
            {field: 'teaSite', title: '联系地址', width: 100, align: 'center'},
            {
                field: 'profession', title: '教学课程', width: 50, align: 'center', formatter: function (value) {
                    return value ? value.proName : "";
                }
            },
            {field: 'teaAbout', title: '老师简介', width: 350, align: 'left'}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        pagination: true, //分页工具
        rownumbers: true,
        singleSelect: true,
        fit: true//适用父盒子的宽高,
    });
    /*获取所有导航的a标签*/
    var $As = $("#menu").find("a");
    $As.click(function () {
        /*添加样式*/
        for (var i = 0; i < $As.length; i++) {
            if (this == $As[i]) {
                $($As).eq(i).addClass("elect");
            } else {
                $($As).eq(i).removeClass("elect");
            }
        }
        var id = $(this).data("id");
        if (id) {
            $("#teacher_dg").datagrid("load", {id: id});
        }
        /*判断点击的是不是全部*/
        if (this == $As[0]) {
            $("#teacher_dg").datagrid("load", {});
        }
    });
    /*查找老师*/
    $("#find_teacher").click(function () {
        var keyword = $(".last_li>input").val();
        var id = $(".elect").data("id");
        if (keyword) {
            if (id) {
                $("#teacher_dg").datagrid("load", {keyword: keyword, id: id});
            } else {
                $("#teacher_dg").datagrid("load", {keyword: keyword});
            }
        } else {
            $.messager.alert("提示", "请先输入要查询的名称");
        }
    });
    // 增改模板
    $("#dia_teacher").dialog({
        width: 400,
        height: 550,
        closed: true,
        cache: false,
        modal: true,
        buttons: [{
            text: '保存',
            handler: function () {
                var id = $("[type='hidden']").val();
                var url;
                if (id) {
                    url = "/editTeacher";
                } else {
                    url = "/addTeacher";
                }
                $("#teacher_form").form("submit", {
                    url: url,
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            /*重新加载*/
                            $("#teacher_dg").datagrid("reload");
                            /*关闭对话框*/
                            $("#dia_teacher").dialog("close");
                            $.messager.alert("提示", data.mrg);
                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    }
                })
            }
        }, {
            text: '关闭',
            handler: function () {
                $("#dia_teacher").dialog("close");
            }
        }]
    });
    /*专业下拉列表*/
    $("#pro_com").combobox({
        valueField: 'proId',
        textField: 'proName',
        panelHeight: "auto",
        url: '/getProfessionList',
        onLoadSuccess: function () { // 在加载远程数据成功的时候触发。
            $("#pro_com").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*添加老师*/
    $("#add_teacher").click(function () {
        $("#dia_teacher").dialog({
            title: "添加老师",
            closed: false
        });
    });
    /*编辑老师*/
    $("#edit_teacher").click(function () {
        var rowData = $("#teacher_dg").datagrid("getSelected");
        if (rowData) {
            $("#dia_teacher").dialog({
                title: "编辑老师",
                closed: false
            });
            /*设置回显数据*/
            if (rowData.teaSex) {
                rowData["teaSex"] = 1;
            } else {
                rowData["teaSex"] = 0;
            }
            rowData["profession.proId"] = rowData.profession.proId;
            /*回显数据*/
            $("#teacher_form").form("load", rowData);
        } else {
            $.messager.alert("提示", "请先选择一行数据在进行编辑");
        }
    });
    /*删除老师*/
    $("#del_teacher").click(function () {
        var rowData = $("#teacher_dg").datagrid("getSelected");
        if (rowData) {
            $.messager.confirm("确认", "确认要删除这条数据吗？？？删除之后不可恢复！！！", function (res) {
                console.log(rowData);
                if (res) {
                    $.get("/delTeacher/" + rowData.teaId, function (data) {
                        if (data.success) {
                            /*重新加载*/
                            $("#teacher_dg").datagrid("reload");
                            $.messager.alert("提示", data.mrg);
                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    })
                }
            })
        } else {
            $.messager.alert("提示", "请先选择一行数据在进行删除");
        }
    });
    /*刷新课程按钮*/
    $("#find_teacher2").click(function () {
        $(".last_li>input").val('');
        $("#teacher_dg").datagrid("load",{});
    });
    /*查看课程面板*/
    $("#win_course").window({
        title: "我的课程表",
        width: 800,
        height: 400,
        modal: true,
        closed: true
    });
    /*加载课程数据*/
    $("#dg_course").datagrid({
        url: '/getCourseWidthTeaId',
        width: "auto",
        columns: [[
            {field: 'couName', title: '课程名称', width: 50, align: 'center'},
            {field: 'couOpenDate', title: '开课时间', width: 60, align: 'center'},
            {
                field: 'profession', title: '课程归属', width: 50, align: 'center', formatter: function (value) {
                    return value ? value.proName : "";
                }
            },
            {field: 'couContent', title: '课程介绍', width: 300, align: 'center'}
        ]],
        fitColumns: true,
        pagination: true, //分页工具
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true//适用父盒子的宽高,
    });
    /*查看课程按钮*/
    $("#show_course").click(function () {
        var rowData = $("#teacher_dg").datagrid("getSelected");
        if (rowData) {
            $("#win_course").window("open");
            $("#dg_course").datagrid("load", {id: rowData.teaId})
        }
    });

});