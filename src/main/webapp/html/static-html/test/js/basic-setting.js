//获取系统基本设置信息
$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/system/basic",
        dataType: "json",
        async: false,
        data: {},
        success: function (data) {
            document.getElementById("setting-id").value = data.id;
            document.getElementById("website-name").value = data.webName;
            document.getElementById("keyword").value = data.keyWord;
            document.getElementById("web-description").value = data.description;
            document.getElementById("copyright").value = data.copyright;
            var hasLoginInfo = data.hasLogNotice;
            document.getElementById("login_inform").value = data.logNotice;
            var hasWelcomeInfo = data.hasAllNotice;
            document.getElementById("welcome_inform").value = data.allNotice;
            document.getElementById("notice").innerText = data.notice;
            document.getElementById("update_log").innerText = data.updateLog;
            if (hasLoginInfo = 0) {

            }
        },
        error: function (data) {

        }
    })
});

//修改基本设置
function changeSetting() {
    console.log($("#notice").val());
    var rawData = {
        id: $("#setting-id").val(),
        webName: $("#website-name").val(),
        keyWord: $("#keyword").val(),
        description: $("#web-description").val(),
        copyright: $("#copyright").val(),
        hasLogNotice: "0",
        logNotice: $("#login_inform").val(),
        hasAllNotice: "0",
        allNotice: $("#welcome_inform").val(),
        notice: $("#notice").val(),
        updateLog: $("#update_log").val()
    };
    var request = JSON.stringify(rawData);
    console.log(request);
    $.ajax({
        url: "/system/modify",
        type: "POST",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        async: "false",
        data: request,
        success:function (data) {
            console.log(data);
            if (data.result) {
                alert("修改成功！");
            }
        },
        error:function (data) {
            console.log(data);
        }
    })
}