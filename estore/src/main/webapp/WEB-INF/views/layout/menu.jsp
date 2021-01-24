
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/home/index">
			
			<s:message code="lyt.menu.home"/>
			</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="/home/about">About US</a></li>
			<li><a href="/home/contact">Contact US</a></li>
			<li><a href="/home/feedback">Feedback</a></li>
			<li><a href="/home/faq">FAQs</a></li>

			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Account <span class="caret"></span></a>
				<c:choose>
					<c:when test="${empty sessionScope.user}">
						<ul class="dropdown-menu">
							<li><a href="/account/login">Đăng Nhập</a></li>
							<li><a href="/account/register">Đăng Ký</a></li>
							<li><a href="/account/forgot">Quên Mật Khẩu</a></li>

						</ul>
					</c:when>
					<c:otherwise>
						<ul class="dropdown-menu">
							<li><a href="/account/logoff">Đăng Xuất</a></li>
							<li><a href="/account/change">Đổi Mật Khẩu</a></li>
							<li><a href="/account/edit">Cập Nhật Tài Khoản</a></li>
							<li><a href="/order/list">Đơn Hàng</a></li>
							<li><a href="/order/items">Hàng Đã mua</a></li>
						</ul>

					</c:otherwise>
				</c:choose></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#" data-lang="vi"><span class="glyphicon glyphicon-user"></span>
					Tiếng Việt</a></li>
			<li><a href="#" data-lang="en"><span class="glyphicon glyphicon-log-in"></span>
					English</a></li>
		</ul>
	</div>
</nav>

<script>
	$(function(){
		$('a[data-lang]').click(function(){
			
			var lang = $(this).attr("data-lang");
			$.ajax({
				url:'/home/language?lang='+lang,
				success: function(){
					location.reload();
				}
			});
			return false;
		});
	});

</script>