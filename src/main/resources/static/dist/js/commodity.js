$(function () {
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
    // console.log(userType);
    let qryType = JSON.parse(sessionStorage.getItem("qryType"));

    if (userType == "3"){
        init2();
    }else if ( userType == "1" && qryType == "0"){
        init3();
    }else if (userType == "1"){
        init4();
    }else {
     $('#exportBtn').css('display','block');
     $('#commodityState').css('display','block');
     $('#labState').css('display','block');
     $('#addBtn').css('display','block');
     init();
 }


    $(window).resize(function () {
        $("#commodityTable").setGridWidth($(".card-body").width());
    });
});

function toSearch(){
    var commodityName = $("#commodityName").val();
    var commodityState = $("#commodityState").val();
    console.log(commodityState)
    $("#commodityTable").jqGrid('setGridParam',{
        url:'commodity/query',
        postData: {
            commodityName : commodityName,
            commodityState : commodityState
        },
        page:1
    }).trigger("reloadGrid");
}
function init4(){
    // var commodityName = $("#commodityName").val();
    // var commodityState = $("#commodityState").val();
    let name = JSON.parse(sessionStorage.getItem("userId"));
    var userType = name.type;
    let qryType = JSON.parse(sessionStorage.getItem("qryType"));
    let userName="";
    var storeNumber="";
    if (qryType == "1"){
        let item = sessionStorage.getItem("user_detail");
        let userInfo="";
        if (item != null && item != ""){
            userInfo = JSON.parse(item);
        }
        if (userInfo.userName != null && userInfo.userName != ""){
            userName = userInfo.userName;
            sessionStorage.setItem("user_detail",name.userName);
        }else {
            userName = name.userName;
            console.log(userName)
        }
    }else if (qryType == "2"){
        let rowData = sessionStorage.getItem("store_detail");
        let storeInfo = JSON.parse(rowData);

        if (storeInfo != null && storeInfo.storeNumber != ""){
            storeNumber = storeInfo.storeNumber;
        }
    }else if (qryType == "0"){
        userName="";
        storeNumber=""
    }
    // var data={
    //     "storeNumber" : storeNumber,
    //     "keyWords" : "",
    //     "userId" : userName
    //     // "commodityName" : commodityName,
    //     // "commodityState" : commodityState
    // };
    // console.log("data:",data)
    $("#commodityTable").jqGrid({
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
            {label: '操作', name: 'state', index: 'state', width: 80,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}
        ],
        height: 600,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        multiselect: false,
        autowidth: true,
        pager: "#commodityPager",
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
        postData : {storeNumber : storeNumber,
            keyWords : "",
            userId: userName},
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#commodityTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    function cmgStateFormat(grid, rows) {
        // let qryType = JSON.parse(sessionStorage.getItem("qryType"));
        // if (userType == '1') {
        //     return "<button class=\"btn btn-info\" onclick=\"toCommodityEdit()\"><i class=\"btn-outline-primary btn-xs\"></i>编辑</button>"+
        //         "<button class=\"btn btn-danger\" onclick=\"toDelete()\"><i class=\"fa fa-plus\"></i>删除</button>";
        // } else if(userType == '2') {
        //     return  "<button class='btn btn-warning ' onclick=\"toMeal()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>套餐</button> " +
        //         "<button  class=\"btn btn-info\"  onclick=\"toCommodityEdit()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>编辑</button>"+
        //         "<button class=\"btn btn-info\" onclick=\"toCommodityMain()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>详情</button>"
        // }
        // if (qryType == "0"){
        //     return "<button class=\"btn btn-info\" onclick=\"toCommodityEdit()\"><i class=\"btn-outline-primary btn-xs\"></i>配置</button>"+
        //         "<button class=\"btn btn-danger\" onclick=\"toDelete()\"><i class=\"fa fa-plus\"></i>删除</button>";
        // }
    };
    function typeFormat(type){
        return type == "0" ? "已上架" : type=="1"?"已下架":"待审核";
    }
}

function init3(){
    var commodityName = $("#commodityName").val();
    var commodityState = $("#commodityState").val();
    $("#commodityTable").jqGrid({
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
            {label: '操作', name: 'state', index: 'state', width: 80,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}
        ],
        height: 600,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        multiselect: false,
        autowidth: true,
        pager: "#commodityPager",
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
            commodityName : commodityName,
            commodityState : commodityState
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#commodityTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    function cmgStateFormat(grid, rows) {
        return "<button class=\"btn btn-info\" onclick=\"toCommodityEdit()\"><i class=\"btn-outline-primary btn-xs\"></i>强制</button>"+
            "<button class=\"btn btn-danger\" onclick=\"toDelete()\"><i class=\"fa fa-plus\"></i>删除</button>";
    };
    function typeFormat(type){
        return type == "0" ? "已上架" : type=="1"?"已下架":"待审核";
    }
}

function init2(){
    var commodityName = $("#commodityName").val();
    var commodityState = $("#commodityState").val();

    $("#commodityTable").jqGrid({
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
            {label: '操作', name: 'state', index: 'state', width: 80,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}
        ],
        height: 600,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        multiselect: false,
        autowidth: true,
        pager: "#commodityPager",
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
            commodityName : commodityName,
            commodityState : commodityState
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#commodityTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    function cmgStateFormat(grid, rows) {
        return "<button class=\"btn btn-info\" onclick=\"toCommodityMain()\"><i class=\"fa fa-plus\"></i>商品详细</button>"
    }
    function typeFormat(type){
        return type == "0" ? "已上架" : type=="1"?"已下架":"待审核";
    }
}

function init(){
    var commodityName = $("#commodityName").val();
    var commodityState = $("#commodityState").val();
    let name = JSON.parse(sessionStorage.getItem("userId"));
    var userType = name.type;
    let qryType = JSON.parse(sessionStorage.getItem("qryType"));
    let userName="";
    var storeNumber="";
    if (qryType == "1"){
        let item = sessionStorage.getItem("user_detail");
        let userInfo="";
        if (item != null && item != ""){
            userInfo = JSON.parse(item);
        }
        if (userInfo.userName != null && userInfo.userName != ""){
            userName = userInfo.userName;
            sessionStorage.setItem("user_detail",name.userName);
        }else {
            userName = name.userName;
            console.log(userName)
        }
    }else if (qryType == "2"){
        let rowData = sessionStorage.getItem("store_detail");
        let storeInfo = JSON.parse(rowData);

        if (storeInfo != null && storeInfo.storeNumber != ""){
            storeNumber = storeInfo.storeNumber;
        }
    }else if (qryType == "0"){
        userName="";
        storeNumber=""
    }
    // var data={
    //     "storeNumber" : storeNumber,
    //     "keyWords" : "",
    //     "userId" : userName,
    //     "commodityName" : commodityName,
    //     "commodityState" : commodityState
    // };
    // console.log("data:",data)
    $("#commodityTable").jqGrid({
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
            {label: '操作', name: 'state', index: 'state', width: 80,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}
        ],
        height: 600,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        multiselect: false,
        autowidth: true,
        pager: "#commodityPager",
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
        postData : {storeNumber : storeNumber,
            keyWords : "",
            userId: userName,
            commodityName : commodityName,
            commodityState : commodityState},
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#commodityTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    function cmgStateFormat(grid, rows) {
        let qryType = JSON.parse(sessionStorage.getItem("qryType"));
        if (userType == '1') {
            return "<button class=\"btn btn-info\" onclick=\"toCommodityEdit()\"><i class=\"btn-outline-primary btn-xs\"></i>编辑</button>"+
                "<button class=\"btn btn-danger\" onclick=\"toDelete()\"><i class=\"fa fa-plus\"></i>删除</button>";
        } else if(userType == '2') {
            return  "<button class='btn btn-warning ' onclick=\"toMeal()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>套餐</button> " +
                "<button  class=\"btn btn-info\"  onclick=\"toCommodityEdit()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>编辑</button>"+
                "<button class=\"btn btn-info\" onclick=\"toCommodityMain()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>详情</button>"
        }
        if (qryType == "0"){
            return "<button class=\"btn btn-info\" onclick=\"toCommodityEdit()\"><i class=\"btn-outline-primary btn-xs\"></i>配置</button>"+
                "<button class=\"btn btn-danger\" onclick=\"toDelete()\"><i class=\"fa fa-plus\"></i>删除</button>";
        }
    };
    function typeFormat(type){
        return type == "0" ? "已上架" : type=="1"?"已下架":"待审核";
    }
}

function imgFormat(img1) {

    return "<img id='commodityPic' class=\"round_icon\" src='"+img1+"'>"

}

function toMeal() {
    //获取选中行id
    var id = $("#commodityTable").jqGrid("getGridParam", "selrow");
    console.log("rowId",id)
    if (id == null) {
        return;
    }
    //根据选中行id获取行数据
    var rowData = $("#commodityTable").jqGrid('getRowData',id);
    console.log("rowData",rowData);
    //将行数据放到sessionStorage
    sessionStorage.setItem("commodityId",JSON.stringify(rowData));
    // window.parent.document.getElementById("meal").innerHTML = '<object type="text/html" data="/meal" width="100%" height="700px"></object>';
    $('#modalMeal').modal('show');
}

function toCommodityMain(){
    //获取选中行id
    var id = $("#commodityTable").jqGrid("getGridParam", "selrow");
    console.log("rowId",id)
    if (id == null) {
        return;
    }
    //根据选中行id获取行数据
    var rowData = $("#commodityTable").jqGrid('getRowData',id);
    console.log("rowData",rowData);
    //将行数据放到sessionStorage
    sessionStorage.setItem("commodity",JSON.stringify(rowData));
    //执行跳转
    window.parent.parent.document.getElementById("main").innerHTML = '<object type="text/html" data="/commoditymain" width="100%" height="700px"></object>';

}

function toAdd() {
    reset();
    $('#modalAdd').modal('show');
}

function toCommodityEdit() {
    reset();
    var id = $("#commodityTable").jqGrid("getGridParam", "selrow");
    console.log(id);
    if (id == null) {
        return;
    }
    $.get( 'commodity/query', {id: id}, function (result) {
        if (result != null) {
            console.log(result)
            $("#editForm #commodityNameEd").val(result.data.list[0].commodityName);
            $("#editForm #commodityScript").val(result.data.list[0].commodityScript);
            $("#editForm #price").val(result.data.list[0].commodityPrice);
            $("#editForm #state").val(result.data.list[0].commodityState);
            $("#editForm #editId").val(id);
        }
    }, 'json');

    $('#modalEdit').modal('show');
}

function toDelete(){
    var rowid=$("#commodityTable").jqGrid("getGridParam","selrow");
    if (rowid == null) {
        swal("请勾选需要删除的选项", {
            icon: "error",
        });
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if(flag) {
                $.ajax({
                    type: "POST",
                    url: "commodity/delete",
                    contentType: "application/json",
                    data: JSON.stringify(rowid),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#commodityTable").trigger("reloadGrid");
                        } else {
                            swal("删除失败", {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    //验证数据
    if (validObjectForAdd()) {
        //一切正常后发送网络请求
        var commodityName =$("#addForm #commodityNameAdd").val();
        var commodityScript = $("#addForm #commodityScriptAdd").val();
        var price =$("#addForm #priceAdd").val();
        var state =$("#addForm #stateAdd").val();
        var authorStore = $("#addForm #authorStoreAdd").val();
        var data = {
            "commodityName": commodityName,
            "commodityScript": commodityScript,
            "commodityPrice": price,
            "commodityAuthorNumber": authorStore,
            "storeState": state
        };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'commodity/insert',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (result) {
                console.log(result);//打印服务端返回的数据
                if (result == 0) {
                    swal("保存成功", {
                        icon: "success",
                    });
                    $('#modalAdd').modal('hide');
                    reload();
                }
                else {
                    swal("新增失败", {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                reset();
                swal("操作失败", {
                    icon: "error",
                });
            }
        });

    }
});

//绑定modal上的编辑按钮
$('#editButton').click(function () {
    //验证数据
    if (validObjectForEdit()) {
        //一切正常后发送网络请求
        var  id=  $("#editForm #editId").val();
        var commodityName =$("#editForm #commodityNameEd").val();
        var commodityScript = $("#editForm #commodityScript").val();
        var price =$("#editForm #price").val();
        var state =$("#editForm #state").val();
        var authorStore = $("#editForm #authorStore").val();
        var data = {
            "id": id,
            "commodityName": commodityName,
            "commodityScript": commodityScript,
            "commodityPrice": price,
            "commodityAuthorNumber": authorStore,
            "storeState": state
        };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'commodity/update',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (result) {
                console.log(result);//打印服务端返回的数据
                if (result == 0) {
                    swal("修改成功", {
                        icon: "success",
                    });
                    $('#modalEdit').modal('hide');
                    reload();
                }
                else {
                    swal("修改失败", {
                        icon: "error",
                    });
                }
                ;
            },
        });

    }
});




/**
 * 数据验证
 */
function validObjectForEdit() {
    var commodityName =$("#editForm #commodityNameEd").val();
    var price =$("#editForm #price").val();
    var state =$("#editForm #state").val();
    var authorStore = $("#editForm #authorStore").val();
    if (isNull(commodityName) || isNull(price) || isNull(state) || isNull(authorStore)) {
        showErrorInfo("数据错误！");
        return false;
    }
    return true;
}


function isNull(obj) {
    if (obj == null || obj == undefined || obj.trim() == "") {
        return true;
    }
    return false;
}

function showErrorInfo(info) {
    $('.alert-danger').css("display", "block");
    $('.alert-danger').html(info);
}

/**
 * 数据验证
 */
function validObjectForAdd() {
    var commodityName =$("#addForm #commodityNameAdd").val();
    var price =$("#addForm #priceAdd").val();
    var state =$("#addForm #stateAdd").val();
    var authorStore = $("#addForm #authorStoreAdd").val();
    if (isNull(commodityName) || isNull(price) || isNull(state) || isNull(authorStore)) {
        showErrorInfo("数据错误！");
        return false;
    }
    return true;
}

/**
 * 重置
 */
function reset() {
    //隐藏错误提示框
    $('.alert-danger').css("display", "none");
    //清空数据
    $('#commodityName').val('');
    $('#commodityState').val('');
}

/**
 * commodityTable重新加载
 */
function reload() {
    reset();
    var page = $("#commodityTable").jqGrid('getGridParam', 'page');
    $("#commodityTable").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}