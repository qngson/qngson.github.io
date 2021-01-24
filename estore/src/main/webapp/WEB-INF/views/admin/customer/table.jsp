<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<table class="table table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>FullName</th>
			<th>Email</th>
			<th>Activated</th>
			<th>Admin</th>
		</tr>
	</thead>
	<tbody id="tbody">
		<%-- <c:forEach var="e" items="${list}">
			<tr>
				<td>${e.id}</td>
				<td>${e.fullname}</td>
				<td>${e.email}</td>
				<td>${e.activated?'Yes':'No'}</td>
				<td>${e.admin?'Admin':'User'}</td>
				<td class="text-center"><a class="btn btn-sm btn-info" href="${base}/edit/${e.id}">Edit</a>
					<a class="btn btn-sm btn-danger" href="${base}/delete/${e.id}">Delete</a>
				</td>

			</tr>
		</c:forEach> --%>
	</tbody>

</table>
<ul class="pager">
	<li ><a href="#"> <i class="glyphicon glyphicon-arrow-up"></i>  </a></li>
	<li ><a href="#"> <i class="glyphicon glyphicon-arrow-left"></i> </a> </li>
	
	<li ><a href="#"> <span id="info"></span> </a> </li>
	
	<li ><a href="#"> <i class="glyphicon glyphicon-arrow-right"></i> </a></li>
	<li ><a href="#"> <i class="glyphicon glyphicon-arrow-down"></i> </a></li>
</ul>


<script>
	$(function(){
		var pageNo =  0;
		var pageCount =  0;
		var pageSize =  2;
		
		$.ajax({
			url:'/pager/customer/page-count',
			success:function(response){
				pageCount = response;
				$(".pager a:eq(0)").click();
				//alert(pageCount);
			}
		});
		
		$(".pager a:eq(0)").click(function(){
			pageNo =  0;
			$.ajax({
				url:'/pager/customer/page/'+pageNo,
				success:fnSuccess
			});
			return false;
		});
		
		$(".pager a:eq(1)").click(function(){
			if(pageNo >0){
				pageNo--;
				$.ajax({
					url:'/pager/customer/page/'+pageNo,
					success:fnSuccess
				});
			}
			
			return false;
		});
		$(".pager a:eq(3)").click(function(){
			if(pageNo <  pageCount-1){
				pageNo++;
				$.ajax({
					url:'/pager/customer/page/'+pageNo,
					success:fnSuccess
				});
			}
			
		
			return false;
		});
		$(".pager a:eq(4)").click(function(){
			
			pageNo = pageCount -1;
			$.ajax({
				url:'/pager/customer/page/'+pageNo,
				success:fnSuccess
			});
			return false;
		});
		
		function fnSuccess(response){
			$("#tbody").html("");
			$(response).each(function(index,user){
				var tr = $("<tr/>");
				$("<td/>").html(user.id).appendTo(tr);
				$("<td/>").html(user.fullname).appendTo(tr);
				$("<td/>").html(user.email).appendTo(tr);
				$("<td/>").html(user.activated?'Yes':'No').appendTo(tr);
				$("<td/>").html(user.admin?'Admin':'User').appendTo(tr);
				var s = `<td class="text-center">
					<a class="btn btn-sm btn-info" href="/admin/customer/edit/`+user.id+`">Edit</a>
					<a class="btn btn-sm btn-danger" href="admin/customer/delete/`+user.id+`">Delete</a>
					</td>`;
					$(s).appendTo(tr);
				tr.appendTo("#tbody")
				$("#info").html((pageNo+1)+'/'+pageCount);
				/* console.log(tr.html()); */
			})
		
		}
		
	});

</script>