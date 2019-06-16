$(function () {
    /*班级数据加载*/
    $("#datagrid_class").datagrid({
        url: '/clazzPage',
        width: "auto",
        columns: [[
            {field: 'claName', title: '班级名称', width: 100, align: 'center'},
            {field: 'claOpenDate', title: '开班时间', width: 100, align: 'center'},
            {field: 'claNumber', title: '应有人数', width: 100, align: 'center'},
            {field: 'refNumber', title: '已有人数', width: 100, align: 'center'},
            {
                field: 'grade',
                title: '课程归属',
                width: 100,
                align: 'center',
                formatter: function (value) {
                    return value.graName ? value.graName : '';
                }
            },
            {
                field: 'teacher', title: '班主任', width: 100, align: 'center', formatter: function (value) {
                    if (value) {
                        return value.teaName ? value.teaName : '';
                    }

                }
            }
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        pagination: true, //分页工具
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        toolbar: "#btn_class"
    });
    /*添加班级窗口*/
    $("#dialog_addCls").dialog({
        width: 300,
        height: 350,
        modal: true,
        closed: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                var claId = $("[name='claId']").val();
                var url;
                if (claId != '' && claId != null) {
                    url = "/editClass";
                } else {
                    url = "/addClass";
                }
                $("#class_Form").form("submit", {
                    url: url,
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            $("#datagrid_class").datagrid("reload");
                            $.messager.alert("提示", data.mrg);
                            /*关闭窗口*/
                            $("#dialog_addCls").dialog("close");
                            /*刷新内容*/
                            $("#dialog_addCls").dialog('reload');

                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    }
                })
            }
        }, {
            iconCls: 'icon-help',
            text: '关闭',
            handler: function () {
                $("#dialog_addCls").dialog("close");
            }
        }]
    });
    /*课程归属下拉框*/
    $("#gra_Com").combobox({
        url: '/getGrades',
        panelHeight: "auto",
        valueField: 'graNum',
        textField: 'graName',
        onLoadSuccess: function () { // 在加载远程数据成功的时候触发。
            $("#gra_Com").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*班主任属下拉框*/
    $("#tea_Com").combobox({
        url: '/getTeachers',
        panelHeight: "auto",
        valueField: 'teaId',
        textField: 'teaName',
        onLoadSuccess: function () { // 在加载远程数据成功的时候触发。
            $("#tea_Com").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*添加班级按钮*/
    $("#add_Class").click(function () {
        $("#class_Form").form("clear");
        $("#dialog_addCls").dialog("open");
        $("#dialog_addCls").dialog("setTitle", "添加班级");

    });
    /*编辑班级按钮*/
    $("#edit_Class").click(function () {
        var rowData = $("#datagrid_class").datagrid("getSelected");
        if (rowData) {
            /*注意点 下拉框的值是 id主键 而不是直接值 否则会报错*/
            rowData["grade.graNum"] = rowData.grade.graNum;
            rowData["teacher.teaId"] = rowData.teacher.teaId;
            $("#dialog_addCls").dialog("open");
            $("#dialog_addCls").dialog("setTitle", "编辑按钮");
            $("#class_Form").form("load", rowData);
        } else {
            $.messager.alert("提示", "请先选择班级进行编辑");
        }
    });
    /*删除按钮*/
    $("#del_Class").click(function () {
        var rowData = $("#datagrid_class").datagrid("getSelected");
        if (rowData) {
            $.messager.confirm("确认", "确认要删除此班级吗？ 删除即将不可恢复", function (res) {
                if (res) {
                    $.get("delClass/" + rowData.claId, function (data) {
                        if (data.success) {
                            $.messager.alert("提示", data.mrg);
                            /*重新加载数据*/
                            $("#datagrid_class").datagrid("reload");
                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    })
                }
            })
        } else {
            $.messager.alert("提示", "请先选择一个班级在进行删除");
        }
    });
    /*查看课程*/
    $("#win_cou").window({
        title: '课程列表',
        width: 800,
        height: 400,
        closed: true,
        cache: false,
        modal: true,
        buttons: [{
            text: '确认',
            iconCls: 'icon-save',
            handler: function () {
                $("#win_cou").dialog("close");
            }
        }]
    });
    /*查看课程按钮*/
    $("#show_course").click(function () {
        var rows = $("#datagrid_class").datagrid("getSelected");
        if (rows) {
            var clzId = rows.claId;
            $("#win_cou").dialog('open');
            /* 课程已有表格*/
            $("#dialog_cou_data").datagrid({
                url: '/getClassCourseById?id=' + clzId,
                columns: [[
                    {field: 'couName', title: '课程名称', width: 30, align: 'center'},
                    {field: 'couOpenDate', title: '开课时间', width: 40, align: 'center'},
                    {field: 'couContent', title: '课程介绍', width: 200, align: 'center'},
                    {
                        field: 'teacher',
                        title: '教课教师',
                        width: 30,
                        align: 'center',
                        formatter: function (value) {
                            return value ? value.teaName : "";
                        }
                    }
                ]],
                fitColumns: true,
                striped: true, // 显示斑马线效果.
                rownumbers: true,
                singleSelect: true
            })
        } else {
            $.messager.alert("提示", "请先选择一个班级在进行查看课程");
        }
    });
    /*查看班级的学生按钮*/
    $("#show_student").click(function () {
        var rows = $("#datagrid_class").datagrid("getSelected");
        if (rows) {
            $("#dialog_student").dialog("open");
            $("#dialog_student_data").datagrid({
                url: '/getStudentByClsId?id=' + rows.claId,
                columns: [[
                    {field: 'stuName', title: '学生姓名', width: 100, align: 'center'},
                    {field: 'stuAge', title: '年龄', width: 100, align: 'center'},
                    {
                        field: 'stuSex', title: '性别', width: 100, align: 'center', formatter: function (value) {
                            if (value) {
                                return "男";
                            } else {
                                return "女";
                            }
                        }
                    },
                    {field: 'stuPhone', title: '手机号码', width: 100, align: 'center'},
                    {field: 'stuEnrol', title: '入学日期', width: 100, align: 'center'}
                ]],
                fitColumns: true,
                striped: true, // 显示斑马线效果.
                pagination: true, //分页工具
                rownumbers: true,
                singleSelect: true,
                fit: true//适用父盒子的宽高,
            });
        } else {
            $.messager.alert("提示", "请先选择一个班级在进行查看学生");
        }
    });
    /*查看班级的学生*/
    $("#dialog_student").dialog({
        title: '学生列表',
        width: 800,
        height: 600,
        closed: true,
        cache: false,
        modal: true
    });
    /*刷新按钮*/
    $("#flush").click(function () {
        /*把查询框清空*/
        $(".keywordCla").val("");
        /*重新加载 参数设置无*/
        $("#datagrid_class").datagrid("load", {});
    });
    /*查找班级按钮*/
    $("#findClas").click(function () {
        /*获取要查询的内容*/
        var keyword = $(".keywordCla").val();
        if (keyword != '' && keyword != null) {
            /*查询加载数据 并且传一个参数*/
            $("#datagrid_class").datagrid("load", {keyword: keyword});
        } else {
            $.messager.alert("提示", "请先输入要查询的班级名称");
        }
    });
    /*窗口大小*/
    $(".panel-tool-max").click(function () {
        /*判断是不是按的班级课程按钮*/
        var row = $("#datagrid_class").datagrid("getSelected");
        /*当扩大的时候 进行自定义*/
        if (row) {
            $("#dialog_cou_data").datagrid("resize");
        }
    });
});