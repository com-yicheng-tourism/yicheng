$(function () {
    init();
    $('#couponAdd').click(function () {
        // var id=$('#couponTable').jqGrid('getGridParam','selrow');
        // if (id == null) {
        //     return;
        // }
        // var rowData = $("#couponTable").jqGrid('getRowData',id);
        // console.log("rowData:",rowData)
        // $('#coupon-name').val(rowData.name);
        // $('#couponType').val(rowData.couponType);
        // $('#reached-amount').val(rowData.reachedAmount);
        // $('#minus-amount').val(rowData.minusAmount);
        // $('#reduce-amount').val(rowData.reduceAmount);
        // $('#effective-time').val(rowData.effectiveTime);
        // $('#expiration-time').val(rowData.expirationTime);
        // $('#discount').val(rowData.discount);
        $('#modalAdd').modal('show');
    });

    $('#saveButton').click(function () {
        let name = JSON.parse(sessionStorage.getItem("userId"));
       var data = {
           "userId" : name.userName,
           "name" : $('#coupon-name').val(),
           "couponType" : $('#couponType').val() == "满减" ? "1" : ($('#couponType').val() == "立减" ? "2" :"3"),
           "reachedAmount" : $('#reached-amount').val(),
           "minusAmount" : $('#minus-amount').val(),
           "reduceAmount" : $('#reduce-amount').val(),
           "effectiveTime" : $('#effective-time').val(),
           "expirationTime" : $('#expiration-time').val(),
           "discount" : $('#discount').val()
       };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'coupon/insert',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            async : false,
            success: function (result) {
               alert(result.message);
               window.location.href="/coupon"
            }
        });
    });

    $('#editButton').click(function () {
        let name = JSON.parse(sessionStorage.getItem("userId"));
        var data = {
            "serialCode" : $('#edit-coupon-id').val,
            "userId" : name.userName,
            "name" : $('#coupon-name').val(),
            "couponType" : $('#couponType').val() == "满减" ? "1" : ($('#couponType').val() == "立减" ? "2" :"3"),
            "reachedAmount" : $('#reached-amount').val(),
            "minusAmount" : $('#minus-amount').val(),
            "reduceAmount" : $('#reduce-amount').val(),
            "effectiveTime" : $('#effective-time').val(),
            "expirationTime" : $('#expiration-time').val(),
            "discount" : $('#discount').val()
        };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'coupon/edit',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            async : false,
            success: function (result) {
                alert(result.message);
                window.location.href="/coupon"
            }
        });
    });

    function toExport() {
        
    }
    function toSearch() {
        
    }
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
    let name = JSON.parse(sessionStorage.getItem("userId"));
    var userType = name.type;
    let store = "";
    $.ajax({
        type : "GET",
        url : "store/getStore",
        dataType : "json",
        async : false,
        data : {
            userId : name.userName
        },
        success : function (result) {
            store = result.data.storeNumber;
            // console.log("storeNumber:",storeNumber)
        }
    });
    let qryType = JSON.parse(sessionStorage.getItem("qryType"));
    let userName="";
    let storeNumber="";
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
        }else {
            storeNumber = store;
        }
    }else if (qryType === 0){
        userName="";
        storeNumber=""
    }
    // let rowData = sessionStorage.getItem("store_detail");
    // let storeInfo = JSON.parse(rowData);
    // var storeId="";
    // if (storeInfo != null && storeInfo.storeNumber !== ""){
    //     storeId = storeInfo.id;
    //     console.log(storeId);
    // }

    $("#couponTable").jqGrid({
        url: 'coupon/qry',
        datatype: "json",
        colModel: [
            {label: '优惠券id', name: 'serialCode', index: 'serialCode', hidden: true, width: 80, key: true},
            {label: '优惠名称', name: 'name', index: 'name', width: 80},
            {label: '优惠券类型', name: 'useType', index: 'useType', width: 80, formatter: typeUseFormat},
            {label: '店铺id', name: 'storeId', index: 'storeId', hidden : true,width: 50},
            {label: '优惠类型', name: 'couponType', index: 'couponType',  width: 30,formatter: typeCouponFormat},
            {label: '满足金额', name: 'reachedAmount', index: 'reachedAmount', sortable: false, align: "center", width: 30},
            {label: '满减金额', name: 'minusAmount', index: 'minusAmount', sortable: false, align: "center", width: 30},
            {label: '立减金额', name: 'reduceAmount', index: 'reduceAmount', sortable: false, align: "center", width: 30},
            {label: '折扣率', name: 'discount', index: 'discount', sortable: false, align: "center", width: 30},
            {label: '生效时间', name: 'effectiveTime', index: 'effectiveTime', sortable: false, align: "center", width: 80},
            {label: '失效时间', name: 'expirationTime', index: 'expirationTime', sortable: false, align: "center", width: 80},
            {
                label: '操作',
                name: 'state',
                index: 'state',
                width: 120,
                sortable: false,
                align: "center",
                edittype: "button",
                formatter: cmgStateFormat
            }

        ],
        height: 635,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        // rownumbers: true,
        // multiselect: true,
        autowidth: true,
        pager: "#couponPager",
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
        postData: {
            storeId:storeNumber,
            keyWords: ""
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#couponTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    function cmgStateFormat(grid, rows) {
        let name = JSON.parse(sessionStorage.getItem("userId"));
        var userType = name.type;
        if (userType == '1') {
            return "<button class=\"btn btn-info\" onclick=\"toStoreEdit()\"><i class=\"fa fa-plus\"></i>编辑</button>" +
                "<button class=\"btn btn-danger\" onclick=\"toDelete()\"><i class=\"fa fa-plus\"></i>删除</button>";
        } else if (userType == '2') {
            return "<button class=\"btn btn-info\" onclick=\"couponEdit()\">编辑</button>" +
                "<button class=\"btn btn-info\" onclick=\"couponDel()\">删除</button>"
        } else {
            return "<button class=\"btn btn-info\" onclick=\"toStoreMain()\"><i class=\"fa fa-plus\"></i>进入店铺</button>"
        }

    }

    function typeUseFormat(type) {
        return type == "1" ? "仅限当前店铺可用" : "所有店铺可用";
    }
    function typeCouponFormat(type) {
        return type == "1" ? "满减" : (type == "2" ? "立减" : "折扣");
    }

    $(window).resize(function () {
        $("#couponTable").setGridWidth($(".card-body").width());
    });
}

function couponEdit() {
    var id=$('#couponTable').jqGrid('getGridParam','selrow');
    if (id == null) {
        return;
    }
    var rowData = $("#couponTable").jqGrid('getRowData',id);
    console.log("rowData:",rowData)
    $('#edit-coupon-id').val(rowData.serialCode);
    $('#edit-coupon-name').val(rowData.name);
    $('#edit-couponType').val(rowData.couponType);
    $('#edit-reached-amount').val(rowData.reachedAmount);
    $('#edit-minus-amount').val(rowData.minusAmount);
    $('#edit-reduce-amount').val(rowData.reduceAmount);
    $('#edit-effective-time').val(rowData.effectiveTime);
    $('#edit-expiration-time').val(rowData.expirationTime);
    $('#edit-discount').val(rowData.discount);
    $('#modalEdit').modal('show');
}
function reset() {
    //隐藏错误提示框
    $('.alert-danger').css("display", "none");
    //清空数据
    $('#password').val('');
    $('#passwordEdit').val('');
    $('#userName').val('');
    $('#userId').val(0);
}
function couponDel() {

}