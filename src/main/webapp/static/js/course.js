$(function () {
    /*加载dg数据*/
    $("#course_dg").datagrid({
        url: '/getCourses',
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
        fit: true,//适用父盒子的宽高,
        toolbar: '#btn_course'
    });
    /*删除和添加面板*/
    $("#dialog_course").dialog({
        width: 400,
        height: 400,
        modal: true,
        closed: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                var claId = $("[name='couId']").val();
                var url;
                if (claId != '' && claId != null) {
                    url = "/editCourse";
                } else {
                    url = "/addCourse";
                }
                $("#course_Form").form("submit", {
                    url: url,
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            $("#datagrid_class").datagrid("reload");
                            $.messager.alert("提示", data.mrg);
                            /*关闭窗口*/
                            $("#dialog_course").dialog("close");
                            /*刷新内容*/
                            $("#course_dg").datagrid('reload');
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
    /* /!*教师的下拉菜单*!/
     $("#cc_teacher").combogrid({
         panelWidth: "800px",
         mode: 'remote',
         idField: 'teaId',
         textField: 'teaName',
         url: '/getTeachers',
         columns: [[
             {field: 'teaName', title: '老师名称', width: 30, align: 'center'},
             {
                 field: 'teaSex', title: '性别', width: 30, align: 'center', formatter: function (value) {
                     return value ? "男" : "女";
                 }
             },
             {
                 field: 'teaAge', title: '年龄', width: 40, align: 'center', formatter: function (value) {
                     if (value) {
                         /!*获取当前时间*!/
                         var date = new Date();
                         /!*获取当前年份*!/
                         var year = date.getFullYear();
                         /!*获取字符串中第一次出现的数的索引*!/
                         var i = value.indexOf("-");
                         /!*截取第几个到第几个的字符串*!/
                         var bgYear = value.substr(0, i); // 获取年份
                         /!*转成数字类型*!/
                         bgYear = parseInt(bgYear);
                         return year - bgYear;
                     } else {
                     }
                     return "";
                 }
             },
             {field: "teaAbout", title: "老师简介", width: 250, align: 'left'}
         ]],
         fitColumns: true,
         onLoadSuccess: function () { // 在加载远程数据成功的时候触发。
             $("#cc_teacher").each(function (i) {
                 var span = $(this).siblings("span")[i];
                 var targetInput = $(span).find("input:first");
                 if (targetInput) {
                     $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                 }
             });
         }
     });*/
    /*课程归属下拉菜单*/
    $("#com_teacher").combobox({
        url: '/getProfessionList',
        valueField: 'proId',
        textField: 'proName',
        panelHeight: "auto",
        onLoadSuccess: function () { // 在加载远程数据成功的时候触发。
            $("#com_teacher").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*添加课程按鈕*/
    $("#add_course").click(function () {
        /*清空表单*/
        $("#course_Form").form("clear");
        /*更改标题*/
        $("#dialog_course").dialog("setTitle", "添加课程");
        /*打开模板*/
        $("#dialog_course").dialog("open");
    });
    /*编辑课程按钮*/
    $("#edit_course").click(function () {
        var rowData = $("#course_dg").datagrid("getSelected");
        if (rowData) {
            /*更改标题*/
            $("#dialog_course").dialog("setTitle", "编辑课程");
            /*打开模板*/
            $("#dialog_course").dialog("open");
            if (rowData.profession) {
                rowData["profession.proId"] = rowData.profession.proId;
            }
            $("#course_Form").form("load", rowData);

        } else {
            $.messager.confirm("提示", "请先选择一行课程在进行编辑");
        }
    });
    /*删除课程按钮*/
    $("#del_course").click(function () {
        var rowData = $("#course_dg").datagrid("getSelected");
        if (rowData) {
            $.messager.confirm("确认", "确认要删除此课程吗？？？删除之后不可恢复！！！", function (res) {
                if (res) {
                    $.get("/delCourse/" + rowData.couId, function (data) {
                        if (data.success) {
                            $("#datagrid_class").datagrid("reload");
                            $.messager.alert("提示", data.mrg);
                            /*关闭窗口*/
                            $("#dialog_course").dialog("close");
                            /*刷新内容*/
                            $("#course_dg").datagrid('reload');
                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    });
                }
            });
        } else {
            $.messager.confirm("提示", "请先选择一行课程在进行删除");
        }
    });
    /*查找课程按钮*/
    $("#find_course").click(function () {
        /*获取要查询的内容*/
        var keyword = $(".keywordCla").val();
        if (keyword != '' && keyword != null) {
            /*查询加载数据 并且传一个参数*/
            $("#course_dg").datagrid("load", {keyword: keyword});
        } else {
            $.messager.alert("提示", "请先输入要查询的班级名称");
        }
    });
    /*刷新按钮*/
    $("#flush_course").click(function () {
        /*把查询框清空*/
        $(".keywordCla").val("");
        /*重新加载 参数设置无*/
        $("#course_dg").datagrid("load", {});
    });

    $("#add_editClassCourse").click(function () {
        var rowDate = $("#course_dg").datagrid("getSelected");
        if (rowDate) {
            $("#dia_course").window("open");
            /*获取已有和未有的班级数据*/
            /*根据 课程的id获取已有的班级*/
            $("#dg1").datagrid("load", {id: rowDate.couId});
            /*根据专业id 获取没有的班级*/
            $("#dg2").datagrid("load", {id: rowDate.proId});
        } else {
            $.messager.confirm("提示", "请先选择一门课程在进行添加或编辑");
        }
    });
    /*添加或编辑班级课程的窗口*/
    $('#dia_course').dialog({
        title: "添加或编辑班级课程",
        width: 800,
        height: 500,
        modal: true,
        closed: true,
        buttons: [
            {
                text: '保存',
                iconCls: 'icon-add',
                handler: function () {
                    var rows = $("#dg1").datagrid("getRows");
                    var claIds = [];
                    for (var i = 0; i < rows.length; i++) {
                        claIds[i] = rows[i].claId;
                    }
                    var rowDate = $("#course_dg").datagrid("getSelected");
                    $.post("/saveCouIdAndClaId", {CouId: rowDate.couId, 'ClsId[]': claIds}, function (data) {
                        if (data.success) {
                            $.messager.alert("提示", data.mrg);
                            /*关闭窗口*/
                            $("#dia_course").dialog("close");
                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    })

                }
            }, {
                text: '保存',
                iconCls: 'icon-edit',
                handler: function () {
                    /*关闭窗口*/
                    $("#dia_course").dialog("close");
                }
            }]
    });

    /*已有课程的班级*/
    $("#dg1").datagrid({
        url: '/getClassByCourseId',
        columns: [[
            {field: 'claName', title: "已有课程班级", width: 100, align: "center"}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        onClickRow: function (rowIndex, rowData) {
            /*删除行*/
            $("#dg1").datagrid("deleteRow", rowIndex);
        }
    });

    /*添加课程的班级*/
    $("#dg2").datagrid({
        url: '/getClassWidByProId',
        columns: [[
            {field: 'claName', title: '未有课程班级', width: 100, align: "center"}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        onClickRow: function (rowIndex, rowData) {
            /*获取所有已有的班级*/
            var rows = $("#dg1").datagrid("getRows");
            /*遍历所有已有的班级*/
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].claId == rowData.claId) {
                    $("#dg1").datagrid("selectRow", rowIndex);
                    return;
                }
            }
            /*添加未有的班级*/
            $("#dg1").datagrid('appendRow', rowData);
        }
    });

    /*添加或编辑班级课程的窗口*/
    $('#dia_course2').dialog({
        title: "添加或编辑班级课程",
        width: 1400,
        height: 500,
        modal: true,
        closed: true,
        buttons: [
            {
                text: '保存',
                iconCls: 'icon-add',
                handler: function () {
                    var clssRow = $("#dg3").datagrid("getSelected");
                    var couRow = $("#dg4").datagrid("getSelected");
                    var teaRow = $("#dg6").datagrid("getSelected");
                    $.get("/insertTeacherIdWidthClaAndCouId", {
                        claId: clssRow.claId,
                        couId: couRow.couId,
                        teaId: teaRow.teaId
                    }, function (data) {
                        if (data.success) {
                            $.messager.alert("提示", data.mrg);
                            /*关闭窗口*/
                            $("#dia_course2").dialog("close");
                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    });
                }
            }, {
                text: '保存',
                iconCls: 'icon-edit',
                handler: function () {
                    /*关闭窗口*/
                    $("#dia_course2").dialog("close");
                }
            }]
    });
    var row;
    /*已有课程的班级*/
    $("#dg3").datagrid({
        width: 500,
        url: '/getClassByCourseId',
        columns: [[
            {field: 'claName', title: "班级", width: 100, align: "center"}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        onClickRow: function (rowIndex, rowData) {
            row = rowData;
            $("#dg6").datagrid("load", {proId: row.proId});
            $("#dg4").datagrid("load", {claId: rowData.claId});
        },
        onLoadSuccess: function (data) {
            if (data.total > 0) {
                row = data.rows[0];
                /*默认选择第一行*/
                $("#dg3").datagrid("selectRow", 0);
                $("#dg6").datagrid("load", {proId: row.proId});
                $("#dg4").datagrid("load", {claId: row.claId});
            } else {
                $("#dia_course2").dialog("close");
                $.messager.alert("提示", "请先添加授课班级");
            }
        }
    });
    /*根据班级id 获取课程*/
    $("#dg4").datagrid({
        url: '/getCourseWidthByClsId',
        columns: [[
            {field: 'couName', title: "课程", width: 100, align: "center"}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        onClickRow: function (rowIndex, rowData) {
            $("#dg5").datagrid("load", {ClasId: row.claId, CouId: rowData.couId});
        },
        onLoadSuccess: function (data) {
            if (data.total > 0) {
                /*默认选择第一行*/
                $("#dg4").datagrid("selectRow", 0);
                if (data.rows[0].couId) {
                    $("#dg5").datagrid("load", {ClasId: row.claId, CouId: data.rows[0].couId});
                }
            }
        }
    });
    /*已有课程的班级*/
    $("#dg5").datagrid({
        url: '/getTeacherWidthByClasIdAndCouId',
        columns: [[
            {field: 'teaName', title: "老师", width: 100, align: "center"}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        onClickRow: function (rowIndex, rowData) {
        }
    });
    /*需要选择的班级*/
    $("#dg6").datagrid({
        url: '/getTeacherByProId',
        columns: [[
            {field: 'teaName', title: "请选择要添加的老师", width: 100, align: "center"}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        onClickRow: function (rowIndex, rowData) {
            var rows = $("#dg5").datagrid("getRows");
            if (rows.length != 0) {
                $("#dg5").datagrid("deleteRow", 0);
            }
            if (rows.length == 0) {
                $("#dg5").datagrid("appendRow", rowData);
            }
        }
    });
    /*添加或编辑课程老师按钮*/
    $("#add_editCourseTeacher").click(function () {
        var rowDate = $("#course_dg").datagrid("getSelected");
        if (rowDate) {
            $("#dia_course2").window("open");
            /*获取已有和未有的班级数据*/
            /*根据 课程的id获取已有的班级*/
            $("#dg3").datagrid("load", {id: rowDate.couId});
            $("#dg4").datagrid("load", {proId: rowDate.proId});
            $("#dg5").datagrid("load", {});
        } else {
            $.messager.confirm("提示", "请先选择一门课程在进行添加或编辑");
        }
    });

});


