//实现登录
$(document).ready(function () {

    //显示登录通知
    loadAndShowLoginNotice();

    console.log("--------");
    //默认隐藏错误提示
    $("#errorTip").hide();
    //用户名检验提示
    $("#name").bind('input propertychange',function(){
        var name = $("#name").val();
        if ($.trim(name).length == 0) {
            $("#nameTip").text("用户名不能为空");
        } else {
            $("#nameTip").text("");
        }
        $("#errorTip").hide();
    });
    //密码检验提示
    $("#password").bind('input propertychange', function () {
        var password = $("#password").val();
        if (password.length < 6) {
            $("#pwdTip").text("密码不少于6位");
        } else {
            $("#pwdTip").text("");
        }
        $("#errorTip").hide();
    });
    //登录
    $("#login").click(function () {
        var check = true;
        var name = $("#name").val();
        var password = $("#password").val();
        if ($.trim(name).length == 0) {
            $("#nameTip").text("用户名不能为空");
            check = false;
        }
        if (password.length < 6) {
            $("#pwdTip").text("密码不少于6位");
            check = false;
        }
        if (check == true) {
            //封装请求数据
            var rawData = {
                name: name,
                password: password
            };
            var request = JSON.stringify(rawData);
            console.log("request = " + request);
            //使用ajax完成登录
            $.ajax({
                type: "POST",
                url: "/se12/user/login",
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                timeout: 3000,
                async: false,
                data: request,
                success: function (data) {
                    console.log(data);
                    var result = data.result;
                    if (result == false) {
                        $("#errorTip").show();
                    } else {
                        window.location.href = "/se12/html/static-html/test/index.html";
                    }
                },
                error: function (data) {
                    $("#errorTip").show();
                }
            })
        }
    });

});

//加载并显示登录通知
function loadAndShowLoginNotice() {
    console.log("加载登陆通知");
    //获取基本设置信息
    $.ajax({
        type: "GET",
        url: "/se12/system/basic",
        dataType: "json",
        timeout: 3000,
        async: true,
        data: {},
        success: function (data) {
            console.log(data);
            if (data != null) {
                //是否需要显示通知
                var hasLoginInfo = data.hasLogNotice == 1 ? true : false;
                console.log("登陆通知 = "+hasLoginInfo);
                if (hasLoginInfo) {
                    showLoginNotice(data.logNotice);
                }
            }
        },
        error: function (data) {

        }
    });
    console.log("加载完毕");
}

//显示通知
function showLoginNotice(notice) {
    var $resultAlert = $('#login-notice-alert');
    $('p.login-notice').text(notice);
    $resultAlert.modal();
}

//关闭通知
function closeAlert() {
    console.log("关闭窗口！");
    $('#login-notice-alert').modal('close');
}