<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Chi tiết người dùng</title>
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
                        <strong class="card-title">Chi tiết người dùng</strong>
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
                                    Tên đăng nhập
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.userName}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Email
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.email}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Họ tên
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.fullName}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Giới tính
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.gender}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Ngày sinh
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    <fmt:formatDate value="${model.birthday}" pattern="dd/MM/yyyy"/>
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Số điện thoại
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.phone}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Địa chỉ
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.address}
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
                                <label class="form-control-label">
                                    <img class="img-thumbnail"
                                         src="<c:url value="/template/images/user/${model.avatar}"/>"
                                         alt="Avatar" style="width: 20%">
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class=" form-control-label">
                                    Công việc
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class=" form-control-label">
                                    ${model.jobs}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class="form-control-label">
                                    Facebook
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class="form-control-label">
                                    ${model.facebook}
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class="form-control-label">
                                    Trạng thái
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <c:if test="${model.status == 0}">
                                    <label class="form-control-label text-danger font-weight-bold">
                                        Khóa
                                    </label>
                                </c:if>
                                <c:if test="${model.status == 1}">
                                    <label class="form-control-label text-success font-weight-bold">
                                        Bình thường
                                    </label>
                                </c:if>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class="form-control-label">
                                    Ngày tạo
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class="form-control-label">
                                    <fmt:formatDate value="${model.createdAt}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                </label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col col-md-3">
                                <label class="form-control-label">
                                    Ngày sửa
                                </label>
                            </div>
                            <div class="col-12 col-md-9">
                                <label class="form-control-label">
                                    <fmt:formatDate value="${model.updatedAt}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="card-footer text-center">
                        <a class="btn btn-primary btn-sm" role="button"
                           href="<c:url value="/admin/user/edit?id=${model.id}"/>">
                            <i class="fa fa-ban"></i> Cập nhật
                        </a>
                        <a class="btn btn-primary btn-sm" role="button"
                           href="<c:url value="/admin/user/list?page=1&limit=10"/>">
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
