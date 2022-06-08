<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="userURL" value="/admin/user/list"/>
<c:url var="userAPI" value="/api/user"/>
<html>
<head>
    <title>Danh sách người dùng</title>
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

<form action="<c:url value='/admin/user/list' />" id="formSubmit" method="get">
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col-sm-12 col-md-6 m-auto">
                                    <strong class="card-title">Danh sách người dùng</strong>
                                </div>
                                <div class="col-sm-12 col-md-6">
                                    <button id="btnDelete" type="button" class="btn btn-dark btn-sm float-right"
                                            role="button" title="Xóa người dùng" onclick="warningBeforeDelete()">
                                        <i class="fa fa-trash"></i>
                                    </button>

                                    <a class="btn btn-dark btn-sm float-right mr-1" role="button" title="Thêm người dùng"
                                       href="<c:url value="/admin/user/edit" />">
                                        <i class="fa fa-plus"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div id="bootstrap-data-table-export_wrapper"
                                 class="dataTables_wrapper dt-bootstrap4 no-footer">
                                <div class="row">
                                    <div class="col-sm-12 col-md-6">
                                        <div class="dataTables_length" id="bootstrap-data-table-export_length"><label>Show
                                            <select name="bootstrap-data-table-export_length"
                                                    aria-controls="bootstrap-data-table-export"
                                                    class="custom-select custom-select-sm form-control form-control-sm">
                                                <option value="10">10</option>
                                                <option value="25">25</option>
                                                <option value="50">50</option>
                                                <option value="-1">All</option>
                                            </select> entries</label></div>
                                    </div>
                                    <div class="col-sm-12 col-md-6">
                                        <div id="bootstrap-data-table-export_filter" class="dataTables_filter"><label>Search:<input
                                                type="search" class="form-control form-control-sm" placeholder=""
                                                aria-controls="bootstrap-data-table-export"></label></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <c:if test="${not empty message}">
                                            <div class="alert alert-${alert}">
                                                    ${message}
                                                <button type="button" class="close" data-dismiss="alert"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>
                                        </c:if>
                                        <table id="bootstrap-data-table-export"
                                               class="table table-striped table-bordered dataTable no-footer"
                                               role="grid" aria-describedby="bootstrap-data-table-export_info">
                                            <thead>
                                            <tr role="row">
                                                <th>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="checkbox" value=""
                                                               id="checkAll" title="Chọn tất cả">
                                                        <label class="form-check-label" for="checkAll"></label>
                                                    </div>
                                                </th>
                                                <th class="sorting_asc">ID</th>
                                                <th class="sorting">Tên đăng nhập</th>
                                                <th class="sorting">Họ tên</th>
                                                <th class="sorting">Ngày tạo</th>
                                                <th class="sorting">Ngày sửa</th>
                                                <th class="sorting">Tình trạng</th>
                                                <th style="width: 6%;"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${model.listResult}" var="model" varStatus="loop">
                                                <tr role="row" class="odd">
                                                    <td>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                   value="${model.id}" id="checkbox_${model.id}"/>
                                                            <label class="form-check-label"></label>
                                                        </div>
                                                    </td>
                                                    <td class="sorting_1">${model.id}</td>
                                                    <td>${model.userName}</td>
                                                    <td>${model.fullName}</td>
                                                    <td>${model.createdAt}</td>
                                                    <td>${model.updatedAt}</td>
                                                    <td>
                                                        <c:if test="${model.status == 1}">
                                                            <div class="text-success">
                                                                Bình thường
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${model.status == 0}">
                                                            <div class="text-danger">
                                                                Khóa
                                                            </div>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a href="<c:url value="/admin/user/edit?id=${model.id}"/>">
                                                            <i class="fa fa-pencil"></i>
                                                        </a>
                                                        <a href="<c:url value="/admin/user/detail?id=${model.id}"/>">
                                                            <i class="fa fa-eye"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12 col-md-12">
                                        <div class="dataTables_paginate paging_simple_numbers">
                                            <ul class="pagination justify-content-center" id="pagination"></ul>
                                            <input type="hidden" value="" id="page" name="page"/>
                                            <input type="hidden" value="" id="limit" name="limit"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- .animated -->
    </div><!-- .content -->
</form>
<script>
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#limit').val(10);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });

    function warningBeforeDelete() {
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        swal({
            title: "Xác nhận xóa?",
            text: "Bạn có chắc chắn muốn xóa hay không!",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Xác nhận!",
            cancelButtonText: "Hủy bỏ!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
            preConfirm: function () {
                return new Promise(function (resolve) {
                    if (ids.length === 0) {
                        swal("Chưa danh mục nào được chọn");
                        console.log("true:" + ids.length);
                    } else {
                        console.log("false:" + ids.length);
                        swal("Deleted!", "Your imaginary file has been deleted.", "success");
                        deleteProduct(ids);
                    }
                })
            },
            allowOutsideClick: false,
        });
    }

    function deleteProduct(data) {
        $.ajax({
            url: '${userAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${userURL}?page=1&limit=10&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${userURL}?page=1&limit=10&message=error_system";
            }
        });
    }
</script>
</body>
</html>
