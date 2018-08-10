var checkUsername = true;
var checkPhone = true;
var checkEmail = true;
var checkPassword = true;

//对密码进行校验
$('#password').bind('input propertychange', function () {
    docheckPassword();
});

$('#repassword').bind('input propertychange', function () {
    docheckRepassword();
});

//对唯一字段进行检查
$('#username').unbind("input propertychange").bind('input propertychange', function(e) {
    if($(this).prop('comStart')) return; // 中文输入过程中不截断
    docheckUsername(true);
}).on('compositionstart', function() {
    $(this).prop('comStart', true);
    console.log('中文输入：开始');
}).on('compositionend', function() {
    $(this).prop('comStart', false);
    console.log('中文输入：结束');
    docheckUsername(true);
});

$('#phone').unbind("input propertychange").bind('input propertychange', function(e) {
    if($(this).prop('comStart')) return; // 中文输入过程中不截断
    docheckPhone(true);
}).on('compositionstart', function() {
    $(this).prop('comStart', true);
    console.log('中文输入：开始');
}).on('compositionend', function() {
    $(this).prop('comStart', false);
    console.log('中文输入：结束');
    docheckPhone(true);
});

$('#email').unbind("input propertychange").bind('input propertychange', function(e) {
    if($(this).prop('comStart')) return; // 中文输入过程中不截断
    docheckEmail(true);
}).on('compositionstart', function() {
    $(this).prop('comStart', true);
    console.log('中文输入：开始');
}).on('compositionend', function() {
    $(this).prop('comStart', false);
    docheckEmail(true);
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

function docheckUsername(needBackCheck) {
    if ($.trim($('#username').val()) == "") {
        $('#usernameTip').text("用户名不能为空！");
        checkUsername = false;
        return;
    } else {
        $('#usernameTip').text("");
        checkUsername = true;
        if (needBackCheck) {
            //后台检查
            checkUniqueField($('#username').val(), null, null);
        }
    }
}

function docheckPhone(needBackCheck) {
    if ($.trim($('#phone').val()) == "") {
        $('#phoneTip').text("手机号不能为空！");
        checkPhone = false;
        return;
    } else {
        $('#phoneTip').text("");
        checkPhone = true;
        if (needBackCheck) {
            //后台检查
            checkUniqueField(null, $('#phone').val(), null);
        }
    }
}

function docheckEmail(needBackCheck) {
    if ($.trim($('#email').val()) == "") {
        $('#emailTip').text("邮箱不能为空！");
        checkEmail = false;
        return;
    } else {
        $('#emailTip').text("");
        checkEmail = true;
        if (needBackCheck) {
            //后台检查
            checkUniqueField(null, null, $('#email').val());
        }
    }
}

//后台检查唯一字段函数
function checkUniqueField(username, phone, email) {
    //封装请求参数
    var rawData = {
        id: $('#id').val(),
        username: username,
        phone: phone,
        email: email
    };
    var request = JSON.stringify(rawData);
    console.log("check request = " + request);
    //后台检验
    $.ajax({
        type: "POST",
        url: "/se12/user/check/unique",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        timeout: 3000,
        async: true,
        data: request,
        success: function (data) {
            if (data != null) {
                if (data.errorCode == 1) {
                    //设置错误信息
                    if (username != null) {
                        checkUsername = false;
                        $('#usernameTip').text("用户名已存在！");
                    } else if (phone != null) {
                        checkPhone = false;
                        $('#phoneTip').text("手机号已被使用！");
                    } else if (email != null) {
                        checkEmail = false;
                        $('#emailTip').text("邮箱已被使用！");
                    }
                } else if (data.errorCode == 0) {
                    if (username != null) {
                        checkUsername = true;
                    } else if (phone != null) {
                        checkPhone = true;
                    } else if (email != null) {
                        checkEmail = true;
                    }
                }
            }
        },
        error: function () {

        }
    });
}

//添加用户信息
$('#addUser').click(function () {
    //非空字段检验
    if (checkUsername)
        docheckUsername(false);
    docheckPassword();
    docheckRepassword();
    if (checkPhone)
        docheckPhone(false);
    if (checkEmail)
        docheckEmail(false);
    //如果检查通过，则执行修改
    if (checkUsername && checkPassword && checkPhone && checkEmail) {
        //获取信息
        var username = $('#username').val();
        var password = $('#password').val();
        var phone = $('#phone').val();
        var email = $('#email').val();
        var description = $('#description').val();
        var sex = "";
        if ($('input[id="woman"]:checked').val() == null) {
            sex = 1;
        } else if ($('input[id="man"]:checked').val() == null) {
            sex = 0;
        }
        //封装请求参数
        var rawData = {
            username: username,
            password: password,
            phone: phone,
            email: email,
            description: description,
            sex: sex
        };
        var request = JSON.stringify(rawData);
        console.log("request = " + request);
        $.ajax({
            type: "POST",
            url: "/se12/user/add",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            timeout: 3000,
            async: false,
            data: request,
            success: function (data) {
                if (data != null) {
                    if (data.errorCode == 0) {
                        $resultAlert = $('#result-alert');
                        $('p.delete-result').text("成功添加用户 " + username + " ，新增用户的 ID 为 " + data.user.id + " ！");
                        $resultAlert.modal();
                        $resultAlert.on('closed.modal.amui', function () {
                            window.location.reload();
                        });
                    } else {
                        $resultAlert = $('#result-alert');
                        $('p.delete-result').text("很遗憾，添加用户失败！");
                        $resultAlert.modal();
                        $resultAlert.on('closed.modal.amui', function () {
                            window.location.reload();
                        });
                    }
                }
            },
            error: function () {
                //显示信息
                $resultAlert = $('#result-alert');
                $('p.delete-result').text("很遗憾，数据被外星人带走了！");
                $resultAlert.modal();
                $resultAlert.on('closed.modal.amui', function () {
                    window.location.href = "/se12/html/static-html/test/login.html";
                    window.parent.location.href = "/se12/html/static-html/test/login.html";
                });
            }
        });
    }
});

//添加返回用户列表按钮事件
$('#back').click(function () {
    window.location.href = "/se12/html/static-html/test/user-lists.html";
});