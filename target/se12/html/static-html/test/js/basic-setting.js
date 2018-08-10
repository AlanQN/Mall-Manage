var hasLoginInfo = "";  //是否开启登录通知
var hasWelcomeInfo = "";    //是否开启欢迎通知

$(document).ready(function () {

    //加载基本设置信息
    loadAndShowBasicSetting();
    //设置修改基本设置事件
    $('#save-setting').click(function () {
        changeSetting();
    });

});

//获取基本设置信息
function loadAndShowBasicSetting() {
    //获取基本设置信息
    $.ajax({
        type: "GET",
        url: "/se12/system/basic",
        dataType: "json",
        timeout: 3000,
        async: true,
        data: {},
        success: function (data) {
            if (data != null) {
                //显示基本设置信息
                $('#setting-id').val(data.id);
                $('#website-name').val(data.webName);
                $('#keyword').val(data.keyWord);
                $('#web-description').val(data.description);
                $('#copyright').val(data.copyright);
                hasLoginInfo = data.hasLogNotice == 1 ? true : false;
                $('#use_login_inform').prop("checked", hasLoginInfo);
                $('#login_inform').val(data.logNotice);
                hasWelcomeInfo = data.hasAllNotice == 1 ? true : false;
                $('#use_welcome_inform').prop("checked", hasWelcomeInfo);
                $('#welcome_inform').val(data.allNotice);
                $('#notice').val(data.notice);
                $('#update_log').val(data.updateLog);
            }
        },
        error: function (data) {
            //出错信息
            showResultAndLogin("很遗憾，数据被外星人带走了！");
        }
    });
}

//修改基本设置
function changeSetting() {
    hasLoginInfo = $('#use_login_inform').prop('checked');
    hasWelcomeInfo = $('#use_welcome_inform').prop('checked');
    var hasLogNotice = hasLoginInfo ? 1 : 0;
    var hasAllNotice = hasWelcomeInfo ? 1 : 0;
    var rawData = {
        id: $("#setting-id").val(),
        webName: $("#website-name").val(),
        keyWord: $("#keyword").val(),
        description: $("#web-description").val(),
        copyright: $("#copyright").val(),
        hasLogNotice: hasLogNotice,
        logNotice: $("#login_inform").val(),
        hasAllNotice: hasAllNotice,
        allNotice: $("#welcome_inform").val(),
        notice: $("#notice").val(),
        updateLog: $("#update_log").val()
    };
    var request = JSON.stringify(rawData);
    console.log(request);
    $.ajax({
        url: "/se12/system/modify",
        type: "POST",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        timeout: 3000,
        async: true,
        data: request,
        success:function (data) {
            console.log(data);
            if (data.result) {
                showResult("成功修改系统基本设置信息！")
            } else {
                showResultAndReload("修改系统基本设置信息失败！")
            }
        },
        error:function (data) {
            //出错信息
            showResultAndLogin("很遗憾，数据被外星人带走了！");
        }
    })
}

//显示结果
function showResult(result) {
    var $resultAlert = $('#result-alert');
    $('p.save-result').text(result);
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
    $('p.save-result').text(result);
    $resultAlert.modal();
    $resultAlert.on('closed.modal.amui', function () {
        window.location.reload();
    });
}