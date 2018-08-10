$(function () {
    //获取管理员信息
    $.ajax({
        type: "GET",
        url: "/se12/user/getInfo",
        async: false,
        success: function (data) {
            if (data != null) {
                $('#admin-id').val(data.id);
                $('#admin-name2').val(data.name);
                $('#admin-phone').val(data.phone);
                $('#admin-sex').val(data.sex);
                $('#admin-createdate').val(data.created);
                $('#admin-email').val(data.email);
                $('#admin-intro').val(data.description);
            }
        },
        error: function () {

        }
    });
});