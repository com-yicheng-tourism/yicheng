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
            {label: '店铺id', name: 'id', index: 'id', width: 50, key: true},
            {label: '店铺编号', name: 'storeNumber', index: 'storeNumber', width: 50},
            {label: '店铺名称', name: 'storeName', index: 'storeName', width: 50},
            {label: '店铺描述', name: 'storeScript', index: 'storeScript', sortable: false,align: "center", width: 80},
            {label: '店主手机号', name: 'authorPhone', index: 'authorPhone', sortable: false,align: "center", width: 80},
            {label: '店铺状态', name: 'storeState', index: 'storeState', sortable: false,align: "center", width: 80,formatter:typeFormat},
            {label: '店主', name: 'createBy', index: 'createBy', sortable: false,align: "center", width: 80},
            {label: '操作', name: 'state', index: 'state', width: 80,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}
        ],
        height: 500,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        // rownumbers: true,
        // rownumWidth: 80,
        autowidth: true,
        multiselect: false,
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
            $("#storeTable").closest(".ui-storeTable-bdiv").css({"overflow-x": "hidden"});
        }
    });
    function cmgStateFormat(grid, rows) {
        return "<button class='btn btn-warning ' οnclick=\"change(" + rows.cmgId+")\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>编辑</button> " +
            "<button class='btn btn-danger ' οnclick=\"change(" + rows.cmgId+")\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>删除</button>" ;
    };
    function typeFormat(type){
        return type == "0" ? "开启" : ( type == "1" ? "关闭" : "封禁中");
    }
    $(window).resize(function () {
        console.log("gaibianledaxiao ")
        $("#storeTable").setGridWidth($(".card-body").width());
    });
});

function toAdd() {

}

function toEdit() {

}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    //验证数据
    if (validObjectForAdd()) {
        //一切正常后发送网络请求
        //ajax
        var userName = $("#userName").val();
        var password = $("#password").val();
        var data = {"userName": userName, "password": password};
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'users/save',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            beforeSend: function (request) {
                //设置header值
                request.setRequestHeader("token", getCookie("token"));
            },
            success: function (result) {
                console.log(result);//打印服务端返回的数据
                checkResultCode(result.resultCode);
                if (result.resultCode == 200) {
                    swal("保存成功", {
                        icon: "success",
                    });
                    $('#modalAdd').modal('hide');
                    //reload
                    reload();
                }
                else {
                    swal(result.message, {
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
        var password = $("#passwordEdit").val();
        var id = $("#userId").val();
        var data = {"id": id, "password": password};
        $.ajax({
            type: 'PUT',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'users/updatePassword',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            beforeSend: function (request) {
                //设置header值
                request.setRequestHeader("token", getCookie("token"));
            },
            success: function (result) {
                checkResultCode(result.resultCode);
                console.log(result);//打印服务端返回的数据
                if (result.resultCode == 200) {
                    swal("修改成功", {
                        icon: "success",
                    });
                    $('#modalEdit').modal('hide');
                    //reload
                    reload();
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                reset();
                swal(result.message, {
                    icon: "error",
                });
            }
        });

    }
});

/**
 * 用户删除
 */
function userDel() {
    var ids = getSelectedRows();
    if (ids == null) {
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
                type: "DELETE",
                url: "users/delete",
                contentType: "application/json",
                data: JSON.stringify(ids),
                beforeSend: function (request) {
                    //设置header值
                    request.setRequestHeader("token", getCookie("token"));
                },
                success: function (r) {
                    checkResultCode(r.resultCode);
                    if (r.resultCode == 200) {
                        swal("删除成功", {
                            icon: "success",
                        });
                        $("#storeTable").trigger("reloadGrid");
                    } else {
                        swal(r.message, {
                            icon: "error",
                        });
                    }
                }
            });
        }
    }
);
}


/**
 * 数据验证
 */
function validObjectForAdd() {
    var userName = $('#userName').val();
    if (isNull(userName)) {
        showErrorInfo("用户名不能为空!");
        return false;
    }
    if (!validUserName(userName)) {
        showErrorInfo("请输入符合规范的用户名!");
        return false;
    }
    var password = $('#password').val();
    if (isNull(password)) {
        showErrorInfo("密码不能为空!");
        return false;
    }
    if (!validPassword(password)) {
        showErrorInfo("请输入符合规范的密码!");
        return false;
    }
    return true;
}

/**
 * 数据验证
 */
function validObjectForEdit() {
    var userId = $('#userId').val();
    if (isNull(userId) || userId < 1) {
        showErrorInfo("数据错误！");
        return false;
    }
    var password = $('#passwordEdit').val();
    if (isNull(password)) {
        showErrorInfo("密码不能为空!");
        return false;
    }
    if (!validPassword(password)) {
        showErrorInfo("请输入符合规范的密码!");
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
    $('#password').val('');
    $('#passwordEdit').val('');
    $('#userName').val('');
    $('#userId').val(0);
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