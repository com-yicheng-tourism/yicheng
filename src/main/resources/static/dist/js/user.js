$(function () {
    //隐藏错误提示框
    $('.alert-danger').css("display", "none");

    //modal绑定hide事件
    $('#modalAdd').on('hide.bs.modal', function () {
        reset();
    })
    $('#modalEdit').on('hide.bs.modal', function () {
        reset();
    });

    $("#searchByCondition").click(function(){
        let findType = $('#find-user-type').val();
        if (findType !== null && findType !== ""){
            findType = findType === "管理员" ? "1" : (findType ==="租户" ? "2" : "3");
        }
        let findByBirth = $('.find-birthday').val();
        let sTime;
        let eTime;
        if (findByBirth !== null && findByBirth !==""){
            let births = findByBirth.split(" - ");
            let s1 = births[0].split("/");
            sTime=s1[2]+"-"+s1[0]+"-"+s1[1];
            let s2 = births[1].split("/");
            eTime=s2[2]+"-"+s2[0]+"-"+s2[1];
        }
        // console.log(births)
        $("#jqGrid").jqGrid('setGridParam',{
            url:"user/qry",
            postData:{'keyWords': $('#find_keyWords').val(),
                "type" : findType
                // "startTime" : sTime,
                // "endTime" : eTime
            }, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
    });
    $("#jqGrid").jqGrid({
        url: 'user/qry',
        datatype: "json",
        colModel: [
            {label: '性别', name: 'sex', index: 'sex', width: 50, hidden: true},
            {label: '职务', name: 'job', index: 'job', width: 50, hidden: true},
            {label: '个性签名', name: 'signature', index: 'signature', width: 50, hidden: true},
            {label: '教育经历', name: 'education', index: 'education', width: 50, hidden: true},
            {label: '技能', name: 'skill', index: 'skill', width: 50, hidden: true},
            {label: '用户名', name: 'userName', index: 'userName', width: 50, hidden: true, key: true},
            {label: '头像', name: 'profilePic', index: 'profilePic', width: 20, sortable: false,align: "center",formatter: imgFormat},
            {label: '昵称', name: 'nickName', index: 'nickName', sortable: false,align: "center", width: 50},
            {label: '邮箱', name: 'mail', index: 'mail', sortable: false,align: "center", width: 60},
            {label: '生日', name: 'birthday', index: 'birthday', sortable: false,align: "center", width: 35},
            {label: '身份', name: 'type', index: 'type', sortable: false,align: "center", width: 20,formatter:typeFormat},
            {label: '角色', name: 'roleName', index: 'roleName', sortable: false,align: "center", width: 40},
            {label: '地址', name: 'userAddress', index: 'userAddress', sortable: false,align: "center", width: 60},
            {label: '操作', name: 'state', index: 'state', width: 80,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}
        ],
        height: 600,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        emptyrecords:"<div  style='position: absolute;top: 0%;left: 0%;width: 100%;height: 30px;z-index: 101;padding: 6px;margin: 5px;text-align: center;font-weight: bold;font-size: 14px;background-color: #FFF;border: 2px solid #8EB8D1;color: #E2B018;display: block;'>暂        无        数        据</div>",
        // rownumWidth: 80,
        autowidth : true,
        multiselect: false,
        multiboxonly :true,
        altRows : true,
        shrinkToFit : true,
        onSelectRow: function (rowId, status, e) {
            var lastSel;
            if (rowId == lastSel) {
                $(this).jqGrid("resetSelection");
                lastSel = undefined;
                status = false;
            } else {
                lastSel = rowId;
            }
        },
        beforeSelectRow: function (rowId, e) {
            $(this).jqGrid("resetSelection");
            return true;
        },
        pager: "#jqGridPager",
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
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });


});
function imgFormat(profilePic) {

    return "<img id='userPic' class=\"round_icon\" src='"+profilePic+"'>"

}
function cmgStateFormat(grid, rows) {
    return "<button class='btn btn-info ' onclick=\"userDetail()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>详情</button> " +
        "<button class='btn btn-warning ' onclick=\"userEdit()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>编辑</button> " +
        "<button class='btn btn-danger ' onclick=\"userDel()\" style='width: 46.4px;height: 30.4px;font-size: 14px;padding: 2px 4px;'>删除</button>";
};
function typeFormat(type){
    return type == "1" ? "管理员" : ( type == "2" ? "租户" : "游客");
}
$(window).resize(function () {
    console.log("gaibianledaxiao ")
    $("#jqGrid").setGridWidth($(".card-body").width());
});



function userDetail() {
    //获取选中行id
    var id=$('#jqGrid').jqGrid('getGridParam','selrow');
    console.log("rowId",id)
    if (id == null) {
        return;
    }
    //根据选中行id获取行数据
    var rowData = $("#jqGrid").jqGrid('getRowData',id);
    console.log("rowData",rowData);
    //将行数据放到sessionStorage
    sessionStorage.setItem("user_detail",JSON.stringify(rowData));
    //执行跳转
    window.parent.document.getElementById("main").innerHTML = '<object type="text/html" data="/userDetail" width="100%" height="700px"></object>';

}

function downloadByCondition() {

}
function userEdit() {
    console.log("点击了编辑")
    reset();


    var id=$('#jqGrid').jqGrid('getGridParam','selrow');
    if (id == null) {
        return;
    }
    var rowData = $("#jqGrid").jqGrid('getRowData',id);
    let profilePic = rowData.profilePic;
    let strings = JSON.stringify(profilePic).split("\"");
    let editUserPic = JSON.stringify(strings[6]).replace("\"","").replace("\\\"","").replace("\\","");
    $('#edit-user-pic').attr("src",editUserPic);
    // $('#userId').val(id);
    $('#edit_username').val(rowData.userName);
    $('#edit_nick_name').val(rowData.nickName);
    $('#edit_birthday').val(rowData.birthday);
    $('#edit_type').val(rowData.type);
    $('#edit_role').val(rowData.roleName);
    $('#edit_mail').val(rowData.mail);
    $('#edit_address').val(rowData.userAddress);
    $('#modalEditTitle').html('用户编辑');
    $('#modalEdit').modal('show');
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
    var userType=$('#edit_type').val() == "管理员" ? '1' : ($('#edit_type').val() == "租户" ? "2" : "3");
    var data = {"userName": $('#edit_username').val(), "nickName": $('#edit_nick_name').val()
        ,"birthday":$('#edit_birthday').val(),"type": userType,"userAddress":$('#edit_address').val(),"role" : $('#edit_role').val()};
    $.ajax({
        type: 'POST',//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: 'user/edit',//url
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        beforeSend: function (request) {
            //设置header值
            // request.setRequestHeader("token", getCookie("token"));
        },
        success: function (result) {
            // checkResultCode(result.resultCode);
            console.log(result.code);//打印服务端返回的数据
            if (result.code == "000_000_000") {
                $('#modalEdit').modal('hide');
                //reload
                alert(result.data)
                reload();
            }
        },
        error: function () {
            reset();
            swal(result.message, {
                icon: "error",
            });
        }
    });
});

/**
 * 用户删除
 */
function userDel() {
    var ids=$('#jqGrid').jqGrid('getGridParam','selrow');
    if (ids == null) {
        return;
    }
    // alert("确定要删除吗")
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
 * jqGrid重新加载
 */
function reload() {
    reset();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page,
    }).trigger("reloadGrid");
}