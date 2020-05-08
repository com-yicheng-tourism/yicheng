<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <#include "base.ftl" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>lou.springboot | 用户管理页</title>
        <style>
            body{padding: 20px;}
            .demo-input{padding-left: 10px; height: 38px; min-width: 262px; line-height: 38px; border: 1px solid #e6e6e6;  background-color: #fff;  border-radius: 2px;}
            .demo-footer{padding: 50px 0; color: #999; font-size: 14px;}
            .demo-footer a{padding: 0 5px; color: #01AAED;}
            .round_icon{
                width: 34px;
                height: 34px;
                display: flex;
                border-radius: 50%;
                align-items: center;
                justify-content: center;
                overflow: hidden;
                margin: auto;
                left: 50%;
                right: 50%;
                top: 50%;
                bottom: 50%;
                text-align: center;
            }
        </style>
        <link rel="stylesheet" href="/dist/css/laydate/theme/default/laydate.css">
        <@style/>
    </head>
    <body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
        <div class="wrapper">

            <!-- Navbar -->
            <@header/>

            <!-- /.navbar -->
            <!-- Main Sidebar Container -->
            <@menu/>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <div class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1 class="m-0 text-dark">用户管理页</h1>
                            </div><!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="index.html">主页</a></li>
                                    <li class="breadcrumb-item active">用户管理页</li>
                                </ol>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>

                <!-- Main content -->
                <div class="content">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <div style="float: left;width: 50%;text-align: left">
                                        <input type="text" class="form-control select2-hidden-accessible"
                                               id="find_keyWords" placeholder="关键字"
                                               aria-hidden="true" style="width: 50%;display: inline">

                                        <select class="form-control custom-select" id="find_department" style="width: 49%;display: inline;margin-top: -6px">
                                            <option selected="" disabled=""><font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">选择身份</font></font></option>
                                            <option><font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">管理员</font></font></option>
                                            <option><font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">租户</font></font></option>
                                            <option ><font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">游客</font></font></option>
                                        </select>
                                    </div>
                                    <div style="width: 50%;float: right;text-align: right">
                                        <button class="btn btn-block btn-default" onclick="userAdd()" style="display: inline-block;width: 15%;"><i class="fa fa-plus"></i>&nbsp;新增</button>
                                        <button class="btn btn-block btn-info" onclick="userEdit()" style="display: inline-block;width: 15%;margin-right: 5%;margin-top: 0px;"><i
                                                    class="glyphicon glyphicon-save	"></i>&nbsp;导出
                                        </button>
                                    </div>
                                    <!--                  <input type="text" class="form-control select2-hidden-accessible test1"-->
                                    <!--                         id="find_date_start" placeholder="开始日期"-->
                                    <!--                         aria-hidden="true" style="width: 20%;display: inline">-->
                                    <!--                  <input type="text" class="form-control select2-hidden-accessible test1"-->
                                    <!--                         id="find_date_end" placeholder="结束日期"-->
                                    <!--                         aria-hidden="true" style="width: 20%;display: inline">-->
                                </div>
                                <div class="card-body">
                                    <!--                  <div class="grid-btn">-->
                                    <!--                    <button class="btn btn-info" onclick="userAdd()"><i class="fa fa-plus"></i>&nbsp;新增</button>-->
                                    <!--                    <button class="btn btn-warning" onclick="userEdit()"><i-->
                                    <!--                            class="fa fa-edit"></i>&nbsp;编辑-->
                                    <!--                    </button>-->
                                    <!--                    <button class="btn btn-danger" onclick="userDel()"><i-->
                                    <!--                            class="fa fa-remove"></i>&nbsp;删除-->
                                    <!--                    </button>-->
                                    <!--                  </div>-->
                                    <table id="jqGrid" class="table table-bordered">
                                    </table>
                                    <div id="jqGridPager"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                <h6 class="modal-title" id="modalAddTitle">用户编辑</h6>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <div class="form-group">
                                        <div class="alert alert-danger add-error-info">错误信息展示栏。</div>
                                    </div>
                                    <div class="form-group">
                                        <label for="userName" class="control-label">用户名:</label>
                                        <input type="text" class="form-control" id="userName" autocomplete="off">
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="control-label">密码:</label>
                                        <input type="text" class="form-control" id="password">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="saveButton">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.modal -->
            </div>

            <div class="content">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="modalEditLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                <h6 class="modal-title" id="modalEditTitle">用户编辑</h6>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <input type="hidden" id="userId" value="0">
                                    <div class="form-group">
                                        <div class="alert alert-danger edit-error-info">错误信息展示栏。</div>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control select2-hidden-accessible"
                                               id="edit_username" placeholder="用户名" disabled="disabled"
                                               aria-hidden="true">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="edit_mail" placeholder="邮箱">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control test1" id="edit_birthday" placeholder="请选择日期" >
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="edit_address" placeholder="地址">
                                    </div>
                                    <!--                  <div class="form-group">-->
                                    <!--                    <input type="text" class="form-control" id="edit_type" placeholder="身份">-->
                                    <!--                  </div>-->
                                    <div class="form-group">
                                        <label><font style="vertical-align: inherit;"><font
                                                        style="vertical-align: inherit;">昵称</font></font></label>
                                        <input type="text" class="form-control" id="edit_nickName" placeholder="昵称">
                                    </div>
                                    <div class="form-group">
                                        <label><font style="vertical-align: inherit;"><font
                                                        style="vertical-align: inherit;">身份</font></font></label>
                                        <select class="form-control custom-select" id="edit_type">
                                            <option selected="" disabled=""><font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">选择一个</font></font></option>
                                            <option><font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">管理员</font></font></option>
                                            <option><font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">租户</font></font></option>
                                            <option selected=""><font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">游客</font></font></option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <!--                    <label for="dtp_input1" class="col-md-2 control-label">DateTime Picking</label>-->

                                        <!--                                        <input type="text" placeholder="请选择时间" id="time">-->
                                        <!--                                        <input type="text" placeholder="请选择日期和时间" id="datetime">-->
                                    </div>
                                    <!--                  <div class="form-group">-->
                                    <!--                    <label for="passwordEdit" class="control-label">新密码:</label>-->
                                    <!--                    <input type="text" class="form-control" id="passwordEdit">-->
                                    <!--                  </div>-->
                                    <!--                  <div class="form-group">-->
                                    <!--                    <label for="passwordEdit" class="control-label">新密码:</label>-->
                                    <!--                    <input type="text" class="form-control" id="passwordEdit">-->
                                    <!--                  </div>-->
                                    <!--                  <div class="form-group">-->
                                    <!--                    <label for="passwordEdit" class="control-label">新密码:</label>-->
                                    <!--                    <input type="text" class="form-control" id="passwordEdit">-->
                                    <!--                  </div>-->
                                    <!--                  <div class="form-group">-->
                                    <!--                    <label for="passwordEdit" class="control-label">新密码:</label>-->
                                    <!--                    <input type="text" class="form-control" id="passwordEdit">-->
                                    <!--                  </div>-->
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="editButton">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.modal -->
            </div>

            <!-- /.content-wrapper -->
            <@footer/>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- /.control-sidebar -->
        </div>

        <!-- ./wrapper -->
        <@jsFile/>


    </body>
</html>