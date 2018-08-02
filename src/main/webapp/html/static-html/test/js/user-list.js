$(document).ready(function () {
    console.log("加载用户数据");
    //加载用户数据
    var rawData = {
        pageNum: 1,
        pageSize: 10
    };
    var request = JSON.stringify(rawData);
    $.ajax({
        url: "/user/queryNormal",
        type: "POST",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        async: "false",
        data: request,
        success: function (data) {
            console.log(data);
            var rows = "";
            var records = data.records;
            for(var i in records)
            {
                console.log(records[i]);
                var phone = records[i].phone == null ? "" : records[i].phone;
                var email = records[i].email == null ? "" : records[i].email;
                var stateLabel = "";
                var oper = "";
                if (records[i].state == 0) {
                    stateLabel = "<span class='am-badge am-badge-success'>已启用</span>";
                    oper = "停用";
                } else {
                    stateLabel = "<span class='am-badge am-badge-danger'>已停用</span>";
                    oper = "启用";
                }
                rows += "<tr>\n" +
                    "<td>\n" + "<input type=\"checkbox\" name=\"cbx\"/>\n" + "</td>\n" +
                    "<td>" + records[i].id + "</td>\n" +
                    "<td>" + records[i].username + "</td>\n" +
                    "<td>" + phone + "</td>\n" +
                    "<td>" + email + "</td>\n" +
                    "<td>" + stateLabel + "</td>\n" +
                    "<td>" + records[i].created + "</td>\n" +
                    "<td>" + records[i].updated + "</td>\n" +
                    "<td>\n" +
                    "<div class=\"am-btn-toolbar\">\n" +
                    "<div class=\"am-btn-group am-btn-group-xs\">\n" +
                    "<a href=\"#\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a\" onclick=\"edit_user_load();\">\n" +
                    "\t<span class=\"am-icon-pencil-square-o\"></span> 编辑\n" +
                    "</a>\n" +
                    "<a href=\"#\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a\" onclick=\"openDialog('stopuser')\">\n" +
                    "\t<span class=\"am-icon-pencil-square-o\"></span> " + oper + "</a>\n" +
                    "<a href=\"#\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a\" onclick=\"openDialog('cpd');\">\n" +
                    "\t<span class=\"am-icon-pencil-square-o\"></span> 修改密码</a>\n" +
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
    /*function addRow(id, username, phone, email, state, created, updated) {
        //创建表格第一行
        var oTr = document.createElement("tr");
        addSelectBox(oTr);
        addColumn(oTr, id);
        addColumn(oTr, username);
        addColumn(oTr, phone);
        addColumn(oTr, email);
        addColumn(oTr, state);
        addColumn(oTr, created);
        addColumn(oTr, updated);
        addOperBox(oTr);
        $("tbody").append(oTr);
    }
    function addSelectBox(oTr) {
        var oTd = document.createElement("td");
        var selectbox = document.createElement("input");
        selectbox.type = "checkbox";
        oTd.appendChild(selectbox);
        oTr.appendChild(oTd);
    }
    function addColumn(oTr, info) {
        var oTd = document.createElement("td");
        oTd.appendChild(document.createTextNode(info));
        oTr.appendChild(oTd);
    }
    function addOperBox(oTr) {
        var oTd = document.createElement("td");
        var div = document.createElement("div");
        div.className = "am-btn-toolbar";
        var btnGroup = document.createElement("div");
        btnGroup.className = "am-btn-group am-btn-group-xs";
        addOperBtn(btnGroup, "编辑", "");
        addOperBtn(btnGroup, "停用", "");
        addOperBtn(btnGroup, "修改密码", "");
        div.appendChild(btnGroup);
        oTd.appendChild(div);
        oTr.appendChild(oTd);
    }
    function addOperBtn(btnGroup, tipContent, hrefPath) {
        var btn = document.createElement("a");
        btn.className = "am-btn am-btn-default am-btn-xs am-text-secondary button_a";
        var tip = document.createElement("span");
        tip.className = "am-icon-pencil-square-o";
        tip.textContent = tipContent;
        btn.appendChild(tip);
        btn.href = "hrefPath";
        btnGroup.appendChild(btn);
    }*/
});