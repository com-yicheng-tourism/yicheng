<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <#include "base.ftl" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>lou.springboot | 用户管理页</title>
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
                            <!--                  <div class="grid-btn">-->
                            <!--                    <button class="btn btn-info" onclick="userAdd()"><i-->
                            <!--                            class="fa fa-plus"></i>&nbsp;新增-->
                            <!--                    </button>-->
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
                        <h6 class="modal-title" id="modalAddTitle">用户添加</h6>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <div class="alert alert-danger add-error-info">错误信息展示栏。</div>
                            </div>
                            <div class="form-group">
                                <label for="userName" class="control-label">用户名:</label>
                                <input type="text" class="form-control" id="userName" autocomplete="off" >
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
                        <h6 class="modal-title" id="modalEditTitle">密码编辑</h6>
                    </div>
                    <div class="modal-body">
                        <form>
                            <input type="hidden" id="userId" value="0">
                            <div class="form-group">
                                <div class="alert alert-danger edit-error-info">错误信息展示栏。</div>
                            </div>
                            <div class="form-group">
                                <label for="passwordEdit" class="control-label">新密码:</label>
                                <input type="text" class="form-control" id="passwordEdit">
                            </div>
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
<script src="/dist/js/user.js"></script>
</body>
</html>