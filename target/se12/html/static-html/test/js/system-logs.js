var totalPage = 10; //总页数
var pageSize = 10; //一页中记录数
var keyword = "";   //关键字
var operCode = "";   //操作代号，0代表查找用户时切换页面时要执行的操作，1代表搜索用户时切换页面时要执行的操作

//获取系统日志信息
$(document).ready(function () {

    //初始化分页插件
    initPagePlugin();

    //加载和显示系统日志
    loadAndShowSystemLogs();

    //监听搜索内容
    $('#logKeyword').bind('input propertychange', function(e) {
        if($(this).prop('comStart')) return; // 中文输入过程中不截断
        //搜索
        searchSystemLogs();
    }).on('compositionstart', function() {
        $(this).prop('comStart', true);
        console.log('中文输入：开始');
    }).on('compositionend', function() {
        $(this).prop('comStart', false);
        console.log('中文输入：结束');
        //搜索
        searchSystemLogs();
    });

});

//加载和显示系统日志
function loadAndShowSystemLogs() {
    //获取系统日志记录总页数
    getLogTotalPage();
    //设置操作代号
    operCode = 0;
    //查找用户
    queryByPage(1, pageSize);
    //更新分页插件
    if (totalPage > 1) {
        updatePagePlugin();
    } else {
        //只有一页，不需要分页
        $('#page').hide();
    }
}

//获取系统日志记录的总页数
function getLogTotalPage() {
    //封装请求信息
    var rawData = {
        pageNum: 1,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    //执行
    $.ajax({
        type: "POST",
        url: "/se12/log/search",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        timeout: 3000,
        async: false,
        data: request,
        success: function (data) {
            console.log("总页数： " + data.totalPage);
            totalPage = data.totalPage;
        },
        error: function (data) {
            
        }
    });
}

//分页查找
function queryByPage(pageNum, pageSize) {
    console.log("分页查找日志");
    //封装请求信息
    var rawData = {
        pageNum: pageNum,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    //使用ajax获取日志信息
    $.ajax({
        type: "POST",
        url: "/se12/log/search",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        timeout: 3000,
        async: true,
        data: request,
        success: function (data) {
            //显示日志信息
            addRecords(data);
            //添加删除事件
            addDeleteEvent();
        },
        error: function (data) {
            showResultAndLogin("很遗憾，数据被外星人带走了！");
        }
    });
}

//添加删除事件
function addDeleteEvent() {
    //删除单个日志
    $('.deleteOne').click(function(){
        //获取要删除的id
        var id = $(this).parent().parent().parent("td").siblings('.id').text();
        //设置删除提示
        $('#delete-warnning').text("确定要删除ID为 " + id + " 的日志记录吗？");
        //显示对话框
        $('#delete-confirm').modal({
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
                    url: "/se12/log/deleteOne",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8;",
                    timeout: 3000,
                    async: false,
                    data: request,
                    success: function (data) {
                        if (data.result == true) {
                            showResultAndReload("成功删除ID为 " + id + " 的日志记录！");
                        } else {
                            showResult("很遗憾，删除失败！");
                        }
                    },
                    error: function (data) {
                        showResultAndLogin("很遗憾，删除失败！");
                    }
                });
            },
            onCancel: function() {

            }
        });
    });
}


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
                console.log("查找日志");
                //分页查找日志
                queryByPage(currPage, pageSize);
            } else if (operCode == 1) {
                console.log("搜索日志");
                //搜索日志
                searchByKeyWord(keyword, currPage, pageSize);
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

//搜索日志
function searchSystemLogs() {
    //获取关键字
    keyword = $("#logKeyword").val();
    //如果关键字不为空，则进行搜索，否则切换为查找
    if (keyword != null && $.trim(keyword) != "") {
        //获取搜索日志的总页数
        getSearchLogTotalPage(keyword);
        //更改操作代号
        operCode = 1;
        //搜索
        searchByKeyWord(keyword, 1, pageSize);
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
        loadAndShowSystemLogs();
    }
}

//获取搜索日志的总页数
function getSearchLogTotalPage(keyword) {
    //封装请求信息
    var rawData = {
        keyword: keyword,
        pageNum: 1,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    //搜索相应的日志
    $.ajax({
        type: "POST",
        url: "/se12/log/search",
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


//根据关键字进行搜索
function searchByKeyWord(keyword, pageNum, pageSize) {
    //封装请求信息
    var rawData = {
        keyword: keyword,
        pageNum: pageNum,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    //搜索相应的日志
    $.ajax({
        type: "POST",
        url: "/se12/log/search",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        timeout: 3000,
        async: true,
        data: request,
        success: function (data) {
            //添加日志信息
            addRecords(data);
            //添加删除事件
            addDeleteEvent();
        },
        error: function () {
            showResultAndLogin("很遗憾，数据被外星人带走了！");
        }
    });
}

//添加日志信息
function addRecords(data) {
    var rows = "";
    if (data != null)  {
        //设置记录个数
        $('#totalRecord').text(data.totalRecord == null ? 0 : data.totalRecord);
        //显示日志信息
        var records = data.records;
        for (var i in records) {
            var name = records[i].name == null ? "" : records[i].name;
            var type = "";
            var user = records[i].user == null ? "" : records[i].user;
            var ip = records[i].ip == null ? "" : records[i].ip;
            var createDate = records[i].createDate == null ? "" : records[i].createDate;
            if (records[i].type == 0) {
                type = "<span class='am-badge am-badge-success'>业务日志</span>";
            }
            rows += "<tr>\n" +
                "<td>\n" +
                "<input type=\"checkbox\" name=\"cbx\"/>\n" +
                "</td>\n" +
                "<td class='id'>" + records[i].id + "</td>\n" +
                "<td>" + type + "</td>\n" +
                "<td>" + user + "</td>\n" +
                "<td>" + name + "</td>\n" +
                "<td>" + ip + "</td>\n" +
                "<td>" + createDate + "</td>\n" +
                "<td>\n" +
                "<div class=\"am-btn-toolbar\">\n" +
                "<div class=\"am-btn-group am-btn-group-xs\">\n" +
                "<a class='am-btn am-btn-default am-btn-xs button_a deleteOne'>\n" +
                "<span class=\"am-icon-copy\"></span> 删除\n" +
                "</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>";
        }
        $('tbody').html(rows);
    } else {
        //设置记录个数
        $('#totalRecord').text(0);
        //设置记录信息
        $('tbody').html(rows);
    }
}

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

//批量删除
$('#deleteMore').click(function () {
    console.log("批量删除");
    //获取要删除的编号数组
    var ids = [];
    $('table').find(":checkbox:checked").each(function(){
        var id = $(this).parent().siblings('.id').text();
        ids.push(id);
    });
    if (ids.length > 0) {
        console.log("ids = " + ids);
        //设置删除提示
        $('#delete-warnning').text("确定要删除选定的日志记录吗？");
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
                //删除日志
                $.ajax({
                    type: "POST",
                    url: "/se12/log/deleteMore",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    timeout: 3000,
                    async: true,
                    data: request,
                    success: function (data) {
                        if (data.result == true) {
                            showResultAndReload("成功删除选中的 " + ids.length + " 条日志记录！");
                        } else {
                            showResult("很遗憾，删除失败！");
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
