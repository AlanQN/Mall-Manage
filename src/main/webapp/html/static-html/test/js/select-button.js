//全选按钮事件
$('button.selectAll').click(function () {
    $(':checkbox').prop('checked',true);
    //防止自动提交
    return false;
});

//反选按钮事件
$('button.reverseSelect').click(function () {
    $(':checkbox').each(function(){
        var v = $(this).prop('checked') ?  false : true;  /*三元运算： var v = 条件? 真值:假值*/
        $(this).prop('checked',v)
    })
    //防止自动提交
    return false;
});

//取消选择按钮事件
$('button.cancelSelect').click(function () {
    $(':checkbox').prop('checked',false);
    //防止自动提交
    return false;
});