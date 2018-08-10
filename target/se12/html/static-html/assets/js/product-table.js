var statusLabel = "";
var status = "";
$(document).ready(function () {
    console.log("123");

    getByPrice();
});

function getByPrice() {
    var getValue = $("#getByPriceValue").val();
    var regexp = /^[0-9]/;
    if (regexp.test(getValue)){
        var obj = {
            "price":getValue
        };
    }
    else {
        var obj = {
            "name":getValue
        };
    }
    var data = typeof_result(obj);
    if(data == null){
        alert("不存在该名称或价格的商品");
    }
    else {
        var len = data.length;
        addTable(len, data);
    }
}

function addTable(len,data) {
    // var table = document.getElementById("table-add");
    var tbody = $("table > tbody");
    // if(tbody.length != 0){
    //     table.removeChild(tbody[0]);
    // }
    // tbody = document.createElement("tbody");
    // table.appendChild(tbody);
    if(tbody.length != 0){
        tbody.html("");
    }
    var rowsLength=len;//行数
    var colLength=11;//列数
    for(var i = 0;i<rowsLength;i++) {
        var objTr = document.createElement("tr");
        for (j = 0; j < colLength; j++) {
            var objTd = document.createElement("td");
            if (j == 0) {
                objTd.innerHTML = '<input type="checkbox" name="cbx" />';
            }
            else if (j == 1) {
                objTd.innerHTML = data[i]["id"];
            }
            else if (j == 2) {
                objTd.innerHTML = data[i]["name"];
            }
            else if (j == 3) {
                objTd.innerHTML = data[i]["description"];
            }
            else if (j == 4) {
                status = data[i]["status"];
                _status(status);
                // objTd.innerHTML = data[i]["status"];
                objTd.innerHTML = statusLabel;
            }
            else if (j == 5) {
                objTd.innerHTML = data[i]["price"];
            }
            else if (j == 6) {
                objTd.innerHTML = data[i]["num"];
            }
            else if (j == 7) {
                objTd.innerHTML = data[i]["tag"];
            }
            else if (j == 8) {
                objTd.innerHTML = data[i]["create_time"];
            }
            else if (j == 9) {
                objTd.innerHTML = data[i]["update_time"];
            }
            else{
                objTd.innerHTML = ' <div class="am-btn-toolbar">\n' +
                    '                                            <div class="am-btn-group am-btn-group-xs">\n' +
                    '                                                <a href="#" onclick="on_off('+ i +')"  class="am-btn am-btn-default am-btn-xs" data-toggle="modal" data-target="#myModal"><span class="am-icon-copy"></span>上架/下架</a>\n' +
                    '                                            </div>\n' +
                    '                                        </div> ';
            }
            objTr.appendChild(objTd);
        }
        tbody[0].appendChild(objTr);
    }
    var records = document.getElementsByClassName("records");
    records[0].innerHTML = data.length;
}

function on_off(i) {
    console.log("12345");
    if(status == 1) status = 2;
    else if(status == 2) status = 1;
    _status(status);
    var td = $ ("table tbody tr td");
    console.log(td[i]);
    td[4].innerHTML = statusLabel;
}

function _status(status) {
    if(status == 1){
        statusLabel = '<span class = \'am-badge am-badge-secondary\'>上架</span>';
    }
    else if(status == 2){
        statusLabel = '<span class = "am-badge am-badge-warning">下架</span>';
    }
}

function typeof_result(obj) {
    console.log(obj);
    if(Object.keys(obj)[0] == "price"){
        var x = "read_price";
        return ajax(obj,x);
    }

    else{
        var x = "read_name";
        return ajax(obj,x);
    }

}

var result = null;
function ajax(obj,variable) {
    var data = JSON.stringify(obj);
    $.ajax({
        url:"/se12/product/" + variable,
        type:"post",
        async:false,
        contentType: "application/json",
        dataType:"JSON",
        data:data,
        success: function(message) {
            result = message;
        },
        error: function (err) {
            console.log(err.error);
        }
    });
    return result.data;
}

function deletemany() {
    var namebox = $("input[name='cbx']"); //获取name值为boxs的所有input
    var records = document.getElementsByClassName("records");
    var tbody = $("table > tbody");
    var trs = tbody[0].childNodes;
    var param = new Array();
    var flag = 0;
    for (var i = 0; i < namebox.length; i++) {
        if (namebox[i].checked == true) {
            flag = 1;
            param.push(trs[i]);
            records[0].innerHTML = Number(records[0].innerHTML) - 1;
        }
    }
    for(var j = 0;j<param.length;j++){
        tbody[0].removeChild(param[j]);
    }
}

function add_page() {
    window.location.href= "edit-products.html";
}

