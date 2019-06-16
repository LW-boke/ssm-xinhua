$(function () {
    var elect;
    /*获取请求头内容*/
    var resnum = document.location.href;
    /*获取参数*/
    var j = resnum.lastIndexOf("=");
    /*用ajax加载数据*/
    var MenuData = $.ajax({
        url: "/getRole",
        success: function (data) {
            elect = data;
            $(data).each(function (index, ele) {
                if (index == 0 && j == -1) {
                    /*默认加载的数据*/
                    $(".ul_role").append(" <li><a href=\"#\" class='elect' data-id='" + ele.rid + "'>" + ele.rname + "</a></li>");
                    $("#dg_role").datagrid("load", {rId: ele.rid});
                    j = ele.rid;
                } else {
                    $(".ul_role").append(" <li><a href=\"#\" data-id='" + ele.rid + "'>" + ele.rname + "</a></li>");
                }
            });
            if (j != -1) {
                j += 1;
                /*截取字符串*/
                var rid = resnum.substring(j, resnum.length);
                if (rid.lastIndexOf("#") > 0) {
                    rid = rid.substring(0, rid.length - 1);
                }
                /*把字符串转为number类型*/
                rid = Number(rid);
                $(".ul_role").find("a").each(function (index, ele) {
                    if ($(ele).data("id") == rid) {
                        $(ele).addClass("elect");
                        $("#dg_role").datagrid("load", {rId: rid});
                    }
                })
            }
        }
    });
    $(".ul_role").delegate("a", "click", function () {
        var cid = $(this).data("id");
        var arrA = $(".ul_role").find("a");
        $(arrA).each(function (index, ele) {
            $(arrA).eq(index).removeClass("elect");
        });
        /*获取选择的数据 并且加载*/
        $(this).addClass("elect");
        $("#dg_role").datagrid("load", {rId: cid});
    });
    /*已有的权限*/
    $("#dg_role").datagrid({
        url: 'getPermissionWidthByRoleId',
        columns: [[
            {field: 'pname', title: "权限名称", width: 100, align: "center"},
            {field: 'presource', title: "权限路径", width: 100, align: "center"}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        onLoadSuccess: function (data) {
            var num = new Array(data.rows.length);
            $(data.rows).each(function (index, ele) {
                num[index] = ele.pid;
            });
            $("#dg_role2").datagrid("load", {arrPid: num});
        },
        onClickRow: function (rowIndex, rowData) {
            /*删除当前行*/
            $("#dg_role").datagrid("deleteRow", rowIndex);
            /*更新关系*/
            var rid = $(".elect").data("id");
            $.get("/delRoleAndPerWidthRidAndPid", {rId: rid, pId: rowData.pid}, function (data) {
                if (data.success) {
                    var rows = $("#dg_role").datagrid("getRows");
                    var num = new Array(rows.length);
                    $(rows).each(function (index, ele) {
                        num[index] = ele.pid;
                    });
                    $("#dg_role2").datagrid("load", {arrPid: num});
                }
            });
        }
    });
    /*未有的权限*/
    $("#dg_role2").datagrid({
        url: 'getPermissionChuRoleId',
        columns: [[
            {field: 'pname', title: "权限名称", width: 100, align: "center"},
            {field: 'presource', title: "权限路径", width: 100, align: "center"}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true,//适用父盒子的宽高,
        onClickRow: function (rowIndex, rowData) {
            /*删除当前行*/
            $("#dg_role2").datagrid("deleteRow", rowIndex);
            /*更新关系*/
            var rid = $(".elect").data("id");
            $.get("/addRoleAndPerWidthRidAndPid", {rId: rid, pId: rowData.pid}, function (data) {
                if (data.success) {
                    /*已有的权限重新加载*/
                    $("#dg_role").datagrid("reload");
                }
            });
        }
    });
    /*增删dialog模板*/
    $("#role_dialog").dialog({
        width: 300,
        height: 200,
        modal: true,
        closed: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                var claId = $("[name='rId']").val();
                var url;
                if (claId != '' && claId != null) {
                    url = "/editRole";
                } else {
                    url = "/addRole";
                }
                $("#role_Form").form("submit", {
                    url: url,
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            //重新加载数据
                            var rid = $(".elect").data("id");
                            window.location.href = "/role?rid=" + rid;
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
                $("#role_dialog").dialog("close");
            }
        }]
    });
    /*添加角色*/
    $("#add_Role").click(function () {
        /*清空表单数据*/
        $("#role_Form").form("clear");
        $("#role_dialog").dialog({
            title: "增加角色",
            closed: false
        });

    });
    /*编辑角色*/
    $("#edit_Role").click(function () {
        /*获取当前角色id*/
        var rid = $(".elect").data("id");
        /*遍历所有的数据*/
        $(elect).each(function (index, ele) {
            if (ele.rid == rid) {
                ele["rNum"] = ele.rnum;
                ele["rId"] = ele.rid;
                ele["rName"] = ele.rname;
                $("#role_Form").form("load", ele);
                $("#role_dialog").dialog({
                    title: "编辑角色",
                    closed: false
                });
                return false;
            }
        });
    });
    /*删除角色*/
    $("#del_Role").click(function () {
        $.messager.confirm("确认", "确认要删除这个菜单栏吗？？？，删除之后不可恢复！！！", function (res) {
            if (res) {
                var rid = $(".elect").data("id");
                $.get("/delRole/" + rid, function (data) {
                    window.location.href = "/role";
                })
            }
        });

    });
});
