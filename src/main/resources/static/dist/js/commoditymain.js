$(function () {
    init();
});

function init(){
    var id = document.getElementById("commodityId").Id;
    console.log(id);
    $.ajax({
        type: 'POST',//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: 'commodity/query',//url
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            'id':id
        }),
        success: function (result) {
            console.log(result);//打印服务端返回的数据

        }

    });
}