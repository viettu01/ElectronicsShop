<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="productURL" value="/admin/product/list"/>
<c:url var="productAPI" value="/api/product"/>
<html>
<head>
    <title>Danh sách sản phẩm</title>
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

<form action="<c:url value='/admin/product/list' />" id="formSubmit" method="get">
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col-sm-12 col-md-6" style="margin: auto">
                                    <strong class="card-title">Danh sách sản phẩm</strong>
                                </div>
                                <div class="col-sm-12 col-md-6">
                                    <button id="btnDelete" type="button" class="btn btn-dark btn-sm float-right"
                                            role="button" title="Xóa sản phẩm" onclick="warningBeforeDelete()">
                                        <i class="fa fa-trash"></i>
                                    </button>

                                    <a class="btn btn-dark btn-sm float-right" role="button" title="Thêm sản phẩm"
                                       href="<c:url value="/admin/product/edit" />" style="margin-right: 5px">
                                        <i class="fa fa-plus"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div id="bootstrap-data-table-export_wrapper"
                                 class="dataTables_wrapper dt-bootstrap4 no-footer">
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
                                                <th class="sorting">Tên</th>
                                                <th class="sorting">Danh mục</th>
                                                <th class="sorting">Giá</th>
                                                <th style="width: 6%;"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${model.listResult}" var="item" varStatus="loop">
                                                <tr>
                                                    <td>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                   value="${item.id}" id="checkbox_${item.id}">
                                                            <label class="form-check-label"></label>
                                                        </div>
                                                    </td>
                                                    <td class="sorting_1">
                                                            ${item.id}
                                                    </td>
                                                    <td>${item.name}</td>
                                                    <td>
                                                            ${item.categoryCode}
                                                    </td>
                                                    <td>
                                                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                          value="${item.price}"/>
                                                    </td>
                                                    <td>
                                                        <c:url var="updateProductURL" value="/admin/product/edit">
                                                            <c:param name="id" value="${item.id}"/>
                                                        </c:url>
                                                        <a href="${updateProductURL}">
                                                            <i class="fa fa-pencil"></i>
                                                        </a>
                                                        <a href="<c:url value="/admin/product/detail?id=${item.id}" />"><i
                                                                class="fa fa-eye"></i></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="font-weight-bold text-success text-center"
                                             style="margin-bottom: 10px">
                                            ${result}
                                        </div>
                                        <div class="font-weight-bold text-danger" style="margin-bottom: 10px">
                                            ${error}
                                        </div>
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
                        swal("Chưa sản phẩm nào được chọn");
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
            url: '${productAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${productURL}?page=1&limit=10&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${productURL}?page=1&limit=10&message=error_system";
            }
        });
    }
</script>
</body>
</html>