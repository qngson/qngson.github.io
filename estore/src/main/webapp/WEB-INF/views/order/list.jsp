<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<h2>Order list</h2>
<table class="table">
<thead>
		<tr>
				<th>Id</th>
				<th>Order Date</th>
				<th>Address</th>
				<th>Amout</th>
			
		</tr>
</thead>
<tbody>
<c:forEach var="o" items="${orders}">
	<tr data-id="${p.id}" data-price="${p.unitPrice}" data-discount="${p.discount}">
		<td>${o.id}</td>
		<td>${o.orderDate}</td>
		<td>${o.address}</td>
		<td>${o.amount}</td>
		<td>
			<a href="/order/detail/${o.id}" class="btn btn-sm btn-warning"> Detail</a>
			</td>
			</tr>
			</c:forEach>
</tbody>
</table>
