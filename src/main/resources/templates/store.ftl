<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <#include "base.ftl" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>lou.springboot | 店铺管理页</title>
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
                        <h1 class="m-0 text-dark">店铺管理页</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="index.html">主页</a></li>
                            <li class="breadcrumb-item active">店铺管理页</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
            <div class="widget-head" style="height: 34px;width:100%;background-color: #f8f8f8;text-shadow: 0px 1px #fff;
                        border-bottom: 1px solid #ccc;color: #666;font-weight: bold;font-size: 13px;padding: 8px 15px;">
                <div class="pull-left">
                    查询条件
                </div>
            </div>
            <div class="widget-content" style="height: 70px;width: 1075px;">
                <div class=" padd" style="box-sizing: border-box">
                    <table class="table border-none" style="width: auto;">
                        <tr>
                            <td>
                                <label for="storeName">店铺名称</label>
                            </td>
                            <td>
                                <input type="text" maxlength="50" class="form-control" name="storeName" id="storeName">
                            </td>
                            <td>
                                <label for="storeAuthor">店主</label>
                            </td>
                            <td>
                                <input type="text" maxlength="50" class="form-control" name="storeAuthor" id="storeAuthor">
                            </td>
                            <td>
                                <button type="button" class="btn btn-primary" id="paramSearch"
                                        onclick="toSearch()">查询
                                </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-default"
                                        onclick="toAdd()">新增
                                </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-default"
                                        onclick="toExport()">导出
                                </button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <!-- Main content -->
        <div class="content">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <table id="storeTable" class="table table-bordered">
                            </table>
                            <div id="stroePager"></div>
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
                        <h6 class="modal-title" id="modalAddTitle">商铺添加</h6>
                    </div>
                    <div class="modal-body">
                        <form id="addForm">
                            <div class="form-group">
                                <div class="alert alert-danger add-error-info">错误信息展示栏。</div>
                            </div>
                            <table class="table table-bordered">
                                <tr>
                                    <td style="width: 120px;">
                                        <label for="param-search-type">店铺名称：<span style="color: red;">*</span></label>
                                    </td>
                                    <td>
                                        <input type="text" name="storeName" id="storeName" class="form-control" maxlength="50"
                                               tips="3" style="height: 35px;" placeholder="店铺名称">
                                    </td>
                                </tr>

                                <tr>
                                    <td style="width: 120px;">
                                        <label for="param-search-type">店铺描述：</label>
                                    </td>
                                    <td>
                                        <input type="text" name="storeScript" id="storeScript" class="form-control" maxlength="200"
                                               tips="3" style="height: 35px;" placeholder="店铺描述">
                                    </td>

                                </tr>
                                <tr>
                                    <td style="width: 120px;">
                                        <label for="param-search-type">联系电话：<span style="color: red;">*</span></label>
                                    </td>
                                    <td>
                                        <input type="text" name="phone" id="phone" class="form-control" maxlength="11"
                                               tips="3" style="height: 35px;" placeholder="联系电话">
                                    </td>
                                </tr>

                                <tr>
                                    <td style="width: 120px;">
                                        <label for="param-search-type">状态：<span style="color: red;">*</span></label>
                                    </td>
                                    <td>
                                        <select name="state" id="state" class="form-control" maxlength="50"
                                                tips="3" style="height: 35px;" placeholder="状态">
                                            <option value="0">开启</option>
                                            <option value="1">关闭</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
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
                        <h6 class="modal-title" id="modalEditTitle">商铺编辑</h6>
                    </div>
                    <div class="modal-body">
                        <form id="editForm">
                            <div class="form-group">
                                <div class="alert alert-danger add-error-info">错误信息展示栏。</div>
                            </div>
                            <table class="table table-bordered">
                                <tr>
                                    <td style="width: 120px;">
                                        <label for="param-search-type">店铺名称：<span style="color: red;">*</span></label>
                                    </td>
                                    <td>
                                        <input type="text" name="storeName" id="storeName" class="form-control" maxlength="50"
                                               tips="3" style="height: 35px;" placeholder="店铺名称">
                                    </td>
                                </tr>

                                <tr>
                                    <td style="width: 120px;">
                                        <label for="param-search-type">店铺描述：</label>
                                    </td>
                                    <td>
                                        <input type="text" name="storeScript" id="storeScript" class="form-control" maxlength="200"
                                               tips="3" style="height: 35px;" placeholder="店铺描述">
                                    </td>

                                </tr>
                                <tr>
                                    <td style="width: 120px;">
                                        <label for="param-search-type">联系电话：<span style="color: red;">*</span></label>
                                    </td>
                                    <td>
                                        <input type="text" name="phone" id="phone" class="form-control" maxlength="11"
                                               tips="3" style="height: 35px;" placeholder="联系电话">
                                    </td>
                                </tr>

                                <tr>
                                    <td style="width: 120px;">
                                        <label for="param-search-type">状态：<span style="color: red;">*</span></label>
                                    </td>
                                    <td>
                                        <select name="state" id="state" class="form-control" maxlength="50"
                                                tips="3" style="height: 35px;" placeholder="状态">
                                            <option value="0">开启</option>
                                            <option value="1">关闭</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
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

    <div class="content">
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="modalEditLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                        <h6 class="modal-title" id="modalEditTitle">删除提示</h6>
                    </div>
                    <div class="modal-body">
                        <p>确认删除选中数据？</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="deleteStoreBtn">确认</button>
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
<script src="/dist/js/store.js"></script>
</body>
</html>