var tag_w = "";//标记子类的tag
var result = "";//存储pro下查询的结果
var class_demo = "";//存储父类的className
var tag_f = "";//存储父类的tag
var result_fa = "";//存储procat下查询的结果

function add_son(tag,classdemo) {
    tag_w = tag;
    class_demo = classdemo;
    var x = $(" ul." + classdemo);
    $("ul."+classdemo+" li").remove();
    ajax(tag);
    var data = result.data;
    console.log(data);
    // console.log(x[0]);
    for(var k = 0;k < data.length;k++) {
        var li = document.createElement("li");
        li.innerHTML = '<a href="#" name="son">' +data[k].name + '</a>';
        x[0].appendChild(li);
        // ul_content += '<li><a href="#" name="son">' +data[k].name + '</a></li>';
    }
    // console.log(ul_content);
    // ul[0].innerHTML = ul_content;
}

//查询pro下的信息
function ajax(tag) {
    var obj = {
        "tag":tag
    };
    var data = JSON.stringify(obj);
    $.ajax({
        url:"/se12/pro/read",
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
            alert("不存在子类");
        }
    });
}

//在pro下插入信息
function ajax_input(name,description) {
    var obj = {
        "name":name,
        "description":description,
        "tag":tag_w
    };
    var data = JSON.stringify(obj);
    $.ajax({
        url:"/se12/pro/add",
        type:"post",
        async:false,
        contentType: "application/json",
        dataType:"JSON",
        data:data,
        success: function(message) {
            result = message;
            if(result.data["name"] == null || result.data["description"] == null){
                alert("请重新输入");
            }
            else {
                alert(result.success);
                data["classdemo"] = class_demo;
                return result.data;
            }
        },
        error: function (err) {
            console.log(err.error);
            alert("不存在子类");
            return result.error;
        }
    });
}


//对于情况的判断
function closeDialog(id,flag) {
    if(flag == 0){
        document.getElementById('pageCover').style.display = 'none';
        document.getElementById(id).style.display = 'none';
    }
    else if(flag == 1){
        // var second_name = $("input.second_name");
        var son_name = $('input[name = "son_name"]').val();
        // var second_description = $("textarea.second_desription");
        var son_description = $('textarea[name = "son_description"]').val();
        ajax_input(son_name, son_description);
        var data = result.data;
        add_son(data["tag"], data["classdemo"]);
    }
     else if(flag == 2){
        var father_name = $('input[name = "father_name"]').val();
        var father_tag = $('input[name = "father_tag"]').val();
        var father_description = $('textarea[name = "father_description"]').val();
        tag_f = father_tag;
        ajax_fa_insert(father_name,father_tag,father_description);
        add_father();
    }
}


//在procat下插入信息
function ajax_fa_insert(name,tag,description) {
    var obj = {
        "name":name,
        "description":description,
        "tag":tag
    };
    var data = JSON.stringify(obj);
    $.ajax({
        url:"/se12/procat/add",
        type:"post",
        async:false,
        contentType: "application/json",
        dataType:"JSON",
        data:data,
        success: function(message) {
            result_fa = message;
            alert(result_fa.success);
        },
        error: function() {
            console.log(data);
            alert("不存在子类");
        }
    });
}

//查询procat下的信息
function ajax_read(tag) {
    var obj = {
        "tag":tag
    };
    var data = JSON.stringify(obj);
    $.ajax({
        url:"/se12/procat/read",
        type:"post",
        async:false,
        contentType: "application/json",
        dataType:"JSON",
        data:data,
        success: function(message) {
            result_fa = message;
        },
        error: function (err) {
            console.log(err.error);
            alert("不存在子类");
        }
    });
}


//目录下添加父类
function add_father() {
    var father_mulu = $("ul.father_mulu");
    var li = document.createElement("li");
    li.className = "admin-parent";
    father_mulu[0].appendChild(li);
    var len = father_mulu[0].getElementsByTagName("li").length;
    li.innerHTML = '<a class="am-cf" data-am-collapse="{target: \'#collapse-nav6\'}" name="father" id="demo'+len+'" href="javascript:add_son_s(' + result_fa.data["tag"] + ',\'demo'+len+''+len+' \');">'+result_fa.data["name"]+'</a>\n' +
        '                            <ul class="am-list am-collapse admin-sidebar-sub am-in demo'+len+''+len+' " id="collapse-nav6">\n' +
        '                            </ul>';
}

function add_son_s(){

}