$(function () {
    init();
});

function init(){
    let commodity = JSON.parse(sessionStorage.getItem("commodity"));
    var id = commodity.id;
    var data = {
        "id": id
    };
    console.log(id);
    $.ajax({
        type: 'post',//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: 'commodity/queryById',//url
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);//打印服务端返回的数据
            if (result != -1) {
                $("#title1").text(result[0].commodityName);
                $("#commodityScript").text(result[0].commodityScript);
                $("#price").text("￥ "+result[0].commodityPrice);
                // $("#picture1").attr("src",result[0].img1);
                // $("#picture2").attr("src",result[0].img1);
                // $("#picture3").attr("src",result[0].img2);
                // $("#picture4").attr("src",result[0].img3);
                // $("#picture5").attr("src",result[0].img4);
                // $("#picture6").attr("src",result[0].img5);
                // $("#exPrice").text(result[0].commodityName);
            }

        }

    });
}