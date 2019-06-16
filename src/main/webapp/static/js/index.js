$(function () {
    $("#accordion").accordion({
        width:"auto",
        height:300,
        border: false
    });

    $("#tabs").tabs({
        fit: true,
        border: false
    });

    /*学校系统管理树*/
    $("#tree_student").tree({
        url: "/getMenuTrees",
        lines: true,
        onClick: function (node) {
            var str = node.text;
            /*判断标题上是否有这些选项*/
            var isY = $("#tabs").tabs("exists", str);
            if (isY) {
                //有则选中
                $("#tabs").tabs("select", str);
            } else {
                if (node.url != null && node.url != '') {
                    //添加选项卡
                    $('#tabs').tabs('add', {
                        title: str,
                        selected: true,
                        closable: true,
                        content: "<iframe src=" + node.url + " frameborder='0' width='100%' height='100%'></iframe>"
                    });
                }
            }
        },
        onLoadSuccess: function (node, data) {
            /*获取第一个的文字*/
            var str = data[0].children[0].text;
            // 添加一个未选中状态的选项卡面板
            $('#tabs').tabs('add', {
                title: str,
                selected: true,
                closable: true,
                content: "<iframe src=" + data[0].children[0].url + " frameborder='0' width='100%' height='100%'></iframe>"
            });
        }
    });
    /*其他树*/
    $("#tree_else").tree({
        url: "tree_else.json",
        lines: true,
        onClick: function (node) {
            var str = node.text;
            /*判断标题上是否有这些选项*/
            var isY = $("#tabs").tabs("exists", str);
            if (isY) {
                //有则选中
                $("#tabs").tabs("select", str);
            } else {
                if (node.url != null && node.url != '') {
                    //添加选项卡
                    $('#tabs').tabs('add', {
                        title: str,
                        selected: true,
                        closable: true,
                        content: "<iframe src=" + node.url + " frameborder='0' width='100%' height='100%'></iframe>"
                    });
                }
            }
        }
    })
});