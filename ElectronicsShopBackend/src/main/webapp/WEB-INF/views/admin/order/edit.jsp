<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="productURL" value="/admin/product/list"/>
<c:url var="editProductURL" value="/admin/product/edit"/>
<c:url var="productAPI" value="/api/product"/>
<html>
<head>
    <title>Thông tin đơn hàng</title>
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
                        <strong class="card-title">
                            Thông tin đơn hàng
                        </strong>
                    </div>
                    <form:form id="formSubmit" action="edit" cssClass="form-horizontal" method="post" modelAttribute="model">
                        <div class="card-body card-block">
                            <c:if test="${not empty message}">
                                <div class="alert alert-${alert}">
                                        ${message}
                                </div>
                            </c:if>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Họ tên
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <label class="form-control-label">
                                            ${model.fullName}
                                    </label>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Số điện thoại
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <label class="form-control-label">
                                            ${model.phone}
                                    </label>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Email
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <label class="form-control-label">
                                            ${model.email}
                                    </label>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Địa chỉ
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <label class="form-control-label">
                                            ${model.address}
                                    </label>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Ghi chú
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <label class="form-control-label">
                                            ${model.note}
                                    </label>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Danh sách sản phẩm
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <table id="bootstrap-data-table-export"
                                           class="table table-striped table-bordered dataTable no-footer"
                                           role="grid" aria-describedby="bootstrap-data-table-export_info">
                                        <thead>
                                        <tr role="row">
                                            <th>Tên sản phẩm</th>
                                            <th>Số lượng</th>
                                            <th>Giá</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${model.orderDetails}" var="item" varStatus="loop">
                                            <tr>
                                                <td>${item.product.name}</td>
                                                <td>${item.quantity}</td>
                                                <td>
                                                    <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                      value="${item.product.price}"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Tổng tiền
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <label class="form-control-label">
                                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                                          value="${model.priceTotal}"/>
                                    </label>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Tình trạng
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <label class="form-control-label">
                                        <div class="form-check form-check-inline">
                                            <form:radiobutton path="status" cssClass="form-check-input" value="0"/>
                                            <label class="form-check-label">Chờ xác nhận</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <form:radiobutton path="status" cssClass="form-check-input" value="1"/>
                                            <label class="form-check-label">Đã xác nhận</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <form:radiobutton path="status" cssClass="form-check-input" value="2"/>
                                            <label class="form-check-label">Đang giao hàng</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <form:radiobutton path="status" cssClass="form-check-input" value="3"/>
                                            <label class="form-check-label">Giao hàng thành công</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <form:radiobutton path="status" cssClass="form-check-input" value="4"/>
                                            <label class="form-check-label">Đã nhận hàng</label>
                                        </div>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <form:hidden path="id"/>
                        <div class="card-footer text-center">
                            <button type="submit" class="btn btn-primary btn-sm" id="btnAddOrUpdateNew">
                                <i class="fa fa-ban"></i> Cập nhật
                            </button>

                            <a class="btn btn-primary btn-sm" href="<c:url value="/admin/order/list?page=1&limit=10" />"
                               role="button">
                                <i class="fa fa-reply"></i> Quay lại
                            </a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div>
<script>
    var editor = '';
    $(document).ready(function () {
        editor = CKEDITOR.replace('description');
    });

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#detailAvatar').attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }

    <%--$('#btnAddOrUpdateNew').click(function (e) {--%>
    <%--    e.preventDefault();--%>
    <%--    var formData = $('#formSubmit').serializeArray();--%>
    <%--    var data = {};--%>
    <%--    $.each(formData, function (i, v) {--%>
    <%--        data["" + v.name + ""] = v.value;--%>
    <%--    });--%>
    <%--    var id = $('#productId').val();--%>
    <%--    if (id === "") {--%>
    <%--        addProduct(data);--%>
    <%--    } else {--%>
    <%--        updateProduct(data);--%>
    <%--    }--%>
    <%--    console.log(formData);--%>
    <%--});--%>

    <%--function addProduct(data) {--%>
    <%--    $.ajax({--%>
    <%--        url: '${productAPI}',--%>
    <%--        type: 'POST',--%>
    <%--        contentType: 'application/json',--%>
    <%--        data: JSON.stringify(data),--%>
    <%--        dataType: 'json',--%>
    <%--        success: function (result) {--%>
    <%--            window.location.href = "${editProductURL}?id=" + result.id + "&message=insert_success";--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            window.location.href = "${productURL}?page=1&limit=10&message=error_system";--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>

    <%--function updateProduct(data) {--%>
    <%--    $.ajax({--%>
    <%--        url: '${productAPI}',--%>
    <%--        type: 'PUT',--%>
    <%--        contentType: 'application/json',--%>
    <%--        data: JSON.stringify(data),--%>
    <%--        dataType: 'json',--%>
    <%--        success: function (result) {--%>
    <%--            window.location.href = "${editProductURL}?id=" + result.id + "&message=update_success";--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            window.location.href = "${editProductURL}?id=" + error.id + "&message=error_system";--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>
</script>
</body>
</html>