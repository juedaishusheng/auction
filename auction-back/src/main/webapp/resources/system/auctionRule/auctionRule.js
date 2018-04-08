/**
 * 适用于普通的添加和编辑窗口,注意iframe页面中要定义submitForm
 */
var html = {
    type : 2,
    shade : 0.3,
    maxmin : true,
    area : [ '100%', '100%' ],
    anim : 1,
    btnAlign : 'r',
    btn : [ '提交', '取消' ],
    success : function(layero, index) {
    },
    yes : function(index, layero) {
        document.getElementById("layui-layer-iframe" + index).contentWindow.submitForm();
    }
};
/**
 * 弹出iframe窗口适用于添加操作
 *
 * @param obj
 */
showAddIframe = function(obj) {
    // 去除打开此页面的按钮的焦点
    document.activeElement.blur();
    var url = $(obj).attr("url");
    var urlHelp = $(obj).attr("urlHelp");
    if (urlHelp != undefined && urlHelp != null && urlHelp != '') {
        url += urlHelp;
    }
    var title = $(obj).attr("title");
    html.title = title;
    html['content'] = path + url;
    layer.open(html);
};
/**
 * 弹出iframe窗口适用于修改操作
 *
 * @param obj
 */
showEditIframe = function(obj) {
    document.activeElement.blur();
    var url = $(obj).attr("url");
    var urlHelp = $(obj).attr("urlHelp");
    if (urlHelp != undefined && urlHelp != null && urlHelp != '') {
        url += urlHelp;
    }
    var title = $(obj).attr("title");
    // 去除打开此页面的按钮的焦点
    var checkStatus = table.checkStatus("search");
    var len = checkStatus.data.length;
    if (len == 0) {
        layer.msg('未选中要操作的行', function() {
        });
    } else if (len > 1) {
        layer.msg('一次仅能操作一行', function() {
        });
    } else {
        html.title = title;
        if (url.indexOf("?") > 0) {
            url += "&";
        } else {
            url += "?";
        }
        html['content'] = path + url + "id=" + checkStatus.data[0].id;
        layer.open(html);
    }
};


/**
 * 通用删除方法,需要食欲页面的table中id要固定为search,如果未定义refreshPage方法则调用window.location.reload();否则执行window.location.reload();
 *
 * @param obj
 */
toDelete = function(obj) {
    document.activeElement.blur();
    // 去除打开此页面的按钮的焦点
    var checkStatus = table.checkStatus("search");
    var len = checkStatus.data.length;
    if (len == 0) {
        layer.msg('未选中要操作的行', function() {
        });
    } else {
        layer.confirm('确定要操作吗?', {
            icon : 3,
            title : '警告'
        }, function(index) {
            var url = $(obj).attr("url");
            var ids = new Array();
            for (var i = 0; i < len; i++) {
                ids.push(checkStatus.data[i].id);
            }
            $.ajax({
                type : "post",
                url : path + url,
                data : {
                    ids : ids
                },
                dataType : "json",
                beforeSend : function() {
                    layer.msg('正在下架' + len + "条数据", {
                        icon : 16
                    });
                },
                success : function(result) {
                    layer.msg(result.msg, {
                        time : 1000
                    }, function() {
                        if (result.code == '0') {
                            if (refreshPage != undefined && refreshPage != null && refreshPage != '') {
                                refreshPage();
                            } else {
                                window.location.reload();
                            }
                        }
                    });
                }
            });
        });

    }
};
/**
 * 弹出iframe窗口适用于修改操作
 *
 * @param obj
 */
showEditRuleIframe = function(obj) {
    document.activeElement.blur();
    var url = $(obj).attr("url");
    var urlHelp = $(obj).attr("urlHelp");
    if (urlHelp != undefined && urlHelp != null && urlHelp != '') {
        url += urlHelp;
    }
    var title = $(obj).attr("title");
    // 去除打开此页面的按钮的焦点
    var checkStatus = table.checkStatus("search");
    var len = checkStatus.data.length;
    if (len == 0) {
        layer.msg('未选中要操作的行', function() {
        });
    } else if (len > 1) {
        layer.msg('一次仅能操作一行', function() {
        });
    } else {

        //修改规则
        $.get("editAuctionRulePre?id=" + checkStatus.data[0].id, function (data, status) {
            if (data.code == '0') {
                html.title = title;
                if (url.indexOf("?") > 0) {
                    url += "&";
                } else {
                    url += "?";
                }
                html['content'] = path + url + "id=" + checkStatus.data[0].id;
                layer.open(html);
            } else {
                layer.msg(data.msg);
            }
        });

    }
};
