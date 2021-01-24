<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<c:set var="cart" value="${sessionScope['scopedTarget.cartService']}"/>
<div class="panel panel-default">
	<div class="panel-heading"><s:message code="lyt.cart.title"/></div>
	<div class="panel-body">
		<img id="cart-img" src="" class="col-sm-5">
		<ul class="col-sm-7">
			<li><b id="cart-cnt"> ${cart.count}</b> mặt hàng</li>
			<li><b id="cart-amt"><f:formatNumber value="${cart.amount}" pattern="#,###.00"/></b>VNĐ</li>
			<li>
				<a href="/cart/view">Xem giỏ Hàng</a>
			</li>
			
		</ul>
</div>
</div>

<div class="panel panel-default">
	<div class="panel-heading">Tìm Kiếm</div>
	<div class="panel-body">
	<form action="/product/list-by-keywords" method="post">
			<input value="${param.keywords}" name="keywords" class="form-control" placeholder="Keywords">
	</form>
</div>
</div>


<div class="panel panel-default">
	<div class="panel-heading">
	<s:message code="lyt.cate.title"/>
	</div>
	<div class="list-group">
	<c:set value="${pageContext.response.locale.language}" var="lang"/>
	<%-- ${pageContext.response.locale.language} --%>
	<c:forEach var="c" items="${cates}">
	<a href="/product/list-by-category/${c.id}" class="list-group-item">${(lang=='vi')?c.nameVn:c.name}</a> 
	
	</c:forEach>
	
</div>
</div>

<div class="panel panel-default">
	<div class="panel-heading">Đặc Biệt</div>
	<div class="list-group">
	<a href="/product/list-by-speacial/0" class="list-group-item">Hàng Mới</a> 
	<a href="/product/list-by-speacial/1" class="list-group-item">Bán Chạy</a> 
	<a href="/product/list-by-speacial/2" class="list-group-item">Xem Nhiều</a>
	<a href="/product/list-by-speacial/3" class="list-group-item">Giảm Giá</a>
</div>
</div>

<style id=cart-css>
	/*  .cart-fly{
		
		background-image:url("/static/images/images.jpg");
		background-size: 100% 100%;
	} */
	/* background-color: yellow; */


</style>
