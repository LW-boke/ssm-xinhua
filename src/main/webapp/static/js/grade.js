$(function () {
    /*关闭默认第一个模板   原因是 默认打开的模板没有内容*/
    closePanel();
    /*模板数据*/
    $("#grade_accordion").accordion({
        fit: true,
        onSelect: function (title, index) {
            //获取当前选中的模板
            var tempContext = $("#grade_accordion").accordion("getPanel", index);
            /*获取年级的编号*/
            var num = $("#grade_accordion").accordion("getSelected").data("id");
            var temp;
            $.post("getClzzByGraNum", {"num": num}, function (data) {
                if (data != null && data != "") {
                    $.each(data, function (index, ele) {
                        /*默认第一个则选中状态*/
                        if (index == 0) {
                            temp = "<li><a href=\"#\" data-id=\"" + ele.claId + "\" class='elect'>" + ele.claName + "</a></li>";
                            $("#grade_dg").datagrid("reload", {id: ele.claId});
                        } else {
                            temp = "<li><a href=\"#\" data-id=\"" + ele.claId + "\">" + ele.claName + "</a></li>";
                        }
                        $(".tmpconter").append(temp);
                    });
                }
            });
        },
        onUnselect: function () {
            $(".tmpconter").html(" ");
        }
    });
    /*在展开第一个模板*/
    var p = $("#grade_accordion").accordion("select", 0);
    /*设置这个模板的高度*/
    p.accordion({height: "auto"});
    /*加载dg数据*/
    $("#grade_dg").datagrid({
        url: '/getClassCourseById',
        width: "auto",
        columns: [[
            {field: 'couName', title: '课程名称', width: 50, align: 'center'},
            {field: 'couOpenDate', title: '开课时间', width: 60, align: 'center'},
            {
                field: 'teacher', title: '授课教师', width: 50, align: 'center', formatter: function (value) {
                    return value ? value.teaName : "";
                }
            },
            {
                field: 'profession', title: '课程归属', width: 50, align: 'center', formatter: function (value) {
                    return value ? value.proName : "";
                }
            },
            {field: 'couContent', title: '课程介绍', width: 300, align: 'center'}
        ]],
        fitColumns: true,
        striped: true, // 显示斑马线效果.
        rownumbers: true,
        singleSelect: true,
        fit: true//适用父盒子的宽高,
    });
    /*事件的委托*/
    $(".tmpconter").delegate("a", "click", function () {
        var id = $(this).data("id");
        $("#grade_dg").datagrid("reload", {id: id});

        /*获取所有的a  清除选择的样式*/
        var allA = $(".tmpconter").find("a");
        for (var i = 0; i < allA.length; i++) {
            $(allA).eq(i).removeClass("elect");
        }
        /*添加当前选中的样式*/
        $(this).addClass("elect");
    });


});

/*关闭默认第一个打开的对话框*/
function closePanel() {
    var aelectionsarrys = $("#grade_accordion").accordion('getSelections');
    if (aelectionsarrys != null && aelectionsarrys.length != 0) {
        for (var i = 0; i < aelectionsarrys.length; i++) {
            aelectionsarrys[i].panel('collapse');
        }
    }
    $('#dlg').dialog('close');
}