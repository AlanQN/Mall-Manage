//获取系统日志信息
$(document).ready(function () {
    //页面添加数据
    queryByPage(1, 10);
    //添加删除事件
    addDeleteEvent();
});

//分页查找
function queryByPage(pageNum, pageSize) {
    //封装请求信息
    var rawData = {
        pageNum: pageNum,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    console.log("request = " + request);
    //使用ajax获取日志信息
    $.ajax({
        type: "POST",
        url: "/log/search",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        async: false,
        data: request,
        success: function (data) {
            console.log(data);
            //显示日志信息
            addRecords(data);
        },
        error: function (data) {
            showResultAndReload("很遗憾，数据被外星人带走了！");
        }
    });
}

//添加删除事件
function addDeleteEvent() {
    //删除单个日志
    $('.deleteOne').click(function(){
        //获取要删除的id
        var id = $(this).parent().parent().parent("td").siblings('.id').text();
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
                    url: "/log/deleteOne",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8;",
                    async: false,
                    data: request,
                    success: function (data) {
                        if (data.result == true) {
                            var $resultAlert = $('#result-alert');
                            $('p.delete-result').text("成功删除ID为 " + id + " 的日志记录");
                            $resultAlert.modal();
                            $resultAlert.on('closed.modal.amui', function () {
                                window.location.reload();
                            });
                        } else {
                            showResult("很遗憾，删除失败");
                        }
                    },
                    error: function (data) {
                        showResult("很遗憾，删除失败");
                    }
                });
            },
            onCancel: function() {

            }
        });
    });
}

//监听搜索内容
$('#keyword').unbind("input propertychange").bind('input propertychange', function(e) {
    if($(this).prop('comStart')) return; // 中文输入过程中不截断
    //搜索
    searchByKeyWord();
}).on('compositionstart', function() {
    $(this).prop('comStart', true);
    console.log('中文输入：开始');
}).on('compositionend', function() {
    $(this).prop('comStart', false);
    console.log('中文输入：结束');
    //搜索
    searchByKeyWord();
});

//根据关键字进行搜索
function searchByKeyWord() {
    //获取关键字
    var keyword = $("#keyword").val();
    if (keyword != null && $.trim(keyword) != "") {
        //封装请求信息
        var rawData = {
            keyword: keyword,
            pageNum: 1,
            pageSize: 10
        };
        var request = JSON.stringify(rawData);
        console.log(request);
        //搜索相应的日志
        $.ajax({
            type: "POST",
            url: "/log/search",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            async: false,
            data: request,
            success: function (data) {
                //添加日志信息
                addRecords(data);
            },
            error: function () {
                showResultAndReload("很遗憾，数据被外星人带走了！");
            }
        });
    } else {
        queryByPage(1, 10);
    }
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

//显示结果并且刷新页面
function showResultAndReload(result) {
    var $resultAlert = $('#result-alert');
    $('p.delete-result').text(result);
    $resultAlert.modal();
    $resultAlert.on('closed.modal.amui', function () {
        window.location.reload();
    });
}

