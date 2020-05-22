$(function () {
    init();
});

function init(){
    let rowData = sessionStorage.getItem("userId");
    let userData = JSON.parse(rowData);
    var data = {
        "userName":userData.userName
    };
    var date = new Date();
    var dateTime = date.toLocaleDateString();
    $("#dateTime").text("日期："+dateTime);
    var accountDate = GetDateStr(date);
    $("#paymentDue").text(accountDate);
    $.ajax({
        type: 'post',//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: 'user/queryById',//url
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);//打印服务端返回的数据
            if (result != -1) {
                $("#nickName").text("昵称："+result[0].nickName);
                $("#address").text("地址："+result[0].userAddress);
                $("#phone").text("电话："+result[0].userPhone);
                $("#email").text("邮箱："+result[0].mail);
            }
        }
    });
    let qryType = JSON.parse(sessionStorage.getItem("qryType"));
    let userName="";
    var storeNumber="";
    if (qryType === 1){
        let item = sessionStorage.getItem("userId");
        let userInfo="";
        if (item != null && item !== ""){
            userInfo = JSON.parse(item);
        }
        if (userInfo.userName != null && userInfo.userName !== ""){
            userName = userInfo.userName;
            sessionStorage.setItem("userId",name.userName);
        }else {
            userName = name.userName;
            console.log(userName)
        }
    }else if (qryType === 2){
        let rowData = sessionStorage.getItem("store_detail");
        let storeInfo = JSON.parse(rowData);

        if (storeInfo != null && storeInfo.storeNumber !== ""){
            storeNumber = storeInfo.storeNumber;
        }
    }else if (qryType == 0){
        userName="";
    }
    $("#orderTable").jqGrid({
        url: 'commodity/query',
        datatype: "json",
        colModel: [
            {label: '商品id', name: 'id', index: 'id', hidden:true,width: 50, key: true},
            {label: '商品编号', name: 'commodityNumber', index: 'commodityNumber', hidden:true,width: 30},
            {label: '商品', name: 'img1', index: 'img1', width: 30,formatter: imgFormat},
            {label: '商品名称', name: 'commodityName', index: 'commodityName', width: 80},
            {label: '商品价格', name: 'commodityPrice', index: 'commodityPrice', sortable: false,align: "center", width: 50},
            {label: '商品状态', name: 'commodityState', index: 'commodityState', sortable: false,align: "center", width: 50,formatter:typeFormat},
            {label: '上架时间', name: 'createTime', index: 'createTime', sortable: false,align: "center", width: 50},
            {label: '商品描述', name: 'commodityScript', index: 'commodityScript', sortable: false,align: "center", width: 50},
        ],
        height: 600,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        multiselect: true,
        autowidth: true,
        pager: "#orderPager",
        jsonReader: {
            root: "data.list",
            page: "data.pageNum",
            total: "data.pages",
            records: "data.total"
        },
        prmNames: {
            page: "page",
            rows: "rows",
            order: "order"
        },
        postData : {
            keyWords : "",
            userId : userName
        },
        // footerrow: true,
        // gridComplete: function () {
        //     var rowNum = parseInt($(this).getGridParam('records'), 10);
        //     if (rowNum > 0) {
        //         $(".ui-jqgrid-sdiv").show();
        //         var commodityPrice = jQuery(this).getCol('commodityPrice', false, 'sum');
        //         $(this).footerData("set", { "Source": "<font color='red'>当前页价格合计<font>", "Hits": "<font color='red'>" + commodityPrice + "<font>"});
        //         price = commodityPrice;
        //     } else {
        //         $(".ui-jqgrid-sdiv").hide();
        //     }
        // },
        // userDataOnFooter: true,
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#orderTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }

    });
    function imgFormat(img1) {
        return "<img id='commodityPic' class=\"round_icon\" src='"+img1+"'>"
    }

    function typeFormat(type){
        return type == "0" ? "已上架" : type=="1"?"已下架":"待审核";
    }
}

function GetDateStr(date) {
    date.setDate(date.getDate()+5);//获取AddDayCount天后的日期
    var y = date.getFullYear();
    var m = (date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1);//获取当前月份的日期，不足10补0
    var d = date.getDate()<10?"0"+date.getDate():date.getDate();//获取当前几号，不足10补0
    return y+"/"+m+"/"+d;
}

function toPayment(){
    var id = $("#orderTable").jqGrid("getGridParam", "selrow");
    console.log("rowId",id)
    if (id == null) {
        alert("请选中商品！");
        return;
    }
    var rowData = $("#orderTable").jqGrid('getRowData',id);
    sessionStorage.setItem("carInfo",JSON.stringify(rowData));
    console.log("data",rowData);
    $('#modalPay').modal('show');
}
$('#payButton').click(function () {
    let rowData = sessionStorage.getItem("carInfo");
    console.log(rowData);
    let car = JSON.parse(rowData);
    console.log(car);
    var commodityId = car.commodityNumber;
    var commodityName = car.commodityName;
    var price = car.commodityPrice;
    console.log(price);
    var  password=  $("#password").val();
    console.log("commodityId="+commodityId+",commodityName="+commodityName+",price="+price);
    if (password != null) {
        let name = JSON.parse(sessionStorage.getItem("userId"));
        var userName = name.userName;
        //一切正常后发送网络请求
        var data = {
            "commodityPrice" : price,
            "commodityId":commodityId,
            "commodityName":commodityName,
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
                if (result == 0) {
                    alert("支付成功");
                } else {
                    alert("支付失败！请重新支付")
                }
                $('#modalPay').modal('hide');
                reload();
            },
            error: function () {
                alert("操作失败");
            }
        });

    }
});

