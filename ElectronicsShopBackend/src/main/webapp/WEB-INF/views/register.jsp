<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
<div class="login-form">
    <form:form action="" method="post" modelAttribute="register" onsubmit="return validateForm()">
        <div class="form-group">
            <label>User Name</label>
            <input type="text" class="form-control" name="userName" id="userName" placeholder="User Name">
            <div id="errorUserName" class="text-danger font-weight-bold mt-1"></div>
        </div>
        <div class="form-group">
            <label>Email address</label>
            <input type="text" class="form-control" name="email" id="email" placeholder="Email">
            <div id="errorEmail" class="text-danger font-weight-bold mt-1"></div>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
            <div id="errorPassword" class="text-danger font-weight-bold mt-1"></div>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox"> Agree the terms and policy
            </label>
        </div>
        <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Đăng ký</button>
        <div class="social-login-content">
            <div class="social-button">
                <button type="button" class="btn social facebook btn-flat btn-addon mb-3"><i class="ti-facebook"></i>Register
                    with facebook
                </button>
                <button type="button" class="btn social twitter btn-flat btn-addon mt-2"><i class="ti-twitter"></i>Register
                    with twitter
                </button>
            </div>
        </div>
        <div class="register-link m-t-15 text-center">
            <p>Bạn đã có tài khoản ? <a href="<c:url value="/dang-nhap"/>"> Đăng nhập</a></p>
        </div>
    </form:form>
</div>
<script>
    function validateForm() {
        var userName = document.getElementById("userName");
        var email = document.getElementById("email");
        var password = document.getElementById("password");

        var errorUserName = document.getElementById("errorUserName");
        var errorPassword = document.getElementById("errorPassword");

        var check = true;

        if (userName.value === "") {
            errorUserName.innerHTML = "Không được để trống";
            userName.focus();
            check = false;
        } else {
            errorUserName.innerHTML = "";
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

        if (password.value === "") {
            errorPassword.innerHTML = "Không được để trống";
            password.focus();
            check = false;
        } else {
            errorPassword.innerHTML = "";
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
