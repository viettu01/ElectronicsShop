<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="categoryURL" value="/admin/category/list"/>
<c:url var="categoryAPI" value="/api/category"/>
<html>
<head>
    <title>Danh sách đơn hàng</title>
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

<form action="<c:url value='/admin/order/list' />" id="formSubmit" method="get">
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col-sm-12 col-md-12 mt-auto">
                                    <strong class="card-title">Danh sách đơn hàng</strong>
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
                                            </div>
                                        </c:if>
                                        <table id="bootstrap-data-table-export"
                                               class="table table-striped table-bordered dataTable no-footer"
                                               role="grid" aria-describedby="bootstrap-data-table-export_info">
                                            <thead>
                                            <tr role="row">
                                                <th class="sorting_asc">ID</th>
                                                <th class="sorting">ID User</th>
                                                <th class="sorting">Họ tên</th>
                                                <th class="sorting">SĐT</th>
                                                <th class="sorting">Tổng tiền</th>
                                                <th class="sorting">Tình trạng</th>
                                                <th class="sorting">Ngày tạo</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${model.listResult}" var="model" varStatus="loop">
                                                <tr role="row" class="odd">
                                                    <td class="sorting_1">${model.id}</td>
                                                    <td class="sorting_1">${model.userId}</td>
                                                    <td>${model.fullName}</td>
                                                    <td>${model.phone}</td>
                                                    <td>
                                                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                          value="${model.priceTotal}"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${model.status == 0}">
                                                            <div class="text-danger font-weight-bold">
                                                                Chờ xác nhận
                                                            </div>
                                                        </c:if>
                                                        <div class="text-success font-weight-bold">
                                                            <c:if test="${model.status == 1}">
                                                                Đã xác nhận
                                                            </c:if>
                                                            <c:if test="${model.status == 2}">
                                                                Đang giao hàng
                                                            </c:if>
                                                            <c:if test="${model.status == 3}">
                                                                Giao hàng thành công
                                                            </c:if>
                                                            <c:if test="${model.status == 4}">
                                                                Đã nhận hàng
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                    <td>${model.createdAt}</td>
                                                    <td>
                                                        <a href="<c:url value="/admin/order/edit?id=${model.id}"/>">
                                                            <i class="fa fa-pencil"></i>
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
                                        <div class="dataTables_paginate paging_simple_numbers"
                                             id="bootstrap-data-table-export_paginate">
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
            url: '${categoryAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${categoryURL}?page=1&limit=10&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${categoryURL}?page=1&limit=10&message=error_system";
            }
        });
    }
</script>
</body>
</html>
