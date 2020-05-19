$(function () {
    init();
});

function init(){
//隐藏错误提示框
    $('.alert-danger').css("display", "none");

    //modal绑定hide事件
    $('#modalAdd').on('hide.bs.modal', function () {
        reset();
    })
    $('#modalEdit').on('hide.bs.modal', function () {
        reset();
    })

    // let rowData = sessionStorage.getItem("store_detail");
    // let storeInfo = JSON.parse(rowData);

    let name = JSON.parse(sessionStorage.getItem("userId"));
    var userType = name.type;
    let qryType = JSON.parse(sessionStorage.getItem("qryType"));
    let userName="";
    var storeNumber="";
    if (qryType === 1){
        let item = sessionStorage.getItem("user_detail");
        let userInfo="";
        if (item != null && item !== ""){
            userInfo = JSON.parse(item);
        }
        if (userInfo.userName != null && userInfo.userName !== ""){
            userName = userInfo.userName;
            sessionStorage.setItem("user_detail",name.userName);
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
    }else if (qryType === 0){
        userName="";
        storeNumber=""
    }
    $("#orderTable").jqGrid({
        url: 'order/query',
        datatype: "json",
        colModel: [
            {label: '订单id', name: 'id', index: 'id', hidden:true,width: 50, key: true},
            {label: '商品编号', name: 'commodityNumber', index: 'commodityNumber', hidden:true,width: 30},
            {label: '商品名称', name: 'commodityName', index: 'commodityName', width: 80},
            {label: '用户id', name: 'userId', index: 'userId', sortable: false,align: "center", width: 50},
            {label: '应付价格', name: 'planPrice', index: 'commodityState', sortable: false,align: "center", width: 50},
            {label: '实付价格', name: 'actrualPrice', index: 'createTime', sortable: false,align: "center", width: 50},
            {label: '下单时间', name: 'createTime', index: 'commodityScript', sortable: false,align: "center", width: 50}
        ],
        height: 600,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        multiselect: false,
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
            storeNumber : storeNumber,
            keyWords : "",
            userId : userName
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#orderTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#orderTable").setGridWidth($(".card-body").width());
    });
}