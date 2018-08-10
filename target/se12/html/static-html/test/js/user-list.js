var checkPassword = true;  //密码校验结果
//用户状态
var pauseState = 1; //停用状态
var normalState = 0;    //正常状态
//性别
var woman = 0;  //女
var man = 1;    //男
var keyword = "";   //搜索关键字
var totalPage = 10; //总页数
var pageSize = 10; //一页中记录数
var operCode = 0;   //操作代号，0表示查找用户，1表示搜索用户

$(document).ready(function () {

    console.log("加载用户数据");
    //初始化分页插件
    initPagePlugin();
    //加载用户信息
    loadAndShowUsers();
    //监听搜索内容
    $('#userKeyword').bind('input propertychange', function(e) {
        if($(this).prop('comStart')) return; // 中文输入过程中不截断
        //搜索
        searchUsers();
    }).on('compositionstart', function() {
        $(this).prop('comStart', true);
        console.log('中文输入：开始');
    }).on('compositionend', function() {
        $(this).prop('comStart', false);
        console.log('中文输入：结束');
        //搜索
        searchUsers();
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
                //分页查找用户
                queryByPage(currPage, pageSize);
            } else if (operCode == 1) {
                console.log("搜索用户");
                //搜索用户
                searchByKeyword(keyword, currPage, pageSize);
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

//加载和显示用户数据
function loadAndShowUsers() {
    //设置操作代号
    operCode = 0;
    //获取用户列表总页数
    getUserTotalPage();
    //加载用户信息
    queryByPage(1, pageSize);
    //根据页数判断是否显示分页插件
    if (totalPage > 1) {
        //更新分页插件
        updatePagePlugin();
    } else {
        //只有一页，隐藏分页插件
        $('#page').hide();
    }
}

//获取用户列表总页数
function getUserTotalPage() {
    //封装请求参数
    var rawData = {
        pageNum: 1,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    //获取总页数
    $.ajax({
        type: "POST",
        url: "/se12/user/queryNormal",
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

//分页查找用户
function queryByPage(pageNum, pageSize) {
    //加载用户数据
    var rawData = {
        pageNum: pageNum,
        pageSize: pageSize
    };
    var request = JSON.stringify(rawData);
    $.ajax({
        type: "POST",
        url: "/se12/user/queryNormal",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        timeout: 3000,
        async: true,
        data: request,
        success: function (data) {
            //添加用户数据
            addRecords(data);
            //添加用户事件
            addEditUserEvent();
            addChangeStateEvent();
            addChangePWDEvent();
        },
        error: function (data) {
            showResultAndLogin("很遗憾，数据被外星人带走了！");
        }
    });
}

//添加记录到界面
function addRecords(data) {
    var rows = "";
    if (data != null) {
        //显示记录个数
        $('#totalRecord').text(data.totalRecord == null ? 0 : data.totalRecord);
        console.log("recordNum = " + data.totalRecord);
        //显示用户信息
        var records = data.records;
        for (var i in records) {
            var phone = records[i].phone == null ? "" : records[i].phone;
            var email = records[i].email == null ? "" : records[i].email;
            var stateLabel = "";
            var oper = "";
            var sex = "未知";
            if (records[i].sex == woman) {
                sex = "女";
            } else if (records[i].sex == man) {
                sex = "男";
            }
            if (records[i].state == normalState) {
                stateLabel = "<span class='am-badge am-badge-success'>已启用</span>";
                oper = "停用";
            } else if (records[i].state == pauseState) {
                stateLabel = "<span class='am-badge am-badge-danger'>已停用</span>";
                oper = "启用";
            }
            rows += "<tr>\n" +
                "<td>\n" + "<input type=\"checkbox\" name=\"cbx\"/>\n" + "</td>\n" +
                "<td class='id'>" + records[i].id + "</td>\n" +
                "<td>" + records[i].username + "</td>\n" +
                "<td>" + sex + "</td>\n" +
                "<td>" + phone + "</td>\n" +
                "<td>" + email + "</td>\n" +
                "<td>" + stateLabel + "</td>\n" +
                "<td>" + records[i].created + "</td>\n" +
                "<td>" + records[i].updated + "</td>\n" +
                "<td>\n" +
                "<div class=\"am-btn-toolbar\">\n" +
                "<div class=\"am-btn-group am-btn-group-xs\">\n" +
                "<a class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a editUser\">\n" +
                "\t<span class=\"am-icon-pencil-square-o\"></span> 编辑\n" +
                "</a>\n" +
                "<a class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a changeState\">\n" +
                "\t<span class=\"am-icon-pencil-square-o\"></span> " + oper + "</a>\n" +
                "<a class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a changePWD\">\n" +
                "\t<span class=\"am-icon-pencil-square-o\"></span> 修改密码</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>";
        }
        $("tbody").html(rows);
    } else {
        //设置记录个数
        $('#totalRecord').text(0);
        //设置记录信息
        $('tbody').html(rows);
    }
}

//添加编辑用户事件
function addEditUserEvent() {
    //编辑用户
    $('a.editUser').click(function () {
        //获取用户编号
        var id = $(this).parent().parent().parent().siblings('.id').text();
        console.log("id = " + id);
        //跳转到用户编辑界面
        window.location.href = "edit-users.html?id="+id;
    });
}

//添加启用或停用用户事件
function addChangeStateEvent() {
    //停用/启用用户
    $('a.changeState').click(function () {
        console.log($(this).text());
        //获取用户编号
        var id = $(this).parent().parent().parent().siblings('.id').text();
        if ($.trim($(this).text() )== "启用") {
            console.log("启用" + id);
            pauseOrResumeUser(id, "/se12/user/resume", "启用");
        } else if ($.trim($(this).text()) == "停用") {
            console.log("停用" + id);
            pauseOrResumeUser(id, "/se12/user/pause", "停用");
        }
    });
}

//添加修改密码事件
function addChangePWDEvent() {
    //修改密码
    $('a.changePWD').on('click', function () {
        //获取用户编号
        var id = $(this).parent().parent().parent().siblings('.id').text();
        //修改密码框
        $('#password-prompt').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                //校验密码
                docheckPassword();
                docheckRepassword();
                //校验通过，则修改密码
                if (checkPassword) {
                    //获取密码
                    var password = $('#password').val();
                    //封装请求参数
                    var rawData = {
                        id: id,
                        password: password
                    };
                    var request = JSON.stringify(rawData);
                    console.log("request = " + request);
                    //执行修改密码操作
                    $.ajax({
                        type: "POST",
                        url: "/se12/user/changepwd",
                        contentType: "application/json;charset=utf-8",
                        dataType: "json",
                        timeout: 3000,
                        async: true,
                        data: request,
                        success: function (data) {
                            if (data != null && data.result) {
                                showResult("成功修改ID为 " + id + " 用户的密码！");
                            } else {
                                showResult("很遗憾，修改密码失败！");
                            }
                        },
                        error: function (data) {
                            showResult("很遗憾，修改密码失败！");
                        }
                    });
                }
                //清空对话框
                clearPrompt();
            },
            onCancel: function () {
                //清空对话框
                clearPrompt();
            }
        });
    });
}

//添加用户
$('#addUser').click(function () {
    //跳转到用户添加界面
    window.location.href = "new-users.html";
});

//停用/启用用户
function pauseOrResumeUser(id, url, oper) {
    //封装请求参数
    var rawData = {
        id: id
    };
    var request = JSON.stringify(rawData);
    console.log("reuest = " + request);
    //执行操作
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        timeout: 3000,
        async: true,
        data: request,
        success: function (data) {
            if (data != null && data.result) {
                showResultAndReload("成功" + oper + "ID为 " + id + " 的用户！")
            }
        },
        error: function () {

        }
    });
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

//对密码进行校验
$('#password').bind('input propertychange', function () {
    docheckPassword();
});

$('#repassword').bind('input propertychange', function () {
    docheckRepassword();
});

//检验函数
function docheckPassword() {
    if ($.trim($('#password').val()) == "") {
        checkPassword = false;
        $('#passwordTip').text("密码不能为空！");
    } else if ($('#password').val().length < 6) {
        checkPassword = false;
        $('#passwordTip').text("密码不能少于6位！");
    } else {
        checkPassword = true;
        $('#passwordTip').text("");
    }
    docheckRepassword();
}

function docheckRepassword() {
    if ($('#password').val() != $('#repassword').val()) {
        checkPassword = false;
        if ($('#repassword').val() != "") {
            $('#repasswordTip').text("两次输入密码不一致！");
        }
    } else {
        checkPassword = true;
        $('#repasswordTip').text("");
    }
}

//清空对话框
function clearPrompt() {
    $('#password').val("");
    $('#repassword').val("");
    $('#passwordTip').text("");
    $('#repasswordTip').text("");
}

//搜索用户
function searchUsers() {
    //获取关键字
    keyword = $("#userKeyword").val();
    //如果关键字不为空，则进行搜索，否则切换为查找
    if (keyword != null && $.trim(keyword) != "") {
        console.log("搜索关键字 = " + keyword);
        //获取搜索用户的总页数
        getSearchUserTotalPage(keyword);
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
        loadAndShowUsers();
    }
}

//获取搜索用户的总页数
function getSearchUserTotalPage(keyword) {
    //封装请求信息
    var rawData = {
        keyword: keyword,
        searchType: 0,
        keyType: 9,
        pageNum: 1,
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
        searchType: 0,
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
            //添加用户数据
            addRecords(data);
            //添加用户事件
            addEditUserEvent();
            addChangeStateEvent();
            addChangePWDEvent();
        },
        error: function (data) {
            showResultAndLogin("很遗憾，数据被外星人带走了！");
        }
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
        $('#delete-confirm').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                //封装请求参数
                var rawData = {
                    ids: ids
                };
                var request = JSON.stringify(rawData);
                console.log("request = " + request);
                //删除选中用户
                $.ajax({
                    type: "POST",
                    url: "/se12/user/removeMore",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    timeout: 3000,
                    async: true,
                    data: request,
                    success: function (data) {
                        if (data.result == true) {
                            var $resultAlert = $('#result-alert');
                            $('p.delete-result').text("成功删除选中的 " + ids.length + " 个用户！");
                            $resultAlert.modal();
                            $resultAlert.on('closed.modal.amui', function () {
                                window.location.reload();
                            });
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