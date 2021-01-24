<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h2>EDIT PROFILE</h2>
<h4>${message}</h4>
<form:form action="/account/edt" method="post" modelAttribute="form" enctype="multipart/form-data">
<div class="form-group">
	<label>Username</label>
	<form:input path="id" class="form-control" readonly="true"/>
</div>


<div class="form-group">
	<label>FullName</label>
	<form:input path="fullname" class="form-control" />
</div>

<div class="form-group">
	<label>Email</label>
	<form:input path="email" class="form-control" />
</div>

<div class="form-group">
	<label>Photo</label>
	<img src="/static/images/customer/${form.photo}" style="width: 80px;height: 90px">
	<input type="file" name="photo_file">
	<form:hidden path="photo" class="form-control" />
</div>



<div class="form-group">
	<form:hidden path="password" />
	<form:hidden path="activated" />
	<form:hidden path="admin" />
	<button class="btn btn-default">Update</button>
</div>
</form:form>