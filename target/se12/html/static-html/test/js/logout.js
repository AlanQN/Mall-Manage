//实现退出登录
$(document).ready(function () {
    //退出登录
    $("#logout").click(function () {
        console.log("退出登录");
        $.ajax({
            type: "GET",
            url: "/user/logout",
            async: false,
            success: function (data) {
                console.log(data);
                window.location.href = "/html/static-html/test/login.html";
                window.parent.location.href = "/html/static-html/test/login.html";
            },
            error: function () {
                window.location.href = "/html/static-html/test/login.html";
                window.parent.location.href = "/html/static-html/test/login.html";
            }
        })
    });
});