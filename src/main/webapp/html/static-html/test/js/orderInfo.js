$(document).ready(function(){
	console.log("订单详情");
	var id = location.search.slice(1).split("=")[1];
	//console.log(id);
	var orderId = parseInt(id);
	var rawData = {
		offset:0,
		limit:100,
		orderId:orderId
	}
	var request = JSON.stringify(rawData);
	//console.log(request);
	$.ajax({
		type: "POST",
        url: "/se12/order/info",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        async: false,
        data: request,
        success: function (msg) {
        	console.log(msg);
        	var orderInfo = msg.data.order;
        	var orderProductList = msg.data.orderProductList;
        	var orderShipping = msg.data.orderShipping;
                if(orderShipping != null){
                        var receiverState = orderShipping.receiverState == null ? "":orderShipping.receiverState;
                        var receiverCity = orderShipping.receiverCity == null ? "" : orderShipping.receiverCity;
                        var receiverDistrict = orderShipping.receiverDistrict == null ? "" :orderShipping.receiverDistrict;
                        var receiverAddress = orderShipping.receiverAddress == null ? "":orderShipping.receiverAddress;
                        var address = receiverState+receiverCity+receiverDistrict+receiverAddress;
                        $("#receiverName").text(orderShipping.receiverName);
                        $("#phone").text(orderShipping.receiverMobile);
                }
        	

        	var rows = "";
        	for(var i in orderProductList){
        		rows += "<tr> "+
			"<td>"+orderProductList[i].productId+"</td>"+
			"<td>"+orderProductList[i].name+"</td>"+
			"<td>"+orderProductList[i].price+"</td>"+
			"<td>"+orderProductList[i].num+"</td>"+
			"<td>"+orderProductList[i].totalFee+"</td>";
        	}
        	$("#probody").html(rows);
        	$("#orderId").text(orderId)
        	$("#created").text(orderInfo.consignTime);
        	$("#address").text(address);
        	$("#postfee").text(orderInfo.postFee);
        	$("#payment").text(orderInfo.payment);
        	console.log(orderInfo.postFee);



        }
	})
})