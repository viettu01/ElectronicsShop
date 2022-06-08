<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Chi tiết danh mục</title>
</head>
<body>
<div class="breadcrumbs">
    <div class="col-sm-4">
        <div class="page-header float-left">
            <div class="page-title">
                <h1>Dashboard</h1>
            </div>
        </div>
    </div>
    <div class="col-sm-8">
        <div class="page-header float-right">
            <div class="page-title">
                <ol class="breadcrumb text-right">
                    <li><a href="#">Dashboard</a></li>
                    <li><a href="#">Table</a></li>
                    <li class="active">Data table</li>
                </ol>
            </div>
        </div>
    </div>
</div>

<div class="content mt-3">
    <div class="animated fadeIn">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <strong class="card-title">Chi tiết danh mục</strong>
                    </div>
                    <div class="card-body card-block">
                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    ID
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.id}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Tên danh mục
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.name}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Code
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.code}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Avatar
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    <img class="img-thumbnail"
                                         src="<c:url value="/template/images/category/${model.avatar}"/>"
                                         alt="Ảnh danh mục" style="width: 20%">
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class="form-control-label">
                                    Created at
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class="form-control-label">
                                    ${model.createdAt}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class="form-control-label">
                                    Updated at
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class="form-control-label">
                                    ${model.updatedAt}
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="card-footer text-center">
                        <a class="btn btn-primary btn-sm" role="button"
                           href="<c:url value="/admin/category/edit?id=${model.id}"/>">
                            <i class="fa fa-ban"></i> Cập nhật
                        </a>
                        <a class="btn btn-primary btn-sm" role="button"
                           href="<c:url value="/admin/category/list?page=1&limit=10"/>">
                            <i class="fa fa-reply"></i> Quay lại
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div><!-- .content -->

</body>
</html>
