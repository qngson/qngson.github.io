<%@ page pageEncoding="UTF-8"%>
<h2>Login</h2>
<h4>${message}</h4>
<form action="/account/login" method="post">
<div class="form-group">
	<label>Username</label>
	<input name="id" class="form-control">
</div>

<div class="form-group">
	<label>Password</label>
	<input name="pw" class="form-control">
</div>

<div class="form-group">
<div class="form-control">
<input name="rm" type="checkbox" >
	<label>Remember me</label>
	</div>
</div>

<div class="form-group">
	<button class="btn btn-default">Login</button>
</div>
</form>