var input_insert = document.getElementsByClassName("input_insert");
// var myDate = new Date();
function insert(){
   var data = {
       "name": input_insert[0].value,
       "price":input_insert[1].value,
       "num":input_insert[2].value,
       "description":input_insert[3].value,
       "status":0,
       "catid":123,
       "created":new Date(),
       "updated":new Date(),
       "image":null
   };
   data = JSON.stringify(data);
    $.ajax({
        url:"http://127.0.0.1:8080/product/add",
        type:"post",
        async:false,
        contentType: "application/json",
        dataType:"JSON",
        data:data,
        success: function(message) {
            result = message;
            alert(result.success);
            window.location.href = "product-table-test.html";
        },
        error: function (err) {
            alert(err.error);
            window.location.href = "product-table-test.html";
        }
    });
}