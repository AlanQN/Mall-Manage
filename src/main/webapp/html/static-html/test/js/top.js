//实现退出登录
$(document).ready(function () {

    //获取登录用户信息
    $.ajax({
        type: "GET",
        url: "/se12/user/getInfo",
        async: false,
        success: function (data) {
            if (data != null) {
                $('#admin-name').text(" " + data.name);
            }
        },
        error: function () {

        }
    });
    //退出登录
    $("#logout").click(function () {
        console.log("退出登录");
        $.ajax({
            type: "GET",
            url: "/se12/user/logout",
            async: false,
            success: function (data) {
                console.log(data);
                window.location.href = "/se12/html/static-html/test/login.html";
                window.parent.location.href = "/se12/html/static-html/test/login.html";
            },
            error: function () {
                window.location.href = "/se12/html/static-html/test/login.html";
                window.parent.location.href = "/se12/html/static-html/test/login.html";
            }
        })
    });

});

