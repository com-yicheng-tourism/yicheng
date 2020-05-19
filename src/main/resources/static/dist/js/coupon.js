$(function () {
    init();
    function toAdd() {
        $('#modalAdd').show();
    }
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

    let rowData = sessionStorage.getItem("store_detail");
    let storeInfo = JSON.parse(rowData);
    var storeId="";
    if (storeInfo != null && storeInfo.storeNumber !== ""){
        storeId = storeInfo.id;
        console.log(storeId);
    }

    $("#couponTable").jqGrid({
        url: 'coupon/qry',
        datatype: "json",
        colModel: [
            {label: '优惠券id', name: 'serialCode', index: 'serialCode', hidden: true, width: 50, key: true},
            {label: '优惠名称', name: 'name', index: 'name', hidden: true, width: 50},
            {label: '优惠券类型', name: 'useType', index: 'useType', width: 30, formatter: typeUseFormat},
            {label: '店铺id', name: 'storeId', index: 'storeId', width: 50},
            {label: '优惠类型', name: 'couponType', index: 'couponType',  width: 30,formatter: typeCouponFormat},
            {label: '满足金额', name: 'reachedAmount', index: 'reachedAmount', sortable: false, align: "center", width: 80},
            {label: '满减金额', name: 'minusAmount', index: 'minusAmount', sortable: false, align: "center", width: 80,},
            {label: '立减金额', name: 'reduceAmount', index: 'reduceAmount', sortable: false, align: "center", width: 80},
            {label: '折扣率', name: 'discount', index: 'discount', sortable: false, align: "center", width: 80},
            {
                label: '操作',
                name: 'state',
                index: 'state',
                width: 80,
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
            "storeId":storeId,
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
            return "<button class=\"btn btn-info\" onclick=\"toStoreEdit()\"><i class=\"fa fa-plus\"></i>编辑</button>" +
                "<button class=\"btn btn-info\" onclick=\"toStoreMain()\"><i class=\"fa fa-plus\"></i>进入店铺</button>"
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