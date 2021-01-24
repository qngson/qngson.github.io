<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Online Shopping Mall </title>
 	<tiles:insertAttribute name="head"/> 
    
</head>
<body>

<div class="container">
  <header class="row">
  	<h1 class="alert alert-sucess">Online Shopping Mall </h1>
  </header>
  
  <nav class="row">
  		 <tiles:insertAttribute name="menu"/> 
		<%-- <jsp:include page="layout/menu.jsp"/> --%>
  
  </nav>
  
  
  <div class="row">
  
  		<article class="col-sm-9">
  			<tiles:insertAttribute name="body"/>
  		</article>
  		
  		<aside class="col-sm-3">
  		 <tiles:insertAttribute name="aside"/>
  			<%-- <jsp:include page="layout/aside.jsp"/> --%>
  		</aside>
  		
  		
  </div>
  <footer class="row">
  	<p class="text-center">&copy;2020. All rights reserved.</p>
  </footer>
</div>

</body>
</html>