$(function () {
    console.log("加载首页信息");
    //获取首页信息
    $.ajax({
        type: "GET",
        url: "/se12/statistic/indexInfo",
        dataType: "json",
        timeout: 3000,
        async: true,
        data: {},
        success: function (data) {
            console.log(data);
            if (data != null) {
                //获取首页信息
                var productNum = data.productNum == null ? 0 :data.productNum;
                var orderNum = data.orderNum == null ? 0 : data.orderNum;
                var userNum = data.userNum == null ? 0 : data.userNum;
                var viewNum = data.viewNum == null ? 0: data.viewNum;
                var notice = data.notice == null ? "" : data.notice;
                //设置首页信息
                $('#productNum').text(productNum);
                $('#orderNum').text(orderNum);
                $('#userNum').text(userNum);
                $('#viewNum').text(viewNum);
                $('p.index-notice').html(notice);
            }
        },
        error: function (data) {

        }
    });
});