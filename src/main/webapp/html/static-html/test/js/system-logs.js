//获取系统日志信息
$(document).ready(function () {
    //封装请求信息
    var rawData = {
        pageNum: 1,
        pageSize: 10
    };
    var request = JSON.stringify(rawData);
    console.log("request = " + request);
    //使用ajax获取日志信息
    $.ajax({
        type: "POST",
        url: "/log/search",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        async: "false",
        data: request,
        success: function (data) {
            console.log(data);
            var records = data.records;
            var rows = "";
            for (var i in records) {
                var name = records[i].name == null ? "" : records[i].name;
                var type = "";
                var user = records[i].user == null ? "" : records[i].user;
                var ip = records[i].ip == null ? "" : records[i].ip;
                var createDate = records[i].createDate == null ? "" : records[i].createDate;
                if (records[i].type == 0) {
                    type = "<span class='am-badge am-badge-success'>业务日志</span>";
                }
                rows += "<tr>\n" +
                    "<td>\n" +
                    "<input type=\"checkbox\" name=\"cbx\"/>\n" +
                    "</td>\n" +
                    "<td>" + records[i].id + "</td>\n" +
                    "<td>" + type + "</td>\n" +
                    "<td>" + user + "</td>\n" +
                    "<td>" + name + "</td>\n" +
                    "<td>" + ip + "</td>\n" +
                    "<td>" + createDate + "</td>\n" +
                    "<td>\n" +
                    "<div class=\"am-btn-toolbar\">\n" +
                    "<div class=\"am-btn-group am-btn-group-xs\">\n" +
                    "<a class=\"am-btn am-btn-default am-btn-xs button_a\" onclick=\"openDialog('hasdelete');\">\n" +
                    "<span class=\"am-icon-copy\"></span> 删除\n" +
                    "</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>";
            }
            $("tbody").html(rows);
        },
        error: function (data) {

        }
    });
});