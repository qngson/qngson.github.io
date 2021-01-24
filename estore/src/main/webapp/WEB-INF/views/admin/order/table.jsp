<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Customer</th>
				<th>OrderDate</th>
				<th>Shipping Address</th>
				<th>Total Amount</th>
			</tr>
		</thead>
	<tbody>
	<c:forEach var="e" items="${list}">
		<tr>
			<td>${e.id}</td>
			<td>${e.customer.id}</td>
			<td>${e.orderDate}</td>
			<td>${e.address}</td>
			<td>${e.amount}</td>
			<td>
				<a class="btn btn-sm btn-info"  href="${base}/edit/${e.id}">Edit</a>
				<a class="btn btn-sm btn-danger"  href="${base}/delete/${e.id}">Delete</a>
			</td>
		
		</tr>
	</c:forEach>
	</tbody>
	
	</table>
	