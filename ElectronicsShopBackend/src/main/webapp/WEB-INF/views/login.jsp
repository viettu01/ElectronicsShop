<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
<div class="login-form">
    <c:if test="${param.incorrectAccount != null}">
        <div class="alert alert-danger">
            Sai Tên đăng nhập hoặc mật khẩu
        </div>
    </c:if>
    <c:if test="${param.accessDenied != null}">
        <div class="alert alert-danger">
                <%--                        You not authorize--%>
            Bạn không có quyền truy cập
        </div>
    </c:if>
    <form method="POST" action="j_spring_security_check" class="form-horizontal" onsubmit="return validateForm()">
        <div class="form-group">
            <label>Tên đăng nhập</label>
            <input type="text" id="username" class="form-control" placeholder="Tên đăng nhập" name="j_username"/>
            <div id="errorUserName" class="text-danger font-weight-bold mt-1"></div>
        </div>
        <div class="form-group">
            <label>Mật khẩu</label>
            <input type="password" id="password" class="form-control" placeholder="Mật khẩu" name="j_password"/>
            <div id="errorPassword" class="text-danger font-weight-bold mt-1"></div>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox"> Nhớ thông tin
            </label>
            <label class="pull-right">
                <a href="#">Quên mật khẩu?</a>
            </label>

        </div>
        <input type="submit" class="btn btn-success btn-flat m-b-30 m-t-30" value="Đăng nhập"/>
        <div class="social-login-content">
            <div class="social-button">
                <button type="button" class="btn social facebook btn-flat btn-addon mb-3"><i
                        class="ti-facebook"></i>Đăng nhập với facebook
                </button>
                <button type="button" class="btn social twitter btn-flat btn-addon mt-2"><i
                        class="ti-twitter"></i>Đăng nhập với twitter
                </button>
            </div>
        </div>
        <div class="register-link m-t-15 text-center">
            <p>Bạn không có tài khoản ? <a href="<c:url value="/dang-ky"/>"> Đăng ký tại đây</a></p>
        </div>
    </form>
</div>
<script>
    function validateForm() {
        var userName = document.getElementById("userName");
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

        if (password.value === "") {
            errorPassword.innerHTML = "Không được để trống";
            password.focus();
            check = false;
        } else {
            errorPassword.innerHTML = "";
        }

        return check;
    }
</script>
</body>
</html>
