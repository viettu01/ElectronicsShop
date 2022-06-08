<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="productURL" value="/admin/product/list"/>
<c:url var="editProductURL" value="/admin/product/edit"/>
<c:url var="productAPI" value="/api/product"/>
<html>
<head>
    <title>
        <c:if test="${not empty model.id}">
            Sửa thông tin sản phẩm
        </c:if>
        <c:if test="${empty model.id}">
            Thêm sản phẩm
        </c:if>
    </title>
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
                            <c:if test="${not empty model.id}">
                                Sửa thông tin sản phẩm
                            </c:if>
                            <c:if test="${empty model.id}">
                                Thêm sản phẩm
                            </c:if>
                        </strong>
                    </div>
                    <form:form id="formSubmit" action="edit" cssClass="form-horizontal" method="post"
                               enctype="multipart/form-data" modelAttribute="model" onsubmit="return validateFormProduct()">
                        <div class="card-body card-block">
                            <c:if test="${not empty message}">
                                <div class="alert alert-${alert}">
                                        ${message}
                                </div>
                            </c:if>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="name" class="form-control-label">
                                        Tên sản phẩm <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input cssClass="form-control" path="name" id="name"/>
                                    <div id="error-name-product" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="categoryCode" class="form-control-label">
                                        Danh mục <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:select cssClass="form-control" path="categoryCode" id="categoryCode">
                                        <form:option value="0" label="-- Chọn danh mục --"/>
                                        <form:options items="${categories}"/>
                                    </form:select>
                                    <div id="error-category-product"
                                         class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="avatar" class=" form-control-label">
                                        Ảnh <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input path="avatar" type="file" cssClass="form-control-file"
                                                onchange="readURL(this);"/>
                                    <img id="detailAvatar" class="image img-thumbnail mt-1" width="20%"
                                         src="<c:url value="/template/images/product/${model.avatar}"/>"
                                         alt="Ảnh sản phẩm"/>
                                    <div id="error-avatar-product" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="price" class="form-control-label">
                                        Giá <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input cssClass="form-control" id="price" path="price"/>
                                    <div id="error-price-product" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="description" class="form-control-label">
                                        Mô tả <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:textarea path="description" cssClass="form-control" id="description"
                                                   rows="9"/>
                                    <div id="error-des-product" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>
                        </div>
                        <form:hidden path="id" id="productId"/>
                        <div class="card-footer text-center">
                            <c:if test="${not empty model.id}">
                                <button type="submit" class="btn btn-primary btn-sm" id="btnAddOrUpdateNew">
                                    <i class="fa fa-ban"></i> Cập nhật
                                </button>
                            </c:if>
                            <c:if test="${empty model.id}">
                                <button type="submit" class="btn btn-primary btn-sm" id="btnAddOrUpdateNew">
                                    <i class="fa fa-ban"></i> Thêm mới
                                </button>
                            </c:if>

                            <button type="reset" class="btn btn-danger btn-sm" onclick="clearErrorProduct()">
                                <i class="fa fa-undo"></i> Reset
                            </button>
                            <a class="btn btn-primary btn-sm"
                               href="<c:url value="/admin/product/list?page=1&limit=10" />"
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

    function validateFormProduct() {
        var name = document.getElementById("name");
        var category = document.getElementById("categoryCode");
        var avatar = document.getElementById("avatar");
        var avatarUpdate = document.getElementById("detailAvatar");
        var priceProduct = document.getElementById("price");
        var desProduct = document.getElementById("description");

        var errorName = document.getElementById("error-name-product");
        var errorCategory = document.getElementById("error-category-product");
        var errorAvatar = document.getElementById("error-avatar-product");
        var errorPrice = document.getElementById("error-price-product");
        var errorDes = document.getElementById("error-des-product");

        var check = true;
        if (name.value === "") {
            errorName.innerHTML = "Không được để trống tên sản phẩm";
            name.focus();
            check = false;
        } else {
            errorName.innerHTML = "";
        }

        if (category.value === "0") {
            errorCategory.innerHTML = "Bạn cần chọn danh mục cho sản phẩm";
            category.focus();
            check = false;
        } else {
            errorCategory.innerHTML = "";
        }

        if (avatar.files.length === 0 && avatarUpdate.src === "http://localhost:8080/ElectronicsShopBackend/template/images/product/") {
            errorAvatar.innerHTML = "Bạn cần có ảnh sản phẩm";
            avatar.focus();
            check = false;
        } else {
            errorAvatar.innerHTML = "";
        }

        if (priceProduct.value === "") {
            errorPrice.innerHTML = "Không được để trống giá sản phẩm";
            priceProduct.focus();
            check = false;
        } else if (isNaN(priceProduct.value)) {
            errorPrice.innerHTML = "Giá trị nhập vào phải là số";
            priceProduct.focus();
            check = false;
        } else {
            errorPrice.innerHTML = "";
        }

        if (desProduct.value === "") {
            errorDes.innerHTML = "Bạn cần viết mô tả cho sản phẩm";
            desProduct.focus();
            check = false;
        } else {
            errorDes.innerHTML = "";
        }

        return check;
    }

    function clearErrorProduct() {
        document.getElementById("error-name-product").innerHTML = "";
        document.getElementById("error-category-product").innerHTML = "";
        document.getElementById("error-avatar-product").innerHTML = "";
        document.getElementById("error-price-product").innerHTML = "";
        document.getElementById("error-des-product").innerHTML = "";
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