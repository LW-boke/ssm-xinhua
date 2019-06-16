$(function () {
    /*学生数据加载*/
    $("#student_dg").datagrid({
        url: '/getStudents',
        width: "auto",
        columns: [[
            {field: 'stuName', title: '学生名称', width: 50, align: 'center'},
            {
                field: 'stuSex', title: '性别', width: 50, align: 'center', formatter: function (value) {
                    return value ? "男" : "女";
                }
            },
            {
                field: 'stuAge', title: '年龄', width: 50, align: 'center', formatter: function (value) {
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
            {field: 'stuEnrol', title: '入校时间', width: 80, align: 'center'},
            {field: 'stuPhone', title: '联系方式', width: 80, align: 'center'},
            {field: 'stuSite', title: '家庭地址', width: 300, align: 'center'},
            {
                field: 'profession', title: '专业名称', width: 100, align: 'center', formatter: function (value) {
                    return value ? value.proName : "";
                }
            },
            {
                field: 'clazz', title: '所在班级', width: 100, align: 'center', formatter: function (value) {
                    return value ? value.claName : "";
                }
            },
            {
                field: 'stuProgress', title: '是否在校', width: 100, align: 'center', formatter: function (value) {
                    return value ? "在校" : "离校 ";
                }
            }
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        pagination: true, //分页工具
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        toolbar: "#btn_student"
    });
    /*查询学生按钮*/
    $("#findStudent").click(function () {
        /*获取搜索框的值*/
        var keyword = $(".keywordCla").val();
        if ($.trim(keyword).length > 0) {
            $("#student_dg").datagrid("load", {keyword: keyword});
        } else {
            $.messager.alert("提示", "请先输入学生的名称在进行查询");
        }
    });
    /*添加学生按钮*/
    $("#add_student").click(function () {
        $("#dia_student").dialog({
            title: "添加学生",
            closed: false
        });
    });
    /*编辑学生按钮*/
    $("#edit_student").click(function () {
        var rowData = $("#student_dg").datagrid("getSelected");
        if (rowData) {
            $("#dia_student").dialog({
                title: "更改学生",
                closed: false
            });
            /*判断是否男生  true为男生 false为女生*/
            if (rowData.stuSex) {
                rowData.stuSex = 1;
            } else {
                rowData.stuSex = 0;
            }
            /*判断是否在职  true为在职 false为离职*/
            if (rowData.stuProgress) {
                rowData.stuProgress = 1;
            } else {
                rowData.stuProgress = 0;
            }
            if (rowData.profession) {
                rowData["profession.proId"] = rowData.profession.proId;
            }
            if (rowData.clazz) {
                rowData["clazz.claId"] = rowData.clazz.claId;
            }
            /*回显表单*/
            $("#student_form").form("load", rowData);
        } else {
            $.messager.alert("提示", "请先选择一行学生在进行编辑");
        }

    });
    /*删除学生按钮*/
    $("#del_student").click(function () {
        var rowData = $("#student_dg").datagrid("getSelected");
        if (rowData) {
            $.messager.confirm("确认", "确认要删除此学生吗？？？删除之后不可恢复!!!", function (res) {
                if (res) {
                    $.get("/delStudent/" + rowData.stuId, function (data) {
                        if (data.success) {
                            /*重新加载*/
                            $("#student_dg").datagrid("reload");
                            $.messager.alert("提示", data.mrg);
                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    });
                }
            })
        } else {
            $.messager.alert("提示", "请先选择一行学生在进行删除");
        }
    });
    /*重新刷新按钮*/
    $("#flush_student").click(function () {
        $(".keywordCla").val("");
        $("#student_dg").datagrid("load", {});
    });
    // 增改模板
    $("#dia_student").dialog({
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
                    url = "/editStudent";
                } else {
                    url = "/addStudent";
                }
                $("#student_form").form("submit", {
                    url: url,
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            /*重新加载*/
                            $("#student_dg").datagrid("reload");
                            /*关闭对话框*/
                            $("#dia_student").dialog("close");
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
                $("#dia_student").dialog("close");
            }
        }]
    });
    /*专业下拉框*/
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
        },
        onChange: function (newValue, oldValue) {
            /*重新加载班级数据下拉框*/
            $('#cla_cc').combogrid('grid').datagrid('reload', {id: newValue});
        }
    });
    /*班级数据下拉框*/
    $("#cla_cc").combogrid({
        panelWidth: 500,
        idField: 'claId',
        textField: 'claName',
        url: "/getClassWidByProId",
        columns: [[
            {field: 'claName', title: '班级名称', width: 100, align: "center"},
            {field: 'claOpenDate', title: '开班时间', width: 80, align: "center"},
            {field: 'claNumber', title: '应有人数', width: 50, align: "center"},
            {field: 'refNumber', title: '已有人数', width: 50, align: "center"},
            {
                field: 'teacher', title: '班主任', width: 60, align: "center", formatter: function (value) {
                    return value ? value.teaName : "";
                }
            }
        ]],
        fitColumns: true,
        onLoadSuccess: function () { // 在加载远程数据成功的时候触发。
            $("#cla_cc").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*左导航的点击*/
    /*获取所有的a标签*/
    var arrA = $(".menu_left").find("a");
    arrA.click(function () {
        /*获取年级的编号*/
        var num = $(this).data("id");
        /*添加样式*/
        for (var i = 0; i < arrA.length; i++) {
            $(arrA).eq(i).removeClass("elect");
        }
        $(this).addClass("elect");
        $("#student_dg").datagrid("load", {id: num});
    });

    /*下载学生的Excel*/
    $("#down_studentExcel").click(function () {
        window.open("/downloadStudentExcel");
    });

    /*下载学生上传Excel的模板*/
    $("#downloadTml").click(function () {
        window.open("/downloadStudentExcelTml")
    });

    /*上传Excel对话框*/
    $('#dialog_excel').dialog({
        title: '上传学生',
        width: 400,
        height: 200,
        closed: true,
        modal: true,
        buttons: [{
            text: '上传',
            handler: function () {
                $("#upload_studentExcel").form("submit", {
                    url: "/uplodaStudentExcel",
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            /*关闭对话框*/
                            $("#dialog_excel").dialog("close");
                            /*重新加载数据*/
                            $("#student_dg").datagrid("reload");
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
                /*关闭对话框*/
                $("#dialog_excel").dialog("close");
            }
        }]
    });
    /*上传Excel按钮*/
    $("#updateExcel").click(function () {
        $("#dialog_excel").dialog("open");
    });

});