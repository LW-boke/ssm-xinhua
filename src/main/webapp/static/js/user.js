$(function () {
    /*用户管理员数据加载*/
    $("#dg_user").datagrid({
        url: '/getArrUsers',
        width: "auto",
        columns: [[
            {field: 'username', title: '用户账号', width: 100, align: 'center'},
            {field: 'name', title: '姓名', width: 100, align: 'center'},
            {
                field: 'roles', title: '角色', width: 100, align: 'center', formatter: function (value) {
                    return value.rname ? value.rname : "";
                }
            },
            {
                field: 'admin', title: '是否管理员', width: 100, align: 'center', formatter: function (value) {
                    return value ? "是" : "否";
                }
            }
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        pagination: true, //分页工具
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        toolbar: "#btn_user"
    });
    /*添加用户按钮*/
    $("#add_user").click(function () {
        /*清空表单数据*/
        $("#user_Form").form("clear");
        /*显示密码框*/
        $("#password_tr").show();
        $("#dialog_user").dialog({
            title: "增加用户账号",
            closed: false
        })
    });
    /*编辑用户按钮*/
    $("#edit_user").click(function () {
        var rowData = $("#dg_user").datagrid("getSelected");
        if (rowData) {
            $("#dialog_user").dialog({
                title: "编辑用户账号",
                closed: false
            });
            $("#password_tr").hide();
            /*设置回显数据*/
            if (rowData.admin) {
                rowData.admin = 1;
            } else {
                rowData.admin = 0;
            }
            if (rowData.roles) {
                rowData["roles.rId"] = rowData.roles.rid;
            }
            $("#user_Form").form("load", rowData);

        } else {
            $.messager.alert("提示", "请先选择一行数据在进行编辑");
        }

    });
    /*删除按钮*/
    $("#del_user").click(function () {
        var rowData = $("#dg_user").datagrid("getSelected");
        if (rowData) {
            $.messager.confirm("确认", "确认要删除此用户账号吗？ 删除即将不可恢复", function (res) {
                if (res) {
                    $.get("delUser/" + rowData.id, function (data) {
                        if (data.success) {
                            $.messager.alert("提示", data.mrg);
                            /*重新加载数据*/
                            $("#dg_user").datagrid("reload");
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
    /*刷新按钮*/
    $("#flush_user").click(function () {
        if (!$(".keywordCla").val()) {
            $.messager.alert("提示", "已经展示所有的内容了。。。。。。。。。。")
        } else {
            $(".keywordCla").val('');
            $("#dg_user").datagrid("load", {});
        }

    });
    /*查找用户按钮*/
    $("#find_user").click(function () {
        /*获取要查询的内容*/
        var keyword = $(".keywordCla").val();
        if (keyword != '' && keyword != null) {
            /*查询加载数据 并且传一个参数*/
            $("#dg_user").datagrid("load", {keyword: keyword});
        } else {
            $.messager.alert("提示", "请先输入要查询的用户账号或者姓名");
        }
    });
    // 增改面板
    $("#dialog_user").dialog({
        width: 300,
        height: 350,
        modal: true,
        closed: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                var claId = $("[name='id']").val();
                var url;
                if (claId != '' && claId != null) {
                    url = "/editUser";
                } else {
                    url = "/addUser";
                }
                $("#user_Form").form("submit", {
                    url: url,
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            /*刷新内容*/
                            $("#dg_user").datagrid("reload");
                            /*关闭窗口*/
                            $("#dialog_user").dialog("close");
                            $.messager.alert("提示", data.mrg);
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
                $("#dialog_user").dialog("close");
            }
        }]
    });
    /*角色下拉框*/
    $("#role_Com").combobox({
        url: '/getRole',
        panelHeight: "auto",
        valueField: 'rid',
        textField: 'rname',
        onLoadSuccess: function () { // 在加载远程数据成功的时候触发。
            $("#role_Com").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

});