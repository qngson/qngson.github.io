<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>NameVn</th>
			</tr>
		</thead>
	<tbody>
	<c:forEach var="e" items="${list}">
		<tr>
			<td>${e.id}</td>
			<td>${e.name}</td>
			<td>${e.nameVn}</td>
			<td>
				<a class="btn btn-sm btn-info"  href="${base}/edit/${e.id}">Edit</a>
				<a class="btn btn-sm btn-danger"  href="${base}/delete/${e.id}">Delete</a>
			</td>
		
		</tr>
	</c:forEach>
	</tbody>
	
	</table>
	