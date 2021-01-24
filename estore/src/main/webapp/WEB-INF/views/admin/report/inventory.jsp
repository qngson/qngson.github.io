<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
	<h2 class="alert alert-success">INVENTORY</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Category</th>
				<th>Quantity</th>
				<th>Value</th>
				<th>Min</th>
				<th>Max</th>
				<th>AVG</th>
			</tr>
		</thead>
	<tbody>
	<c:forEach var="e" items="${data}">
		<tr>
			<td>${e[0]}</td>
			<td>${e[1]}</td>
			<td> <f:formatNumber value="${e[2]}" pattern="#,###" /> </td>
			<td> <f:formatNumber value="${e[3]}" pattern="#,###"/> </td>
			<td> <f:formatNumber value="${e[4]}" pattern="#,###"/> </td>
			<td> <f:formatNumber value="${e[5]}" pattern="#,###"/> </td>
			
		
		</tr>
	</c:forEach>
	</tbody>
	
	</table>
 <jsp:include page="inventory_chart.jsp"/> 