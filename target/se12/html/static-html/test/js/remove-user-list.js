var totalPage = 10; //总页数
var pageSize = 10; //一页中记录数
var operCode = 0;   //操作代号，0表示查找用户，1表示搜索用户

$(function () {

    console.log("加载已删除用户数据");
    //初始化分页插件
    initPagePlugin();
    //加载已删除用户信息
    loadAndShowRemoveUsers();
    //监听搜索内容
    $('#removeUserKeyword').bind('input propertychange', function(e) {
        if($(this).prop('comStart')) return; // 中文输入过程中不截断
        //搜索
        searchRemoveUsers();
    }).on('compositionstart', function() {
        $(this).prop('comStart', true);
        console.log('中文输入：开始');
    }).on('compositionend', function() {
        $(this).prop('comStart', false);
        console.log('中文输入：结束');
        //搜索
        searchRemoveUsers();
    });

});

//初始化分页插件
function initPagePlugin() {
    $("#page").page({
        pages: totalPage, //页数
        curr: 1, //当前页
        type: 'default', //主题
        groups: 6, //连续显示分页数
        prev: '上一页', //若不显示，设置false即可
        next: '下一页', //若不显示，设置false即可
        first: "首页",
        last: "尾页", //false则不显示
        before: function (context, next) { //加载前触发，如果没有执行next()则中断加载
            next();
        },
        after: function (context, next) { //加载完成后触发
            next();
        },
        /*
         * 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假
         */
        jump: function (context, first) {
            currPage = context.option.curr;
            console.log('当前第：' + context.option.curr + "页");
            //根据operCode执行不同操作
            if (operCode == 0) {
                console.log("查找用户");
                console.log('当前第：' + context.option.curr + "页");
                //分页查找用户
                queryByPage(currPage, pageSize);
            } else if (operCode == 1) {
                console.log("搜索用户");

            }
        }
    });
}

//更新分页插件
function updatePagePlugin() {
    console.log("更新页面数：" + totalPage);
    $("#page").page({
        pages: totalPage, //页数
        curr: 1, //当前页
        type: 'default', //主题
        groups: 6, //连续显示分页数
        prev: '上一页', //若不显示，设置false即可
        next: '下一页', //若不显示，设置false即可
        first: "首页",
        last: "尾页" //false则不显示
    });
    //显示分页插件
    $('#page').show();
}

//加载和显示已删除用户信息
function loadAndShowRemoveUsers() {
    //获取已删除用户总页数
    getRemoveUserTotalPage();
    //设置操作代号
    operCode = 0;
    //加载用户信息
    queryByPage(1, pageSize);
    //根据总页数，决定显示分页插件的情况
    if (totalPage > 1) {
        //更新分页插件
        updatePagePlugin();
    } else {
        //只有一页，隐藏分页插件
        $('#page').hide();
    }
}

//获取已删除用户总页数
function getRemoveUserTotalPage() {
    //封装请求参数
    var rawData = {
        pageNum: 1,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    //获取总页数
    $.ajax({
        type: "POST",
        url: "/se12/user/queryRemove",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        timeout: 3000,
        async: false,
        data: request,
        success: function (data) {
            //更新总页数
            totalPage = data.totalPage;
        },
        error: function (data) {

        }
    });
}

//分页查找已删除用户
function queryByPage(pageNum, pageSize) {
    //加载已删除用户数据
    var rawData = {
        pageNum: pageNum,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    $.ajax({
        type: "POST",
        url: "/se12/user/queryRemove",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        timeout: 3000,
        async: true,
        data: request,
        success: function (data) {
            //显示记录信息
            addRecords(data);
            //添加还原事件
            addRestoreEvent();
        },
        error: function (data) {

        }
    });
}

//显示记录信息
function addRecords(data) {
    //显示记录个数
    $('#totalRecord').text(data.totalRecord == null ? 0 : data.totalRecord);
    //显示已删除用户信息
    var rows = "";
    var records = data.records;
    for(var i in records)
    {
        var phone = records[i].phone == null ? "" : records[i].phone;
        var email = records[i].email == null ? "" : records[i].email;
        rows += "<tr>\n" +
            "<td>\n" + "<input type=\"checkbox\" name=\"cbx\"/>\n" + "</td>\n" +
            "<td class='id'>" + records[i].id + "</td>\n" +
            "<td>" + records[i].username + "</td>\n" +
            "<td>" + phone + "</td>\n" +
            "<td>" + email + "</td>\n" +
            "<td>" + records[i].created + "</td>\n" +
            "<td>" + records[i].updated + "</td>\n" +
            "<td>\n" +
            "<div class=\"am-btn-toolbar\">\n" +
            "<div class=\"am-btn-group am-btn-group-xs\">\n" +
            "<a class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a restoreOne\">\n" +
            "\t<span class=\"am-icon-pencil-square-o\"></span> 还原</a>\n" +
            "</div>\n" +
            "</div>\n" +
            "</td>\n" +
            "</tr>";
    }
    $("tbody").html(rows);
}

//添加还原事件
function addRestoreEvent() {
    //还原单个用户
    $('a.restoreOne').click(function(){
        //获取要还原用户的id
        var id = $(this).parent().parent().parent("td").siblings('.id').text();
        //设置对话框提示信息
        $('#restore-warnning').text("确定要还原ID为 " + id + " 的用户吗？");
        //显示对话框
        $('#restore-confirm').modal({
            relatedTarget: this,
            onConfirm: function(options) {
                //封装请求参数
                var rawData = {
                    id: id
                };
                var request = JSON.stringify(rawData);
                console.log("request = " + request);
                $.ajax({
                    type: "POST",
                    url: "/se12/user/restoreOne",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8;",
                    timeout: 3000,
                    async: true,
                    data: request,
                    success: function (data) {
                        if (data.result == true) {
                            showResultAndReload("成功还原ID为 " + id + " 的用户！");
                        } else {
                            showResult("很遗憾，还原用户失败！");
                        }
                    },
                    error: function (data) {
                        showResultAndLogin("很遗憾，数据被外星人带走了！");
                    }
                });
            },
            onCancel: function() {

            }
        });
    });
}

//搜索用户
function searchRemoveUsers() {
    //获取关键字
    keyword = $("#removeUserKeyword").val();
    //如果关键字不为空，则进行搜索，否则切换为查找
    if (keyword != null && $.trim(keyword) != "") {
        console.log("搜索关键字 = " + keyword);
        //获取搜索已删除用户的总页数
        getSearchRemoveUserTotalPage(keyword);
        //更改操作代号
        operCode = 1;
        //搜索
        searchByKeyword(keyword, 1, pageSize);
        //只有一页，不需要分页
        if (totalPage <= 1) {
            $('#page').hide();
            return;
        } else {
            //刷新分页插件
            updatePagePlugin();
        }
    } else {
        //更改操作代号
        operCode = 0;
        //查找
        loadAndShowRemoveUsers();
    }
}

//获取搜索已删除用户的总页数
function getSearchRemoveUserTotalPage(keyword) {
    //封装请求信息
    var rawData = {
        keyword: keyword,
        searchType: 1,
        keyType: 9,
        pageNum: 1,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    //搜索相应的已删除用户
    $.ajax({
        type: "POST",
        url: "/se12/user/search",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        timeout: 3000,
        async: false,
        data: request,
        success: function (data) {
            //获取总页数
            totalPage = data.totalPage;
        },
        error: function () {

        }
    });
}

//根据关键字搜索用户
function searchByKeyword(keyword, pageNum, pageSize) {
    //封装请求信息
    var rawData = {
        keyword: keyword,
        searchType: 1,
        keyType: 9,
        pageNum: pageNum,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    //搜索相应的用户
    $.ajax({
        type: "POST",
        url: "/se12/user/search",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        timeout: 3000,
        async: true,
        data: request,
        success: function (data) {
            //显示记录信息
            addRecords(data);
            //添加还原事件
            addRestoreEvent();
        },
        error: function (data) {
            showResultAndLogin("很遗憾，数据被外星人带走了！");
        }
    });
}

//批量还原
$('#restoreMore').click(function () {
    console.log("批量还原");
    //获取要还原用户的编号数组
    var ids = [];
    $('table').find(":checkbox:checked").each(function(){
        var id = $(this).parent().siblings('.id').text();
        ids.push(id);
    });
    if (ids.length > 0) {
        console.log("ids = " + ids);
        //设置还原提示
        $('#restore-warnning').text("确定要还原选中的用户吗？");
        //显示对话框
        $('#restore-confirm').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                //封装请求参数
                var rawData = {
                    ids: ids
                };
                var request = JSON.stringify(rawData);
                console.log("request = " + request);
                //还原用户
                $.ajax({
                    type: "POST",
                    url: "/se12/user/restoreMore",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    timeout: 3000,
                    async: true,
                    data: request,
                    success: function (data) {
                        if (data.result == true) {
                            showResultAndReload("成功还原选中的 "+ ids.length + " 个用户！");
                        } else {
                            showResult("很遗憾，无法还原选中的用户！");
                        }
                    },
                    error: function () {
                        showResultAndLogin("很遗憾，数据被外星人带走了！");
                    }
                });
            },
            onCancel: function () {

            }
        });
    }
});

//批量删除
$('#deleteMore').click(function () {
    console.log("批量删除");
    //获取要彻底删除用户的编号数组
    var ids = [];
    $('table').find(":checkbox:checked").each(function(){
        var id = $(this).parent().siblings('.id').text();
        ids.push(id);
    });
    if (ids.length > 0) {
        console.log("ids = " + ids);
        //显示对话框
        $('#delete-confirm').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                //封装请求参数
                var rawData = {
                    ids: ids
                };
                var request = JSON.stringify(rawData);
                console.log("request = " + request);
                //删除用户
                $.ajax({
                    type: "POST",
                    url: "/se12/user/deleteMore",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    timeout: 3000,
                    async: true,
                    data: request,
                    success: function (data) {
                        if (data.result == true) {
                            showResultAndReload("成功删除选中的 " + ids.length + " 个用户！");
                        } else {
                            showResult("很遗憾，无法删除选中的用户！");
                        }
                    },
                    error: function () {
                        showResultAndLogin("很遗憾，数据被外星人带走了！");
                    }
                });
            },
            onCancel: function () {

            }
        });
    }
});

//显示结果
function showResult(result) {
    var $resultAlert = $('#result-alert');
    $('p.delete-result').text(result);
    $resultAlert.modal();
}

//显示结果并跳转到登录页面
function showResultAndLogin(result) {
    var $resultAlert = $('#result-alert');
    $('p.delete-result').text(result);
    $resultAlert.modal();
    $resultAlert.on('closed.modal.amui', function () {
        window.location.href = "/se12/html/static-html/test/login.html";
        window.parent.location.href = "/se12/html/static-html/test/login.html";
    });
}

//显示结果并且刷新页面
function showResultAndReload(result) {
    var $resultAlert = $('#result-alert');
    $('p.delete-result').text(result);
    $resultAlert.modal();
    $resultAlert.on('closed.modal.amui', function () {
        window.location.reload();
    });
}