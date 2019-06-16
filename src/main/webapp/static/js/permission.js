$(function () {
    /*权限数据加载*/
    $("#dg_permission").datagrid({
        url: 'getPermissions',
        width: "auto",
        columns: [[
            {field: 'pid', title: '权限编号', width: 100, align: 'center'},
            {field: 'pname', title: '权限名称', width: 100, align: 'center'},
            {field: 'presource', title: '权限路径', width: 100, align: 'center'}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        pagination: true, //分页工具
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        toolbar: "#btn_user"
    });
    /*添加权限按钮*/
    $("#add_permission").click(function () {
        /*清空表单数据*/
        $("#permission_Form").form("clear");
        $("#dialog_permission").dialog({
            title: "增加权限",
            closed: false
        })
    });
    /*编辑权限按钮*/
    $("#edit_Permission").click(function () {
        var rowData = $("#dg_permission").datagrid("getSelected");
        if (rowData) {
            $("#dialog_permission").dialog({
                title: "编辑权限",
                closed: false
            });
            /*设置回显数据*/
            rowData["pResource"] = rowData.presource;
            rowData["pId"] = rowData.pid;
            rowData["pName"] = rowData.pname;
            $("#permission_Form").form("load", rowData);
        } else {
            $.messager.alert("提示", "请先选择一行数据在进行编辑");
        }
    });
    /*删除权限按钮*/
    $("#del_Permission").click(function () {
        var rowData = $("#dg_permission").datagrid("getSelected");
        if (rowData) {
            $.messager.confirm("确认", "确认要删除此权限吗？ 删除即将不可恢复", function (res) {
                if (res) {
                    $.get("delPermission/" + rowData.pid, function (data) {
                        if (data.success) {
                            $.messager.alert("提示", data.mrg);
                            /*重新加载数据*/
                            $("#dg_permission").datagrid("reload");
                        } else {
                            $.messager.alert("提示", data.mrg);
                        }
                    })
                }
            })
        } else {
            $.messager.alert("提示", "请先选择一个权限之后在进行删除");
        }

    });
    /*查找权限按钮*/
    $("#find_Permission").click(function () {
        /*获取要查询的内容*/
        var keyword = $(".keywordCla").val();
        if (keyword != '' && keyword != null) {
            /*查询加载数据 并且传一个参数*/
            $("#dg_permission").datagrid("load", {keyword: keyword});
        } else {
            $.messager.alert("提示", "请先输入要查询的用户账号或者姓名");
        }
    });
    /*刷新按钮*/
    $("#flush_Permission").click(function () {
        if (!$(".keywordCla").val()) {
            $.messager.alert("提示", "已经展示所有的内容了。。。。。。。。。。")
        } else {
            $(".keywordCla").val('');
            $("#dg_permission").datagrid("load", {});
        }

    });

    // 增改面板
    $("#dialog_permission").dialog({
        width: 300,
        height: 200,
        modal: true,
        closed: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                var claId = $("[name='pId']").val();
                var url;
                if (claId != '' && claId != null) {
                    url = "/editPermission";
                } else {
                    url = "/addPermission";
                }
                $("#permission_Form").form("submit", {
                    url: url,
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            /*刷新内容*/
                            $("#dg_permission").datagrid("reload");
                            /*关闭窗口*/
                            $("#dialog_permission").dialog("close");
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
                $("#dialog_permission").dialog("close");
            }
        }]
    });
});