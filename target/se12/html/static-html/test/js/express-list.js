var keyword = null;
var operCode = 0;
var totalPage = 10;
var pageNum = 1;
var keyword = null;
$(document).ready(function() {
	console.log("加载快递信息");
	//初始化分页插件	
	initExpressPagePlugin();
	loadAndShowExpress();

	$("#express-keyword").bind('input propertychange',  function(event) {
		if($(this).prop('comStart')) return; // 中文输入过程中不截断
        //搜索
        searchExpress();
    }).on('compositionstart', function() {
        $(this).prop('comStart', true);
        console.log('中文输入：开始');
    }).on('compositionend', function() {
        $(this).prop('comStart', false);
        console.log('中文输入：结束');
        //搜索
        searchExpress();
	});
});


function initExpressPagePlugin(){
	$("#express-page").page({
		pages:totalPage,
		curr:1,
		type:'default',
		groups:6,
		prev:'上一页',
		next: '下一页',
        first: "首页",
        last: "尾页", //false则不显示
        before:function(context,next){
        	next();
        },
        after:function(context,next){
        	next();
        },
        jump:function(context,first){
        	 currPage = context.option.curr;
        	 console.log('当前第：' + context.option.curr + "页");
        	 if(operCode == 0){

                console.log("查找快递");
        	 	queryExpress(currPage);
        	 }else if(operCode == 1){
        	 	searchExpress(currPage,keyword);
        	 }
        }
	})
}

function updateExpressPagePlugin(){
	console.log("更新页面数："+totalPage);
	$("#express-page").page({
		pages: totalPage, //页数
        curr: 1, //当前页
        type: 'default', //主题
        groups: 6, //连续显示分页数
        prev: '上一页', //若不显示，设置false即可
        next: '下一页', //若不显示，设置false即可
        first: "首页",
        last: "尾页" //false则不显示
	});
	$("#express-page").show();
}

function loadAndShowExpress(){
	operCode = 0;

	getExpressTotalPage();

	queryExpress(1);
	//console.log(totalPage)

	if(totalPage > 1){
		updateExpressPagePlugin();
	}else{
		$('#express-page').hide();
	}

}

function getExpressTotalPage(){
	var rawData = {
		pageNum:1,
		pageSize:10
	};
	pageNum = 1;
	var request = JSON.stringify(rawData);
	$.ajax({
		url: '/se12/express/getlist',
		contentType: "application/json;charset=utf-8",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: request
	})
	.done(function(msg) {
		console.log(msg);
		//添加订单数据
		totalPage = msg.data.totalPage;
		console.log("总页数"+msg.data.totalPage);
		//添加
		// addEditExpressEvent();
		// addDeleteExpressEvent();
	})
	.fail(function() {
		console.log("error");
	})
}


function queryExpress(currPage){
	var rawData = {
		pageNum:currPage,
		pageSize:10
	};
	var request = JSON.stringify(rawData);
	//console.log(request);
	$.ajax({
		url: '/se12/express/getlist',
		type: 'POST',
		contentType: "application/json;charset=utf-8",
		dataType: 'json',
		data: request,
        async: false
	})
	.done(function(msg) {
		//console.log(msg);
		//添加订单数据
		addExpressTable(msg.data);
		//添加
		addEditExpressEvent();
		addDeleteExpressEvent();
	})
	.fail(function() {
		console.log("error");
	})
	
}

function addExpressTable(data){
	var rows = "";
	if(data != null){
		//console.log(totalPage)
		totalPage = data.totalPage;
		pageNum = data.pageNum;
		//console.log(totalPage)
		$("#epress-total").text(data.totalRecord);
		var records = data.records;
		for(var i in records){
			var expressId = records[i].id;
			var expressName = records[i].expressName;
			rows += "<tr>"+
				"<td>\n" + "<input type=\"checkbox\" name=\"cbx\"/>\n" + "</td>\n" +
                "<td class='id'>" + expressId + "</td>\n" +
                "<td class='name'>" + expressName + "</td>\n" +
                "<td>\n" +
                "<div class=\"am-btn-toolbar\">\n" +
	                "<div class=\"am-btn-group am-btn-group-xs\">\n" +
		                "<a class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a editExpress\">\n" +
		                "<span class=\"am-icon-pencil-square-o\"></span> 编辑</a>\n" +
		                "<a class=\"am-btn am-btn-default am-btn-xs am-text-secondary button_a deleteExpress\">\n" +
		                "<span class=\"am-icon-copy\"></span> 删除</a>\n" +
	                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>";
		}
		$("tbody").html(rows);
	}else{
		//设置记录个数
        $('#epress-total').text(0);
        //设置记录信息
        $('tbody').html(rows);
	}
}

var expressId = null;
function addEditExpressEvent(){
	$("a.editExpress").click(function(){
		//console.log($(this).text());
		expressId = $(this).parent().parent().parent().siblings('.id').text();
		expressId = parseInt(expressId);
		console.log(expressId);
		var expressName = $(this).parent().parent().parent().siblings('.name').text();
		$("#express-name").val(expressName);
		console.log("快递Id:"+expressId+"\n快递名字："+expressName);
		$("#edit-express-prompt").modal({
			relatedTarget: this,
			onConfirm:function(option){
				//判断输入的名字是新的
				var newName = $("#express-name").val();
				if(expressName != newName && newName != ""){
					var rawData = {
						express:{
							id:expressId,
							expressName:newName
						}

					};
					var request = JSON.stringify(rawData);
					console.log(request);
					$.ajax({
						type: "POST",
                        url: "/se12/express/modify",
                        cache:false,
                        contentType: "application/json;charset=utf-8",
                        dataType: "json",
                        async: true,
                        data: request,
                        success: function (msg) {
                            if(msg.success ==true){
                            	showResult(msg.data)
                            	queryExpress(pageNum);
                            }else{
                            	showResult(msg.error);
                            }
                        },
                        error: function (msg) {
                            showResult("发生错误");
                        }
					})

				}
				else{
					showResult("没有修改操作");
				}
			},
			onCancel:function(){

			}
			
		})
	})
}

//显示结果
function showResult(result) {
    var $resultAlert = $('#epxress-result-alert');
    $('p.epxress-result').text(result);
    $resultAlert.modal();
}

function addDeleteExpressEvent(){
	$("a.deleteExpress").click(function(){
		var ids = [];
		var id = $(this).parent().parent().parent().siblings('.id').text();
		id = parseInt(id);
		ids.push(id);
		deleteExpress(ids);
	})
}

function deleteExpress(ids){
	$("#delete-express-confirm").modal({
		relatedTarget: this,
		onConfirm:function(option){
			var rawData = {
				ids:ids
			}
			var request = JSON.stringify(rawData);
            $.ajax({
                type: "POST",
                url: "/se12/express/delete",
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                async: false,
                data: request,
                success: function (msg) {
                    console.log(msg);
                    if (msg.success == true) {
                        //showResult(msg.data)
                        window.location.reload();
                    } else {
                        showResult(msg.error)
                    }
                },
                error: function () {
                    showResult("出现异常")
                }
			})
        },
		onCancel:function(){

		}
	})

}

$("#deleteMoreExpress").click(function(){
	var ids = [];
	$("table").find(":checkbox:checked").each(function(){
		var id = $(this).parent().siblings(".id").text();
		ids.push(parseInt(id));

	})
	console.log(ids);
	deleteExpress(ids);
})

$("#add-express").click(function(){
	$("#add-express-prompt").modal({
		relatedTarget: this,
		onConfirm:function(option){
			var expressname = $("#add-express-name").val();
			var rawData = {
				name:expressname
			}
			var request = JSON.stringify(rawData);
			$.ajax({
				type: "POST",
                url: "/se12/express/add",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                async: true,
                data: request,
                success: function (msg) {
                    if(msg.success ==true){
                        //showResult(msg.data)
                        window.location.reload();
                    }else{
                        showResult(msg.error);
                    }
                },
                error: function (msg) {
                    showResult("发生错误");
                }
			})

		},
		onCancel:function(){

		}
	})
})

function searchExpress(){
	keyword = $("#express-keyword").val();
	 if (keyword != null && $.trim(keyword) != "") {
        console.log("搜索关键字 = " + keyword);
        //获取搜索用户的总页数
        getSearchExpressTotalPage(keyword);
        //更改操作代号
        operCode = 1;
        //搜索
        searchExpressByKeyword(keyword);
        console.log(totalPage);
        //只有一页，不需要分页
        if(totalPage > 1){
		updateExpressPagePlugin();
		}else{
			$('#express-page').hide();
		}
    } else {
        //更改操作代号
        operCode = 0;
        //查找
        loadAndShowExpress();
    }
}

function getSearchExpressTotalPage(){
	var rawData = {
		pageNum:1,
		pageSize:10,
		key:keyword
	};
	pageNum = 1;
	var request = JSON.stringify(rawData);
	$.ajax({
		url: '/se12/express/search',
		contentType: "application/json;charset=utf-8",
		type: 'POST',
		dataType: 'json',
		async: true,
		data: request
	})
	.done(function(msg) {
		console.log(msg);
		//添加订单数据
		totalPage = msg.data.totalPage;
		console.log("总页数"+msg.data.totalPage);
		//添加
		// addEditExpressEvent();
		// addDeleteExpressEvent();
	})
	.fail(function() {
		console.log("error");
	})
}

function searchExpressByKeyword(){
	var rawData = {
		pageNum:currPage,
		pageSize:10,
		key:keyword
	};
	var request = JSON.stringify(rawData);
	$.ajax({
		url: '/se12/express/search',
		contentType: "application/json;charset=utf-8",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: request
	})
	.done(function(msg) {
		console.log(msg);
		//添加订单数据
		addExpressTable(msg.data);
		//添加
		addEditExpressEvent();
		addDeleteExpressEvent();
		//添加
		// addEditExpressEvent();
		// addDeleteExpressEvent();
	})
	.fail(function() {
		console.log("error");
	})
}


