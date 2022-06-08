<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <title><dec:title default="Trang chủ"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="<c:url value='/template/admin/favicon.ico'/>">

    <link rel="stylesheet" href="<c:url value='/template/admin/vendors/bootstrap/dist/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/vendors/font-awesome/css/font-awesome.min.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/vendors/themify-icons/css/themify-icons.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/vendors/flag-icon-css/css/flag-icon.min.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/vendors/selectFX/css/cs-skin-elastic.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/vendors/jqvmap/dist/jqvmap.min.css' />">

    <link rel="stylesheet"
          href="<c:url value="/template/admin/vendors/datatables.net-bs4/css/dataTables.bootstrap4.min.css"/>">
    <link rel="stylesheet"
          href="<c:url value="/template/admin/vendors/datatables.net-buttons-bs4/css/buttons.bootstrap4.min.css"/>">

    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/style.css'/>">

    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' type='text/css'>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

    <%-- sweetalert--%>
    <script src="<c:url value='/template/admin/sweetalert/sweetalert2.min.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/template/admin/sweetalert/sweetalert2.min.css' />">

    <script src="<c:url value='/template/ckeditor/ckeditor.js' />"></script>
</head>
<body>

<%@ include file="/common/admin/aside.jsp" %>

<!-- Right Panel -->
<div id="right-panel" class="right-panel">
    <%@ include file="/common/admin/header.jsp" %>
    <dec:body/>
</div>
<!-- Right Panel -->

<script src="<c:url value='/template/admin/paging/jquery.twbsPagination.js'/>"></script>

<script src="<c:url value='/template/admin/vendors/jquery/dist/jquery.min.js'/>"></script>
<script src="<c:url value='/template/admin/vendors/popper.js/dist/umd/popper.min.js'/>"></script>
<script src="<c:url value='/template/admin/vendors/bootstrap/dist/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/main.js'/>"></script>

<script src="<c:url value='/template/admin/vendors/chart.js/dist/Chart.bundle.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/dashboard.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/widgets.js'/>"></script>
<script src="<c:url value='/template/admin/vendors/jqvmap/dist/jquery.vmap.min.js'/>"></script>
<script src="<c:url value='/template/admin/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js'/>"></script>
<script src="<c:url value='/template/admin/vendors/jqvmap/dist/maps/jquery.vmap.world.js'/>"></script>
<script>
    (function ($) {
        "use strict";

        jQuery('#vmap').vectorMap({
            map: 'world_en',
            backgroundColor: null,
            color: '#ffffff',
            hoverOpacity: 0.7,
            selectedColor: '#1de9b6',
            enableZoom: true,
            showTooltip: true,
            values: sample_data,
            scaleColors: ['#1de9b6', '#03a9f5'],
            normalizeFunction: 'polynomial'
        });
    })(jQuery);
</script>
</body>
</html>
