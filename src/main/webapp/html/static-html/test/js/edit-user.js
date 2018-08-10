//编辑用户
$(document).ready(function () {
    console.log("编辑用户");
    //获取用户编号
    var id = location.search.slice(1).split("=")[1];
    //请求参数
    console.log(id);
    var rawData = {
        id: id
    };
    var request = JSON.stringify(rawData);
    console.log("request = " + request);
    //获取用户原始信息
    $.ajax({
        type: "POST",
        url: "/se12/user/edit",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        timeout: 3000,
        async: false,
        data: request,
        success: function (data) {
            if (data != null) {
                //获取字段信息
                var username = data.username == null ? "" : data.username;
                var sex = data.sex == null ? 0 : data.sex;
                var phone = data.phone == null ? "" : data.phone;
                var email = data.email == null ? "" : data.email;
                var headicon = data.headicon == null ? "" : data.headicon;
                var description = data.description == null ? "" : data.description;
                //设置字段信息
                $('#id').val(id);
                $('#username').val(username);
                $('#phone').val(phone);
                $('#email').val(email);
                $('#description').val(description);
                if (sex == 0) {
                    $('#woman').attr('checked', 'true');
                } else {
                    $('#man').attr('checked', 'true');
                }
                if(headicon != "") {
                    $("#up-img-touch img").attr("src", headicon);
                }
            } else {
                //显示信息
                $resultAlert = $('#result-alert');
                $('p.delete-result').text("用户不存在");
                $resultAlert.modal();
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
});

var checkUsername = true;
var checkPhone = true;
var checkEmail = true;

//对唯一字段进行检查
$('#username').unbind("input propertychange").bind('input propertychange', function(e) {
    if($(this).prop('comStart')) return; // 中文输入过程中不截断
    if ($.trim($('#username').val()) == "") {
        $('#usernameTip').text("用户名不能为空！");
        checkUsername = false;
        return;
    } else {
        $('#usernameTip').text("");
        checkUsername = true;
        //后台检查
        checkUniqueField($('#username').val(), null, null);
    }
}).on('compositionstart', function() {
    $(this).prop('comStart', true);
    console.log('中文输入：开始');
}).on('compositionend', function() {
    $(this).prop('comStart', false);
    console.log('中文输入：结束');
    if ($.trim($('#username').val()) == "") {
        $('#usernameTip').text("用户名不能为空！");
        checkUsername = false;
        return;
    } else {
        $('#usernameTip').text("");
        checkUsername = true;
        //后台检查
        checkUniqueField($('#username').val(), null, null);
    }
});

$('#phone').unbind("input propertychange").bind('input propertychange', function(e) {
    if($(this).prop('comStart')) return; // 中文输入过程中不截断
    if ($.trim($('#phone').val()) == "") {
        $('#phoneTip').text("手机号不能为空！");
        checkPhone = false;
        return;
    } else {
        $('#phoneTip').text("");
        checkPhone = true;
        //后台检查
        checkUniqueField(null, $('#phone').val(), null);
    }
}).on('compositionstart', function() {
    $(this).prop('comStart', true);
    console.log('中文输入：开始');
}).on('compositionend', function() {
    $(this).prop('comStart', false);
    console.log('中文输入：结束');
    if ($.trim($('#phone').val()) == "") {
        $('#phoneTip').text("手机号不能为空！");
        checkPhone = false;
        return;
    } else {
        $('#phoneTip').text("");
        checkPhone = true;
        //后台检查
        checkUniqueField(null, $('#phone').val(), null);
    }
});

$('#email').unbind("input propertychange").bind('input propertychange', function(e) {
    if($(this).prop('comStart')) return; // 中文输入过程中不截断
    if ($.trim($('#email').val()) == "") {
        $('#emailTip').text("邮箱不能为空！");
        checkEmail = false;
        return;
    } else {
        $('#emailTip').text("");
        checkEmail = true;
        //后台检查
        checkUniqueField(null, null, $('#email').val());
    }
}).on('compositionstart', function() {
    $(this).prop('comStart', true);
    console.log('中文输入：开始');
}).on('compositionend', function() {
    $(this).prop('comStart', false);
    console.log('中文输入：结束');
    if ($.trim($('#email').val()) == "") {
        $('#emailTip').text("邮箱不能为空！");
        checkEmail = false;
        return;
    } else {
        $('#emailTip').text("");
        checkEmail = true;
        //后台检查
        checkUniqueField(null, null, $('#email').val());
    }
});

//检查唯一字段函数
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

//修改用户信息
$('#modifyUser').click(function () {
    //如果检查通过，则执行修改
    if (checkUsername && checkPhone && checkEmail) {
        //获取信息
        var headicon = $("#up-img-touch img")[0].src;
        var id = $('#id').val();
        var username = $('#username').val();
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
            headicon: headicon,
            id: id,
            username: username,
            phone: phone,
            email: email,
            description: description,
            sex: sex
        };
        var request = JSON.stringify(rawData);
        console.log("request = " + request);
        $.ajax({
            type: "POST",
            url: "/se12/user/modify",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            timeout: 3000,
            async: false,
            data: request,
            success: function (data) {
                if (data != null) {
                    if (data.errorCode == 0) {
                        $resultAlert = $('#result-alert');
                        $('p.delete-result').text("成功修改用户信息！");
                        $resultAlert.modal();
                        $resultAlert.on('closed.modal.amui', function () {
                            window.location.reload();
                        });
                    } else {
                        $resultAlert = $('#result-alert');
                        $('p.delete-result').text("修改用户信息失败！");
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
