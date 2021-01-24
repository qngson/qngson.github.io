<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<div class="col-sm-5 text-center">
	<img class="detail-img" src="${prod.image}">
</div>
<div class="col-sm-7">
	<ul class="detail-info">
		<li>Name:${prod.name}</li>
		<li>UnitPrice:<f:formatNumber value="${prod.unitPrice}"
				pattern="#,###.00" />
		</li>
		<li>ProductDate:<f:formatDate value="${prod.productDate}"
				pattern="dd-MM-yyyy" />
		</li>
		<li>NameVn: ${prod.category.nameVn}</li>
		<li>Quantity: ${prod.quantity}</li>
		<li>Discount:<f:formatNumber value="${prod.discount}"
				type="percent" />
		</li>
		<li>ViewCount: ${prod.viewCount}</li>
		<li>Available: ${prod.available?'Yes':'No'}</li>
		<li>Special: ${prod.special?'Yes':'No'}</li>
	</ul>
</div>
<div class="text-justify">
	<p>Description:${prod.description}</p>
</div>
<ul class="nav nav-tabs">
	<li class="active"><a data-toggle="tab" href="#tab1">Cùng Loại</a></li>
	<li><a data-toggle="tab" href="#tab2">Yêu Thích</a></li>
	<li><a data-toggle="tab" href="#tab3">Đã Xem</a></li>
</ul>

<div class="tab-content">
	<div id="tab1" class="tab-pane fade in active">
		<div>
			<c:forEach var="p" items="${listTypeCate}">

				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="${p.image}">
				</a>


			</c:forEach>
		</div>
	</div>
	<div id="tab2" class="tab-pane fade">
		<div>
			<c:forEach var="p" items="${favo}">

				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="${p.image}">
				</a>


			</c:forEach>
		</div>
	</div>
	<div id="tab3" class="tab-pane fade">
		<div>
			<c:forEach var="p" items="${viewed}">

				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="${p.image}">
				</a>


			</c:forEach>
		</div>
	</div>
</div>

<!-- <h3>Hàng Cùng Loại</h3>


<h3>Hàng Yêu Thích</h3>


<h3>Hàng Đã Xem</h3>
 -->