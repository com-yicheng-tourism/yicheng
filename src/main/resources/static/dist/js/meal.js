$(function () {
    init();
});

function init(){
    let rowData = sessionStorage.getItem("commodityId");
    let commodityInfo = JSON.parse(rowData);
    $("#mealTable").jqGrid({
        url: 'meal/query',
        datatype: "json",
        colModel: [
            {label: '套餐id', name: 'id', index: 'id', hidden:true,width: 50, key: true},
            {label: '商品编号', name: 'commodityId', index: 'commodityId', hidden:true,width: 30},
            {label: '套餐名称', name: 'setMeal', index: 'setMeal', width: 210},
            {label: '价格', name: 'price', index: 'commodityState', sortable: false,align: "center", width: 140},
            {label: '操作', name: 'state', index: 'state', width: 140,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}
        ],
        height: 600,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        multiselect: false,
        autowidth: false,
        pager: "#mealPager",
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
            commodityId : commodityInfo.id,
            keyWords : ""
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#mealTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    function cmgStateFormat(grid, rows) {
        return "<button class='btn btn-warning ' onclick=\"mealEdit()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>编辑</button> " +
            "<button class='btn btn-danger ' onclick=\"mealDel()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>删除</button>";
    };

    $(window).resize(function () {
        $("#mealTable").setGridWidth($(".card-body").width());
    });
}

function mealEdit(){
    reset();
    var id = $("#mealTable").jqGrid("getGridParam", "selrow");
    console.log(id);
    if (id == null) {
        return;
    }
    $.get( 'meal/query', {id: id}, function (result) {
        if (result != null) {
            console.log(result)
            $("#editMealForm #mealName").val(result.data.list[0].setMeal);
            $("#editMealForm #price").val(result.data.list[0].price);
            $("#editMealForm #editId").val(id);
        }
    }, 'json');
    $('#modalMealEdit').modal('show');
}

function mealDel(){
    $('#modalMealDel').modal('show');
}

function toMealAdd(){
    $('#modalMealAdd').modal('show');
}

//绑定modal上的保存按钮
$('#saveMealButton').click(function () {
    //验证数据
    if (validObjectForAdd()) {
        //一切正常后发送网络请求
        let rowData = sessionStorage.getItem("commodityId");
        let commodityInfo = JSON.parse(rowData);
        var id = commodityInfo.id;
        var setMeal =$("#addMealForm #mealName").val();
        var price =$("#addMealForm #price").val();
        var data = {
            "id": id,
            "setMeal": setMeal,
            "price": price
        };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'meal/insert',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (result) {
                console.log(result);//打印服务端返回的数据
                if (result == 0) {
                    swal("保存成功", {
                        icon: "success",
                    });
                    $('#modalMealAdd').modal('hide');
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
$('#saveMealEdit').click(function () {
    //验证数据
    if (validObjectForEdit()) {
        //一切正常后发送网络请求
        var  id=  $("#editMealForm #editId").val();
        var mealName =$("#editMealForm #mealName").val();
        var price =$("#editMealForm #price").val();
        var data = {
            "id": id,
            "setMeal": mealName,
            "price": price,

        };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'meal/update',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (result) {
                console.log(result);//打印服务端返回的数据
                if (result == 0) {
                    swal("修改成功", {
                        icon: "success",
                    });
                    $('#modalMealEdit').modal('hide');
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

function validObjectForEdit() {
    var mealName =$("#editMealForm #mealName").val();
    var price =$("#editMealForm #price").val();
    if (isNull(mealName) || isNull(price)) {
        showErrorInfo("数据错误！");
        return false;
    }
    return true;
}

function validObjectForAdd() {
    var mealName =$("#addMealForm #mealName").val();
    var price =$("#addMealForm #price").val();
    if (isNull(mealName) || isNull(price)) {
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

function reload() {
    reset();
    var page = $("#mealTable").jqGrid('getGridParam', 'page');
    $("#mealTable").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}