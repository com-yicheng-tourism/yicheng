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

    $("#storeTable").jqGrid({
        url: 'store/query',
        datatype: "json",
        colModel: [
            {label: '店铺id', name: 'id', index: 'id', hidden:true,width: 50, key: true},
            {label: '店铺编号', name: 'storeNumber', index: 'storeNumber', hidden:true,width: 50},
            {label: '头像', name: 'storeHead', index: 'storeHead', width: 30,formatter: imgFormat},
            {label: '店铺名称', name: 'storeName', index: 'storeName', width: 50},
            {label: '店主', name: 'nickName', index: 'nickName', sortable: false,align: "center", width: 80},
            {label: '店主手机号', name: 'authorPhone', index: 'authorPhone', sortable: false,align: "center", width: 80},
            {label: '店铺状态', name: 'storeState', index: 'storeState', sortable: false,align: "center", width: 80,formatter:typeFormat},
            {label: '注册时间', name: 'createTime', index: 'createTime', sortable: false,align: "center", width: 80},
            {label: '店铺描述', name: 'storeScript', index: 'storeScript', sortable: false,align: "center", width: 80},
            {label: '操作', name: 'state', index: 'state', width: 80,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}

        ],
        height: 635,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        // rownumbers: true,
        // multiselect: true,
        autowidth: true,
        pager: "#storePager",
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
            keyWords : ""
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#storeTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    function cmgStateFormat(grid, rows) {
        let name = JSON.parse(sessionStorage.getItem("userId"));
        var userType = name.type;
        if (userType == '1') {
            return "<button class=\"btn btn-info\" onclick=\"toStoreEdit()\"><i class=\"fa fa-plus\"></i>编辑</button>"+
                "<button class=\"btn btn-danger\" onclick=\"toDelete()\"><i class=\"fa fa-plus\"></i>删除</button>";
        } else if(userType == '2'){
            return "<button class=\"btn btn-info\" onclick=\"toStoreEdit()\"><i class=\"fa fa-plus\"></i>编辑</button>"+
                "<button class=\"btn btn-info\" onclick=\"toStoreMain()\"><i class=\"fa fa-plus\"></i>进入店铺</button>"
        }else {
            return  "<button class=\"btn btn-info\" onclick=\"toStoreMain()\"><i class=\"fa fa-plus\"></i>进入店铺</button>"
        }

    };
    function typeFormat(type){
        return type == "0" ? "开启" : ( type == "1" ? "关闭" : "封禁中");
    }
    $(window).resize(function () {
        console.log("gaibianledaxiao ")
        $("#storeTable").setGridWidth($(".card-body").width());
    });
});
function imgFormat(storeHead) {

    return "<img id='storeHead' class=\"round_icon\" src='"+storeHead+"'>"

}
function toStoreMain(){
    //获取选中行id
    var id=$('#storeTable').jqGrid('getGridParam','selrow');
    console.log("rowId",id)
    if (id == null) {
        return;
    }
    //根据选中行id获取行数据
    var rowData = $("#storeTable").jqGrid('getRowData',id);
    console.log("rowData",rowData);
    //将行数据放到sessionStorage
    sessionStorage.setItem("store_detail",JSON.stringify(rowData));
    //执行跳转
    window.parent.document.getElementById("main").innerHTML = '<object type="text/html" data="/profileShop" width="100%" height="700px">'
}

function toAdd() {
    reset();
    $('#modalAdd').modal('show');
}

function toStoreEdit() {
    reset();
    var id = $("#storeTable").jqGrid("getGridParam", "selrow");
    console.log(id);
    if (id == null) {
        return;
    }
    $.get( 'store/query', {id: id}, function (result) {
        if (result != null) {
            console.log(result)
            $("#editForm #storeNo").val(result.data.list[0].storeNumber);
            $("#editForm #storeName").val(result.data.list[0].storeName);
            $("#editForm #storeScript").val(result.data.list[0].storeScript);
            $("#editForm #phone").val(result.data.list[0].authorPhone);
            $("#editForm #state").val(result.data.list[0].storeState);
            $("#editForm #editId").val(id);
            sessionStorage.setItem("storeInfo",JSON.stringify(result.data.list[0]))
        }
    }, 'json');

    $('#modalEdit').modal('show');
}

function toDelete(){
    var rowid=$("#storeTable").jqGrid("getGridParam","selrow");
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
                url: "store/deleteStore",
                contentType: "application/json",
                data: JSON.stringify(rowid),
                success: function (r) {
                    if (r.resultCode == 200) {
                        swal("删除成功", {
                            icon: "success",
                        });
                        $("#storeTable").trigger("reloadGrid");
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
        let name = JSON.parse(sessionStorage.getItem("userId"));
        var userId = name.serialId;
        //一切正常后发送网络请求
        var  storeName=  $("#addForm #storeName").val();
        var  storeScript=  $("#addForm #storeScript").val();
        var  phone= $("#addForm #phone").val();
        var  state= $("#addForm #state").val();
        var data = {
            "userId": userId,
            "storeName": storeName,
            "authorPhone": phone,
            "storeScript": storeScript,
            "storeState": state
        };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'store/insertStore',//url
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
        var  storeName=  $("#editForm #storeName").val();
        var  storeScript=  $("#editForm #storeScript").val();
        var  phone= $("#editForm #phone").val();
        var  state= $("#editForm #state").val();
        var data = {
            "id": id,
            "storeName": storeName,
            "authorPhone": phone,
            "storeScript": storeScript,
            "storeState": state
        };
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'store/updateStore',//url
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
    var  storeName=  $("#editForm #storeName").val();
    var  phone= $("#editForm #phone").val();
    var  state= $("#editForm #state").val();
    if (isNull(storeName) || isNull(phone) || isNull(state)) {
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
    var  storeName=  $("#addForm #storeName").val();
    var  phone= $("#addForm #phone").val();
    var  state= $("#addForm #state").val();
    console.log(storeName+"--"+phone+"--"+state);
    if (isNull(storeName) || isNull(phone) || isNull(state)) {
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
    $('#storeName').val('');
    $('#storeAuthor').val('');
}

/**
 * storeTable重新加载
 */
function reload() {
    reset();
    var page = $("#storeTable").storeTable('getGridParam', 'page');
    $("#storeTable").storeTable('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}