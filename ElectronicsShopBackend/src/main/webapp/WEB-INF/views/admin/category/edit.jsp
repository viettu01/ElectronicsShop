<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa danh mục</title>
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
                                Sửa danh mục
                            </c:if>
                            <c:if test="${empty model.id}">
                                Thêm danh mục
                            </c:if>
                        </strong>
                    </div>
                    <form:form id="formSubmit" action="edit" cssClass="form-horizontal" method="post"
                               enctype="multipart/form-data" modelAttribute="model"
                               onsubmit="return validateFormCategory()">
                        <div class="card-body card-block">
                            <c:if test="${not empty message}">
                                <div class="alert alert-${alert}">
                                        ${message}
                                </div>
                            </c:if>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="name" class=" form-control-label">
                                        Tên danh mục <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input cssClass="form-control" path="name" value="${model.name}"/>
                                    <div id="errorName" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="avatar" class=" form-control-label">
                                        Avatar <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input type="file" path="avatar" cssClass="form-control-file"
                                                onchange="readURL(this);"/>
                                    <img id="detailAvatar" class="image img-thumbnail mt-1 visible" width="20%"
                                         src="<c:url value="/template/images/category/${model.avatar}"/>"
                                         alt="Ảnh danh mục"/>
                                    <div id="errorAvatar" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>
                        </div>
                        <form:hidden path="id"/>
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
                            <button type="reset" class="btn btn-danger btn-sm" onclick="clearErrorCategory()">
                                <i class="fa fa-ban"></i> Reset
                            </button>
                            <a class="btn btn-primary btn-sm" role="button"
                               href="<c:url value="/admin/category/list?page=1&limit=10"/>">
                                <i class="fa fa-reply"></i> Quay lại
                            </a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div><!-- .content -->
<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#detailAvatar').attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }

    function validateFormCategory() {
        var nameCategory = document.getElementById("name");
        var avatarCategory = document.getElementById("avatar");
        var avatarUpdate = document.getElementById("detailAvatar");

        var errorNameCategory = document.getElementById("errorName");
        var errorAvatarCategory = document.getElementById("errorAvatar");

        var check = true;

        if (nameCategory.value === "") {
            errorNameCategory.innerHTML = "Không được để trống";
            nameCategory.focus();
            check = false;
        } else {
            errorNameCategory.innerHTML = "";
        }

        if (avatarCategory.files.length === 0 && avatarUpdate.src === "http://localhost:8080/ElectronicsShopBackend/template/images/category/") {
            errorAvatarCategory.innerHTML = "Bạn cần có ảnh danh mục";
            avatarCategory.focus();
            check = false;
        } else {
            errorAvatarCategory.innerHTML = "";
        }

        return check;
    }

    function clearErrorCategory() {
        document.getElementById("errorName").innerHTML = "";
        document.getElementById("errorAvatar").innerHTML = "";
    }
</script>
</body>
</html>
