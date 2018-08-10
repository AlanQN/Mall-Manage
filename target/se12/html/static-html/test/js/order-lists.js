    $(document).ready(function (){
        //初始化分页插件
        initPagePlugin();
        //加载信息
        pageNum = 1;
        loadAndShowOrder();

        //监听搜索内容
        $("#orderkeyword").bind("input propertychange", function()  {
            if($(this).prop('comStart')) return; // 中文输入过程中不截断
                //搜索
                //console.log(1111);
                searchOrder();
            }).on('compositionstart', function() {
                $(this).prop('comStart', true);
                console.log('中文输入：开始');
            }).on('compositionend', function() {
                $(this).prop('comStart', false);
                console.log('中文输入：结束');
                //搜索
                searchOrder();
            }
        );

        // $('#orderkeyword').bind('input propertychange', function(e) {
        //     if($(this).prop('comStart')) return; // 中文输入过程中不截断
        //     //搜索
        //     console.log(1111);
        // }).on('compositionstart', function() {
        //     $(this).prop('comStart', true);
        //     console.log('中文输入：开始');
        // }).on('compositionend', function() {
        //     $(this).prop('comStart', false);
        //     console.log('中文输入：结束');
        //     //搜索
        //     console.log(1111);
        // });
    })

    var totalPage = 10; //总页数
    var pageSize = 10; //一页中记录数
    var operCode = 0;   //操作代号，0表示查找订单，1表示搜索订单
    function initPagePlugin() {
        $("#page").page({
        pages: totalPage, //页数
        curr: 1, //当前页
        type: 'default', //主题
        groups: 8, //连续显示分页数
        prev: '上一页', //若不显示，设置false即可
        next: '下一页', //若不显示，设置false即可
        first: "首页",
        last: "尾页", //false则不显示
        before: function (context, next) { //加载前触发，如果没有执行next()则中断加载
            next();
        },
        after: function (context, next) { //加载完成后触发
            next();
        },
        /*
         * 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假
         */
        jump: function (context, first) {
            currPage = context.option.curr;
            console.log('当前第：' + context.option.curr + "页");
            //根据operCode执行不同操作
            if (operCode == 0) {
                console.log("查找订单");
                //分页查找
                initTable(currPage);
            } else if (operCode == 1) {
                console.log("搜索订单");
                initSearchTable(currPage,keyword);
            }
        }
    });
    }

    //更新分页插件
    function updatePagePlugin() {
        console.log("更新页面数：" + totalPage);
        $("#page").page({
            pages: totalPage, //页数
            curr: 1, //当前页
            type: 'default', //主题
            groups: 8, //连续显示分页数
            prev: '上一页', //若不显示，设置false即可
            next: '下一页', //若不显示，设置false即可
            first: "首页",
            last: "尾页" //false则不显示
        });
    }

    //加载和显示订单数据
    function loadAndShowOrder() {
        console.log("获取总页数");
        //设置操作代号
        operCode = 0;
        //获取订单列表总页数
        getOrderTotalPage();
        console.log("总页数 = " + totalPage);
        //加载订单信息
        //initTable(pageNum);
        //根据页数判断是否显示分页插件
        if (totalPage > 1) {
            //更新分页插件
            $('#page').show();
            updatePagePlugin();
        } else {
            //只有一页
            $('#page').hide();
        }
    }

    //获取订单列表总页数
    function getOrderTotalPage() {
        //封装请求参数
        var rawData = {
            pageNum: 1,
            pageSize: 10
        };
        var request = JSON.stringify(rawData);
        //获取总页数
        $.ajax({
            type: "POST",
            url: "/se12/order/list",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            async: false,
            data: request,
            success: function (msg) {
                //更新总页数
                totalPage = msg.data.totalPage;
            },
            error: function (data) {

            }
        });
    }

    //获取订单列表
    var pageNum;
    var records;
    function initTable(page){
        console.log("加载订单数据");
        var rawData = {
            pageNum:page,
            pageSize:10
        };
        pageNum=page;
        var request = JSON.stringify(rawData);
        $.ajax({
            url:"/se12/order/list",
            contentType: "application/json;charset=utf-8",
            type: "POST",
            dataType: "json",
            async: "false",
            data: request,
            success:function(msg){
                console.log(msg);
                var rows="";
                records = msg.data.records;//订单列表
                var i=0;
                var demo = document.getElementById("totalRecord");
                var totalRecord = msg.data.totalRecord;
                demo.innerHTML = totalRecord;
                for(i; i<msg.data.recordNum; i++)
                {
                    var orderId ="" ;
                    var payment ="";
                    var userId ="";
                    var shippingId ="";
                    var description ="";
                    var createTime ="";
                    var consignTime ="";
                    var closeTime ="";
                    var endTime ="";
                    var statuss ="";
                    var statusLabel = ""; 
                    var operLabel = ""; 
                    // if(i < msg.data.recordNum)
                    // {
                        orderId=records[i].orderId == null ? "":records[i].orderId;
                        payment=records[i].payment == null ? "":records[i].payment;
                        userId=records[i].userId == null ? "":records[i].userId;
                        shippingId=records[i].shippingId == null ? "":records[i].shippingId;
                        description=records[i].description == null ? "":records[i].description;
                        createTime=records[i].createTime == null ? "":records[i].createTime;
                        consignTime=records[i].consignTime == null ? "":records[i].consignTime;
                        closeTime=records[i].closeTime == null ? "":records[i].closeTime;
                        endTime=records[i].endTime == null ? "":records[i].endTime;
                        status=records[i].status == null ? "":records[i].status;
                        var consignBn = "";
                        var deny_order_lable = "data-target=\"#deny_order_fail\"";
                        var consign_order_lable = "data-target=\"#consign_order_fail\"";
                        if(status == 1){
                            statusLabel = "<span class = 'am-badge am-badge-warning'>未付款</span>";
                           deny_order_lable = "  onclick = \"denyOrder("+orderId+")\"";
                        }else if(status == 2){
                            statusLabel = "<span class = 'am-badge am-badge-primary'>未发货</span>";
                            deny_order_lable = "  onclick = \"denyOrder("+orderId+")\"";
                            consign_order_lable = "onclick=\"getExpress("+orderId+","+status+");\"";
                            //data-target=\"#deny_order\"
                        }else if(status == 3 ){
                            statusLabel = "<span class = 'am-badge am-badge-secondary'>已发货</span>";
                        }else if(status == 4 ){
                            statusLabel = "<span class = 'am-badge am-badge-success'>交易成功</span>";
                        }else if(status == 5 ){
                            statusLabel = "<span class = 'am-badge'>关闭交易</span>";
                        }
                        //console.log(status);
                        operLabel = "<div class=\"am-btn-toolbar\">\n" +
                                "<div class=\"am-btn-group am-btn-group-xs\">\n"+
                                    // "<a href=\"#\" class=\"am-btn am-btn-default am-btn-xs button_a\" "+consign_order_lable+">发货</a>\n"+
                                     "<button type=\"button\" class=\"am-btn am-btn-default\" data-toggle=\"modal\" "+consign_order_lable+" >发货\n"+
                                                    "</button>\n"+
                                    "<button type=\"button\" class=\"am-btn am-btn-default\" data-toggle=\"modal\"  onclick = \"deleteOrder("+orderId+")\">删除\n"+
                                    //data-target=\"#del_order\"
                                                    "</button>\n"+
                                    "<button type=\"button\" class=\"am-btn am-btn-default\" data-toggle=\"modal\" "+deny_order_lable+" >取消\n"+
                                                    "</button>\n"+
                                    "<a href=\"#\" class=\"am-btn am-btn-default button_a\" onclick=\"description("+orderId+",'"+description+"');\">备注</a>\n"+
                                    "<a href=\"#\" class=\"am-btn am-btn-default button_a orderInfo\" onclick=\"order_load("+orderId+");\">详情</a>"
                                "</div>\n"+
                            "</div>\n";
                        //console.log(operLabel);
                    //}
                    rows += "<tr>\n" +
                    "<td>\n" + "<input type=\"checkbox\" name=\"cbx\"/>\n" + "</td>\n" +
                    "<td class = 'orderId'>" + orderId + "</td>\n" +
                        "<td>" + payment + "</td>\n" +
                        "<td>" + userId + "</td>\n" +
                        "<td>" + shippingId + "</td>\n" +
                        "<td>" + description + "</td>\n" +
                        "<td>" + createTime + "</td>\n" +
                        "<td>" + consignTime + "</td>\n" +
                        "<td>" + endTime + "</td>\n" +
                        "<td>" + closeTime + "</td>\n" +
                        "<td>" + statusLabel + "</td>\n" +
                        "<td>\n" +
                            operLabel
                        "</td>\n"+
                    "<tr/>\n";
                }
                $("tbody").html(rows);
            },
            error:function(msg) {
                /* Act on the event */
                console.log("error");
            }
            })
	}

    //获取快递信息
    var consignId = null
    var state = false
    function getExpress(orderId,status){
        console.log(orderId);
        console.log(status);
        if(status == 2)
        {

            consignId = orderId;//标志要发货的id
            if(state == false ){
                console.log("获取快递数据");
                $.ajax({
                    url:"/se12/express/list",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    type:"POST",
                    async: "false",
                    success:function(msg) {
                        console.log(msg);
                        var position = document.getElementById("get-express-list");
                        var records = msg.data;
                        for(var i in records){
                            //console.log(records[i]);
                            var objOption = document.createElement("option");
                            objOption.innerText = records[i].expressName;
                            position.appendChild(objOption);
                        }
                        state = true;
                    }
                })
            }
            openDialog('dg');  
        }
        
    }

    //获取订单详情
    function order_load(orderId) {
        console.log(orderId);
        window.location.href = "orders.html?orderId="+orderId;
    }

    //发货
    function consign(){
        console.log("发货");
        var shippingId = document.getElementById("shippingId").value;
        console.log(shippingId);
        var shippingName = document.getElementById("get-express-list").value;
        console.log(shippingName);
        console.log(consignId);

        var rawData = {
            orderId:consignId,
            shippingId:shippingId,
            shippingName:shippingName
        };
        var request = JSON.stringify(rawData);
        $.ajax({
            url:"/se12/order/consign",
            contentType: "application/json;charset=utf-8",
            type: "POST",
            dataType: "json",
            async: "false",
            data: request,
            success:function(msg){
                console.log(msg);
            }
        })
        initTable(pageNum);
        closeDialog('dg') 
    }

    //获取已存在备注并显示
    var descriptionId;
    function description(orderId,text1){
        descriptionId = orderId;
        if(text1 != '')
        {
            $("#description").val(text1);
            console.log(text1);
        }else{
            $("#description").val(null);
        }
        openDialog('rmk');
    }

    //添加备注
    function descriptionOk(){
        var description = $("#description").val();
        console.log(description);
        var rawData = {
            orderId:descriptionId,
            description:description
        };
        var request = JSON.stringify(rawData);
        console.log(request);
        $.ajax({
            url:"/se12/order/description",
            contentType: "application/json;charset=utf-8",
            type: "POST",
            dataType: "json",
            async: "false",
            data: request,
            success:function(msg){
            }
        })
        initTable(pageNum);
        $("#description").val(null);
        closeDialog('rmk');
    }

    //取消订单
    function denyOrder(orderId){
        
        $('#deny-order-warnning').text("确定要取消选定的订单吗？");
        //显示对话框
        $('#deny-order-confirm').modal({
            relatedTarget: this,
            onConfirm: function(options) {
                var rawData = {
                    orderId:orderId
                };
                var request = JSON.stringify(rawData);
                $.ajax({
                    url:"/se12/order/cancel",
                    contentType: "application/json;charset=utf-8",
                    type: "POST",
                    dataType: "json",
                    async: "false",
                    data: request,
                    success:function(msg){
                        console.log(msg);
                        showOrderResultAndReload(msg.data);
                    }
                })
            },
            onCancel: function() {
            }
        });
    }

    //删除订单
    function deleteOrder(orderId){
        var ids = [];
        ids.push(orderId);
        deleteMore(ids);

    }

    var keyword = null;
    function searchOrder(){
        keyword = $("#orderkeyword").val();
        console.log(keyword);
        if (keyword != null && $.trim(keyword) != ""){
            getSearchOrderTotalPage(keyword);
            operCode = 1;
            initSearchTable(1,keyword);
            if (totalPage <= 1) {
                $('#page').hide();
                return;
            } else {
                //刷新分页插件
                $('#page').show();
                updatePagePlugin();
            }
        }else {
        //更改操作代号
        operCode = 0;
        //查找
        loadAndShowOrder();
        }
    }

    //获取搜索订单列表总页数
    function getSearchOrderTotalPage(keyword) {
        //封装请求参数
        var rawData = {
            pageNum: 1,
            pageSize: 10,
            key:keyword
        };
        var request = JSON.stringify(rawData);
        //获取总页数
        $.ajax({
            type: "POST",
            url: "/se12/order/search",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            async: false,
            data: request,
            success: function (msg) {
                //更新总页数
                if(msg.success == true){
                    totalPage = msg.data.totalPage;
                }else{
                    totalPage = 0;
                }
            },
            error: function (data) {

            }
        });
    }
    
    function initSearchTable(page,keyword){
        console.log("加载订单数据");
        var rawData = {
            pageNum: 1,
            pageSize: 10,
            key:keyword
        };
        pageNum=page;
        var request = JSON.stringify(rawData);
        $.ajax({
            url:"/se12/order/search",
            contentType: "application/json;charset=utf-8",
            type: "POST",
            dataType: "json",
            async: "false",
            data: request,
            success:function(msg){
                console.log(msg);
                var rows="";
                var records = null;//订单列表
                var  demo = document.getElementById("totalRecord");
                var totalRecord = 0;
                if(msg.success == true){
                    records = msg.data.records;//订单列表
                    totalRecord = msg.data.totalRecord;
                    demo.innerHTML = totalRecord;
                }
                demo.innerHTML = totalRecord;
                for(var i in records)
                {
                    var orderId ="" ;
                    var payment ="";
                    var userId ="";
                    var shippingId ="";
                    var description ="";
                    var createTime ="";
                    var consignTime ="";
                    var closeTime ="";
                    var endTime ="";
                    var statuss ="";
                    var statusLabel = ""; 
                    var operLabel = ""; 
                    // if(i < msg.data.recordNum)
                    // {
                        orderId=records[i].orderId == null ? "":records[i].orderId;
                        payment=records[i].payment == null ? "":records[i].payment;
                        userId=records[i].userId == null ? "":records[i].userId;
                        shippingId=records[i].shippingId == null ? "":records[i].shippingId;
                        description=records[i].description == null ? "":records[i].description;
                        createTime=records[i].createTime == null ? "":records[i].createTime;
                        consignTime=records[i].consignTime == null ? "":records[i].consignTime;
                        closeTime=records[i].closeTime == null ? "":records[i].closeTime;
                        endTime=records[i].endTime == null ? "":records[i].endTime;
                        status=records[i].status == null ? "":records[i].status;
                        var consignBn = "";
                        var deny_order_lable = "data-target=\"#deny_order_fail\"";
                        var consign_order_lable = "data-target=\"#consign_order_fail\"";
                        if(status == 1){
                            statusLabel = "<span class = 'am-badge am-badge-warning'>未付款</span>";
                           deny_order_lable = "  onclick = \"denyOrder("+orderId+")\"";
                        }else if(status == 2){
                            statusLabel = "<span class = 'am-badge am-badge-primary'>未发货</span>";
                            deny_order_lable = "  onclick = \"denyOrder("+orderId+")\"";
                            consign_order_lable = "onclick=\"getExpress("+orderId+","+status+");\"";
                            //data-target=\"#deny_order\"
                        }else if(status == 3 ){
                            statusLabel = "<span class = 'am-badge am-badge-secondary'>已发货</span>";
                        }else if(status == 4 ){
                            statusLabel = "<span class = 'am-badge am-badge-success'>交易成功</span>";
                        }else if(status == 5 ){
                            statusLabel = "<span class = 'am-badge'>关闭交易</span>";
                        }
                        //console.log(status);
                        operLabel = "<div class=\"am-btn-toolbar\">\n" +
                                "<div class=\"am-btn-group am-btn-group-xs\">\n"+
                                    // "<a href=\"#\" class=\"am-btn am-btn-default am-btn-xs button_a\" "+consign_order_lable+">发货</a>\n"+
                                     "<button type=\"button\" class=\"am-btn am-btn-default\" data-toggle=\"modal\" "+consign_order_lable+" >发货\n"+
                                                    "</button>\n"+
                                    "<button type=\"button\" class=\"am-btn am-btn-default\" data-toggle=\"modal\"  onclick = \"deleteOrder("+orderId+")\">删除\n"+
                                    //data-target=\"#del_order\"
                                                    "</button>\n"+
                                    "<button type=\"button\" class=\"am-btn am-btn-default\" data-toggle=\"modal\" "+deny_order_lable+" >取消\n"+
                                                    "</button>\n"+
                                    "<a href=\"#\" class=\"am-btn am-btn-default button_a\" onclick=\"description("+orderId+",'"+description+"');\">备注</a>\n"+
                                    "<a href=\"#\" class=\"am-btn am-btn-default button_a orderInfo\" onclick=\"order_load("+orderId+");\">详情</a>"
                                "</div>\n"+
                            "</div>\n";
                        //console.log(operLabel);
                    //}
                    rows += "<tr>\n" +
                     "<td>\n" + "<input type=\"checkbox\" name=\"cbx\"/>\n" + "</td>\n" +
                    "<td class = 'orderId'>" + orderId + "</td>\n" +
                        "<td>" + payment + "</td>\n" +
                        "<td>" + userId + "</td>\n" +
                        "<td>" + shippingId + "</td>\n" +
                        "<td>" + description + "</td>\n" +
                        "<td>" + createTime + "</td>\n" +
                        "<td>" + consignTime + "</td>\n" +
                        "<td>" + closeTime + "</td>\n" +
                        "<td>" + endTime + "</td>\n" +
                        "<td>" + statusLabel + "</td>\n" +
                        "<td>\n" +
                            operLabel
                        "</td>\n"+
                    "<tr/>\n";
                }
                $("tbody").html(rows);
            },
            error:function(msg) {
                /* Act on the event */
                console.log("error");
            }
            })
    }

    //显示结果
    function showOrderResult(result) {
        var $resultAlert = $('#result-order-alert');
        $('p.delete-order-result').text(result);
        $resultAlert.modal();
    }


    //显示结果并且刷新页面
    function showOrderResultAndReload(result) {
        var $resultAlert = $('#result-order-alert');
        $('p.delete-order-result').text(result);
        $resultAlert.modal();
        $resultAlert.on('closed.modal.amui', function () {
        window.location.reload();
    });
    }


    //批量删除
    $('#deleteOrderMore').click(function () {
        console.log("批量删除");
        //获取要删除的编号数组
        var ids = [];
        $('table').find(":checkbox:checked").each(function(){
            var tid = $(this).parent().siblings('.orderId').text();
            var id = parseInt(tid);
            ids.push(id);
        });
        console.log("ids = " + ids);
        deleteMore(ids);
    
    });


    function deleteMore(ids){
        $('#delete-order-warnning').text("确定要删除选定的订单吗？");
        //显示对话框
        $('#delete-order-confirm').modal({
            relatedTarget: this,
            onConfirm: function(options) {
                //封装请求参数
                var rawData = {
                    ids: ids
                };
                var request = JSON.stringify(rawData);
                console.log("request = " + request);
                var randomCode = Math.random();
                $.ajax({
                    type: "POST",
                    url: "/se12/order/delete",
                    cache:false,
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                   // async: true,
                    data: request,
                    success: function (msg) {
                        console.log(msg);
                        if (msg.success == true) {
                            showOrderResultAndReload(msg.data);
                        } else {
                            showOrderResult("很遗憾，删除失败！");
                        }
                    },
                    error: function () {
                        console.log("???");
                    }
                });
            },
            onCancel: function() {

            }
        });
        
    }