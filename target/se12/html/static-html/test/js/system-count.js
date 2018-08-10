var endDate = null;
var startDate = null;
$(document).ready(function(){

	initDate();
	changeDate();

})

function initDate(){
	var oldmonth;
	var oldyear;
	var isNew = true;
	var date = new Date();

	var seperator = "-"
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if(month == 1 ){
		isNew = false;
	}
	if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
    }

    if(isNew ==false){
    	oldmonth = "12";
    	oldyear = year - 1;
    }else{
    	oldyear = year;
    	if(month == 10)
    	{
    		oldmonth = "09";
    	}else if(month > 10){
    		oldmonth = month - 1;
    	}else{
    		oldmonth = "0"+(month-1);
    	}

    }
    // var endDate = new Date(year,month);
    // var startDate = new Date(year-1,month)
    var end = year + seperator + month + seperator + strDate;
    var start = oldyear + seperator + oldmonth + seperator + strDate;
	console.log("开始时间："+start);
	console.log("结束时间："+end);

	endDate = new Date(year,month-1,strDate);
	startDate = new Date(oldyear,oldmonth-1,strDate);

	$("#start-text").val(start);
	$("#end-text").val(end);
}

$('#start-datetimepicker').datetimepicker({
	format: 'yyyy-mm-dd ',
	startView:'year',
	minView:'month',
	autoclose:true,
	todayBtn:true,
	todayHighlight:true,
	language:'zh-CN'
}).on('changeDate', function(ev){
	console.log(ev);
	console.log(endDate);
	if(ev.date.valueOf() > endDate.valueOf()){
		$("#my-alert").text('开始日期应小于结束日期！');
		$("#my-alert").show();
	}else{
		 $("#my-alert").hide();
		 startDate = new Date(ev.date);
		 $('#start-text').text($("#start-datetimepicker").data('date'));
		 changeDate();
	}
});

$('#end-datetimepicker').datetimepicker({
	format: 'yyyy-mm-dd ',
	startView:'year',
	minView:'month',
	autoclose:true,
	todayBtn:true,
	todayHighlight:true,
	language:'zh-CN'
}).on('changeDate', function(ev){
	console.log(ev);
	console.log(endDate);
	if(ev.date.valueOf() < startDate.valueOf()){
		$("#my-alert").text('结束日期应大于开始日期！');
		$("#my-alert").show();
	}else{
		 $("#my-alert").hide();
		 endDate = new Date(ev.date);
		 $('#start-text').text($("#start-datetimepicker").data('date'));
		 changeDate();
	}
});


function changeDate(){
	console.log("开始时间："+startDate);
	console.log("结束时间："+endDate);
	var rawData = {
		start:startDate,
		end:endDate
	}
	var request = JSON.stringify(rawData);
	console.log(request);
	$.ajax({
        url:"/se12/count/data",
        contentType: "application/json;charset=utf-8",
        type: "POST",
        dataType: "json",
        async: "false",
        data: request,
        success:function(msg){
        	if(msg.success ==true){
        		console.log(msg);
        		var dataOb = msg.data;
        		var dataArr = [];
        		for(var i in dataOb){
        			var arr = [];
        			arr.push(dataOb[i].data);
        			arr.push(dataOb[i].count);
        			dataArr.push(arr);
        		}
        		console.log(dataArr);
        		goBarChart(dataArr);
        	}
        }
    })
}


function goBarChart(dataArr) {
    // 声明所需变量
    var canvas, ctx;
    // 图表属性
    var cWidth, cHeight, cMargin, cSpace;
    var originX, originY;
    // 柱状图属性
    var bMargin, tobalBars, bWidth, maxValue;
    var totalYNomber;
    var gradient;

    // 运动相关变量
    var ctr, numctr, speed;
    //鼠标移动
    var mousePosition = {};

    // 获得canvas上下文
    canvas = document.getElementById("barChart");
    if (canvas && canvas.getContext) {
        ctx = canvas.getContext("2d");
    }
    initChart(); // 图表初始化
    drawLineLabelMarkers(); // 绘制图表轴、标签和标记
    drawBarAnimate(); // 绘制柱状图的动画
    //检测鼠标移动
    var mouseTimer = null;
    canvas.addEventListener("mousemove", function(e) {
        e = e || window.event;
        if (e.layerX || e.layerX == 0) {
            mousePosition.x = e.layerX;
            mousePosition.y = e.layerY;
        } else if (e.offsetX || e.offsetX == 0) {
            mousePosition.x = e.offsetX;
            mousePosition.y = e.offsetY;
        }

        clearTimeout(mouseTimer);
        mouseTimer = setTimeout(function() {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            drawLineLabelMarkers();
            drawBarAnimate(true);
        }, 10);
    });

    //点击刷新图表
    canvas.onclick = function() {
        initChart(); // 图表初始化
        drawLineLabelMarkers(); // 绘制图表轴、标签和标记
        drawBarAnimate(); // 绘制折线图的动画
    };


    // 图表初始化
    function initChart() {
        // 图表信息
        cMargin = 30;
        cSpace = 60;
        cHeight = canvas.height - cMargin * 2 - cSpace;
        cWidth = canvas.width - cMargin * 2 - cSpace;
        originX = cMargin + cSpace;
        originY = cMargin + cHeight;

        // 柱状图信息
        bMargin = 15;
        tobalBars = dataArr.length;
        bWidth = parseInt(cWidth / tobalBars - bMargin);
        maxValue = 0;
        for (var i = 0; i < dataArr.length; i++) {
            var barVal = parseInt(dataArr[i][1]);
            if (barVal > maxValue) {
                maxValue = barVal;
            }
        }
        maxValue += 50;
        totalYNomber = 10;
        // 运动相关
        ctr = 1;
        numctr = 100;
        speed = 10;

        //柱状图渐变色
        gradient = ctx.createLinearGradient(0, 0, 0, 300);
        gradient.addColorStop(0, 'green');
        gradient.addColorStop(1, 'rgba(67,203,36,1)');

    }

    // 绘制图表轴、标签和标记
    function drawLineLabelMarkers() {
        ctx.translate(0.5, 0.5); // 当只绘制1像素的线的时候，坐标点需要偏移，这样才能画出1像素实线
        ctx.font = "12px Arial";
        ctx.lineWidth = 1;
        ctx.fillStyle = "#000";
        ctx.strokeStyle = "#000";
        // y轴
        drawLine(originX, originY, originX, cMargin);
        // x轴
        drawLine(originX, originY, originX + cWidth, originY);

        // 绘制标记
        drawMarkers();
        ctx.translate(-0.5, -0.5); // 还原位置
    }

    // 画线的方法
    function drawLine(x, y, X, Y) {
        ctx.beginPath();
        ctx.moveTo(x, y);
        ctx.lineTo(X, Y);
        ctx.stroke();
        ctx.closePath();
    }

    // 绘制标记
    function drawMarkers() {
        ctx.strokeStyle = "#E0E0E0";
        // 绘制 y
        var oneVal = parseInt(maxValue / totalYNomber);
        ctx.textAlign = "right";
        for (var i = 0; i <= totalYNomber; i++) {
            var markerVal = i * oneVal;
            var xMarker = originX - 5;
            var yMarker = parseInt(cHeight * (1 - markerVal / maxValue)) + cMargin;
            //console.log(xMarker, yMarker+3,markerVal/maxValue,originY);
            ctx.fillText(markerVal, xMarker, yMarker + 3, cSpace); // 文字
            if (i > 0) {
                drawLine(originX, yMarker, originX + cWidth, yMarker);
            }
        }
        // 绘制 x
        ctx.textAlign = "center";
        for (var i = 0; i < tobalBars; i++) {
            var markerVal = dataArr[i][0];
            var xMarker = parseInt(originX + cWidth * (i / tobalBars) + bMargin + bWidth / 2);
            var yMarker = originY + 15;
            ctx.fillText(markerVal, xMarker, yMarker, cSpace); // 文字
        }
        // 绘制标题 y
        ctx.save();
        ctx.rotate(-Math.PI / 2);
        ctx.fillText("销售额", -canvas.height / 2, cSpace - 10);
        ctx.restore();
        // 绘制标题 x
        ctx.fillText("时间", originX + cWidth / 2, originY + cSpace / 2 + 10);
    };

    //绘制柱形图
    function drawBarAnimate(mouseMove) {
        for (var i = 0; i < tobalBars; i++) {
            var oneVal = parseInt(maxValue / totalYNomber);
            var barVal = dataArr[i][1];
            var barH = parseInt(cHeight * barVal / maxValue * ctr / numctr);
            var y = originY - barH;
            var x = originX + (bWidth + bMargin) * i + bMargin;
            drawRect(x, y, bWidth, barH, mouseMove); //高度减一避免盖住x轴
            ctx.fillText(parseInt(barVal * ctr / numctr), x + 15, y - 8); // 文字
        }
        if (ctr < numctr) {
            ctr++;
            setTimeout(function() {
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                drawLineLabelMarkers();
                drawBarAnimate();
            }, speed);
        }
    }

    //绘制方块
    function drawRect(x, y, X, Y, mouseMove) {

        ctx.beginPath();
        ctx.rect(x, y, X, Y);
        if (mouseMove && ctx.isPointInPath(mousePosition.x, mousePosition.y)) { //如果是鼠标移动的到柱状图上，重新绘制图表
            ctx.fillStyle = "green";
        } else {
            ctx.fillStyle = gradient;
            ctx.strokeStyle = gradient;
        }
        ctx.fill();
        ctx.closePath();

    }
}
