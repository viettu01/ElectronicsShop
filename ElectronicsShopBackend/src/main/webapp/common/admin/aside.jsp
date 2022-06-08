<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!-- Left Panel -->
<aside id="left-panel" class="left-panel">
    <nav class="navbar navbar-expand-sm navbar-default">

        <div class="navbar-header">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu"
                    aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="<c:url value="/admin/trang-chu" />">
                <img src="<c:url value='/template/admin/images/logo.png'/>" alt="Logo">
            </a>
            <a class="navbar-brand hidden" href="<c:url value="/admin/trang-chu" />">
                <img src="<c:url value='/template/admin/images/logo2.png'/>" alt="Logo">
            </a>
        </div>

        <div id="main-menu" class="main-menu collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="<c:url value="/admin/trang-chu" />"> <i class="menu-icon fa fa-dashboard"></i>Trang chủ </a>
                </li>
                <h3 class="menu-title">Quản lý</h3><!-- /.menu-title -->
                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Danh sách</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="fa fa-table"></i>
                            <a href="<c:url value="/admin/category/list?page=1&limit=10" />">
                                Danh mục
                            </a>
                        </li>
                        <li><i class="fa fa-table"></i>
                            <a href="<c:url value="/admin/product/list?page=1&limit=10" />">
                                Sản phẩm
                            </a>
                        </li>
                        <li><i class="fa fa-table"></i>
                            <a href="<c:url value="/admin/user/list?page=1&limit=10" />">
                                Người dùng
                            </a>
                        </li>
                        <li><i class="fa fa-table"></i>
                            <a href="<c:url value="/admin/order/list?page=1&limit=10" />">
                                Đặt hàng
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</aside>
<!-- /#left-panel -->

<!-- Left Panel -->