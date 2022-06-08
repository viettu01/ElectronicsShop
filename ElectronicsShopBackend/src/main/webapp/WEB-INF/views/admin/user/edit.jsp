<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="productURL" value="/admin/product/list"/>
<c:url var="editProductURL" value="/admin/product/edit"/>
<c:url var="productAPI" value="/api/product"/>
<html>
<head>
    <title>
        <c:if test="${not empty model.id}">
            Sửa thông tin người dùng
        </c:if>
        <c:if test="${empty model.id}">
            Thêm người dùng
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
                                Sửa thông tin người dùng
                            </c:if>
                            <c:if test="${empty model.id}">
                                Thêm người dùng
                            </c:if>
                        </strong>
                    </div>
                    <form:form id="formSubmit" action="edit" cssClass="form-horizontal" method="post"
                               enctype="multipart/form-data" modelAttribute="model"
                               onsubmit="return validateFormUser()">
                        <div class="card-body card-block">
                            <c:if test="${not empty message}">
                                <div class="alert alert-${alert}">
                                        ${message}
                                </div>
                            </c:if>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="userName" class="form-control-label">
                                        Tên đăng nhập <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input cssClass="form-control" path="userName" id="userName"/>
                                    <div id="errorUserName" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="email" class="form-control-label">
                                        Email <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input cssClass="form-control" path="email" id="email"/>
                                    <div id="errorEmail" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="password" class="form-control-label">
                                        Mật khẩu <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input type="password" cssClass="form-control" path="password" id="password"/>
                                    <div id="errorPassword" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label class="form-control-label">
                                        Quyền <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <c:forEach var="role" items="${roles}">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" id="roleId${role.id}" name="roleId" type="checkbox"
                                                   value="${role.id}"
                                                    <c:forEach var="subRole" items="${model.listRole}">
                                                        <c:if test="${role.id == subRole.id}">
                                                            checked
                                                        </c:if>
                                                    </c:forEach>/>
                                                <%--                                                    <c:forEach var="subRole" items="${model.roleIds}">--%>
                                                <%--                                                        <c:if test="${role.id == subRole}">--%>
                                                <%--                                                            checked--%>
                                                <%--                                                        </c:if>--%>
                                                <%--                                                    </c:forEach>/>--%>
                                            <label for="roleId" class="form-check-label">${role.name}</label>
                                        </div>
                                    </c:forEach>
                                    <div id="errorRole" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="fullName" class="form-control-label">
                                        Họ tên <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input cssClass="form-control" path="fullName"/>
                                    <div id="errorFullName" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="userName" class="form-control-label">
                                        Giới tính
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <div class="form-check form-check-inline">
                                        <form:radiobutton path="gender" cssClass="form-check-input" value="Nam"/>
                                        <label class="form-check-label">Nam</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <form:radiobutton path="gender" cssClass="form-check-input" value="Nữ"/>
                                        <label class="form-check-label">Nữ</label>
                                    </div>
                                    <div id="errorGender" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="birthday" class="form-control-label">
                                        Ngày sinh
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <input type="date" class="form-control" name="birthday" id="birthday"
                                           value="<fmt:formatDate value="${model.birthday}" pattern="yyyy-MM-dd"/>"/>
                                    <div id="errorBirthday" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="phone" class="form-control-label">
                                        Số điện thoại <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input path="phone" class="form-control"/>
                                    <div id="errorPhone" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="address" class="form-control-label">
                                        Địa chỉ
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input path="address" class="form-control"/>
                                    <div id="errorAddress" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="avatar" class=" form-control-label">
                                        Ảnh
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input path="avatar" type="file" cssClass="form-control-file"
                                                onchange="readURL(this);"/>
                                    <img id="detailAvatar" class="image img-thumbnail mt-1" width="20%"
                                         src="<c:url value="/template/images/user/${model.avatar}"/>"
                                         alt="Ảnh đại diện"/>
                                    <div id="errorAvatar" class="text-danger font-weight-bold mt-1"></div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="jobs" class="form-control-label">
                                        Công việc
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input path="jobs" class="form-control"/>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="facebook" class="form-control-label">
                                        Facebook
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <form:input path="facebook" class="form-control"/>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="userName" class="form-control-label">
                                        Trạng thái <sup class="text-danger font-weight-bold" style="font-size: 16px">*</sup>
                                    </label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <div class="form-check form-check-inline">
                                        <form:radiobutton path="status" cssClass="form-check-input" value="1"/>
                                        <label class="form-check-label">Bình thường</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <form:radiobutton path="status" cssClass="form-check-input" value="0"/>
                                        <label class="form-check-label">Khóa</label>
                                    </div>
                                    <div id="errorStatus" class="text-danger font-weight-bold mt-1"></div>
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

                            <button type="reset" class="btn btn-danger btn-sm">
                                <i class="fa fa-undo"></i> Reset
                            </button>
                            <a class="btn btn-primary btn-sm"
                               href="<c:url value="/admin/user/list?page=1&limit=10" />"
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

    function validateFormUser() {
        var userName = document.getElementById("userName");
        var email = document.getElementById("email");
        var password = document.getElementById("password");
        var roleAdmin = document.getElementById("roleId1");
        var roleUser = document.getElementById("roleId2");
        var fullName = document.getElementById("fullName");
        var phone = document.getElementById("phone");
        var status = document.getElementsByName("status");

        var errorUserName = document.getElementById("errorUserName");
        var errorEmail = document.getElementById("errorEmail");
        var errorPassword = document.getElementById("errorPassword");
        var errorRole = document.getElementById("errorRole");
        var errorFullName = document.getElementById("errorFullName");
        var errorPhone = document.getElementById("errorPhone");
        var errorStatus = document.getElementById("errorStatus");

        var check = true;
        if (userName.value === "") {
            errorUserName.innerHTML = "Không được để trống tên đăng nhập";
            userName.focus();
            check = false;
        } else {
            errorUserName.innerHTML = "";
        }

        if (password.value === "") {
            errorPassword.innerHTML = "Không được để trống mật khẩu";
            password.focus();
            check = false;
        } else {
            errorPassword.innerHTML = "";
        }

        if (email.value === "") {
            errorEmail.innerHTML = "Không được để trống email";
            email.focus();
            check = false;
        } else if (!ValidateEmail(email)) {
            errorEmail.innerHTML = "Email không đúng định dạng";
            email.focus();
            check = false;
        } else {
            errorEmail.innerHTML = "";
        }

        if (!roleAdmin.checked && !roleUser.checked) {
            errorRole.innerHTML = "Bạn cần chọn quyền cho tài khoản";
            check = false;
        } else {
            errorRole.innerHTML = "";
        }

        if (fullName.value === "") {
            errorFullName.innerHTML = "Không được để trống họ tên";
            fullName.focus();
            check = false;
        } else {
            errorFullName.innerHTML = "";
        }

        if (phone.value === "") {
            errorPhone.innerHTML = "Không được để trống số điện thoại";
            phone.focus();
            check = false;
        } else if (isNaN(phone.value)) {
            errorPhone.innerHTML = "Giá trị nhập vào phải là số";
            phone.focus();
            check = false;
        } else if (phone.value.length < 10) {
            errorPhone.innerHTML = "Số điện thoại cần nhập đủ 10 số";
            phone.focus();
            check = false;
        } else {
            errorPhone.innerHTML = "";
        }

        if (!status[0].checked && !status[1].checked) {
            errorStatus.innerHTML = "Vui lòng chọn trạng thái tài khoản";
            check = false;
        } else {
            errorStatus.innerHTML = "";
        }

        return check;
    }

    function ValidateEmail(input) {
        var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        if (input.value.match(validRegex)) {
            return true;
        } else {
            return false;
        }
    }
</script>
</body>
</html>