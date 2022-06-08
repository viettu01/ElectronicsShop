<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><dec:title default="Đăng nhập"/></title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="<c:url value="/template/admin/favicon.ico"/>">

    <link rel="stylesheet" href="<c:url value="/template/admin/vendors/bootstrap/dist/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/admin/vendors/font-awesome/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/admin/vendors/themify-icons/css/themify-icons.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/admin/vendors/flag-icon-css/css/flag-icon.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/admin/vendors/selectFX/css/cs-skin-elastic.css"/>">

    <link rel="stylesheet" href=<c:url value="/template/admin/assets/css/style.css"/>>

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>
</head>
<body class="bg-dark">
<div class="sufee-login d-flex align-content-center flex-wrap">
    <div class="container">
        <div class="login-content">
            <div class="login-logo">
                <a href="#">
                    <img class="align-content" src="<c:url value="/template/admin/images/logo.png"/>" alt="">
                </a>
            </div>
            <dec:body/>
        </div>
    </div>
</div>

<script src="<c:url value="/template/admin/vendors/jquery/dist/jquery.min.js"/>"></script>
<script src="<c:url value="/template/admin/vendors/popper.js/dist/umd/popper.min.js"/>"></script>
<script src="<c:url value="/template/admin/vendors/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/template/admin/assets/js/main.js"/>"></script>
</body>
</html>
