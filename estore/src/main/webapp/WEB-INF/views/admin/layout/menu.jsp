
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/home/index">Trang Chủ</a>
		</div>
		<ul class="nav navbar-nav">
			
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Quản lý <span class="caret"></span></a>
				
						<ul class="dropdown-menu">
							<li><a href="/admin/category/index">Loại</a></li>
							<li><a href="/admin/product/index"> Sản Phẩm</a></li>
							<li><a href="/admin/customer/index">Khách Hàng</a></li>
							<li><a href="/admin/order/index">Đơn Hàng</a></li>
						</ul>
					</li>
					
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Thống kê <span class="caret"></span></a>
				
						<ul class="dropdown-menu">
							<li><a href="/admin/inventory/index">Tồn Kho Loại</a></li>
							<li><a href="/admin/revenue/category"> Doanh Số Theo Loại</a></li>
							<li><a href="/admin/revenue/customer/">Doanh Sô Theo Khách Hàng</a></li>
							<li><a href="/admin/revenue/year">Doanh Sô Theo Năm</a></li>
							<li><a href="/admin/revenue/quater">Doanh Sô Theo Quý</a></li >
							<li><a href="/admin/revenue/month">Doanh Sô Theo Tháng</a></li>
							
						</ul>
					</li>
		
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Tài Khoản <span class="caret"></span></a>
				
						<ul class="dropdown-menu">
							<li><a href="/admin/category/index">Đăng Xuất</a></li>
							<li><a href="/admin/product/index"> Đổi Mật Khẩu</a></li>
							
						</ul>
					</li>
			
		</ul>
		
	</div>
</nav>