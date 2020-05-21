$(function () {
    init();
});

function init(){
    let rowData = sessionStorage.getItem("user_detail");
    let userData = JSON.parse(rowData);
    var data = {
        "userName":userData.userName
    };
    $.ajax({
        type: 'post',//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: 'user/queryById',//url
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);//打印服务端返回的数据
            if (result != -1) {
                $("#nickName").text(result[0].nickName);
                $("#address").text(result[0].userAddress);
                $("#phone").text(result[0].userPhone);
                $("#email").text(result[0].mail);
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







    // $("#orderTable").jqGrid({
    //     url: 'order/query',
    //     datatype: "json",
    //     colModel: [
    //         {label: '订单id', name: 'id', index: 'id', hidden:true,width: 50, key: true},
    //         {label: '商品编号', name: 'commodityNumber', index: 'commodityNumber', hidden:true,width: 30},
    //         {label: '商品名称', name: 'commodityName', index: 'commodityName', width: 80},
    //         {label: '用户id', name: 'userId', index: 'userId', sortable: false,align: "center", width: 50},
    //         {label: '应付价格', name: 'planPrice', index: 'commodityState', sortable: false,align: "center", width: 50},
    //         {label: '实付价格', name: 'actrualPrice', index: 'createTime', sortable: false,align: "center", width: 50},
    //         {label: '下单时间', name: 'createTime', index: 'commodityScript', sortable: false,align: "center", width: 50}
    //     ],
    //     height: 600,
    //     rowNum: 10,
    //     rowList: [10, 30, 50],
    //     styleUI: 'Bootstrap',
    //     loadtext: '信息读取中...',
    //     rownumbers: false,
    //     multiselect: false,
    //     autowidth: true,
    //     pager: "#orderPager",
    //     jsonReader: {
    //         root: "data.list",
    //         page: "data.pageNum",
    //         total: "data.pages",
    //         records: "data.total"
    //     },
    //     prmNames: {
    //         page: "page",
    //         rows: "rows",
    //         order: "order"
    //     },
    //     postData : {
    //         storeNumber : storeInfo.storeNumber,
    //         keyWords : ""
    //     },
    //     gridComplete: function () {
    //         //隐藏grid底部滚动条
    //         $("#orderTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
    //     }
    // });
    //
    // $(window).resize(function () {
    //     $("#orderTable").setGridWidth($(".card-body").width());
    // });
}

function toPayment(){
    $('#modalPay').modal('show');
}
$('#payButton').click(function () {
    //验证数据
    var  password=  $("#password").val();
    if (password != null) {
        let name = JSON.parse(sessionStorage.getItem("user_detail"));
        var userName = name.userName;
        //一切正常后发送网络请求
        var data = {
            "price" : price,
            "orderId" : orderId,
            "storeId": storeId,
            "userName":userName,
            "password":password
        };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'user/pasdver',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (result) {
                console.log(result);//打印服务端返回的数据

            },
            error: function () {
                alert("操作失败");
            }
        });

    }
});

