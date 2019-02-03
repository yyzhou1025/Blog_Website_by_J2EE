<%@ page import="hw5.databean.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link rel="stylesheet" href="login.css" type="text/css">

<style type="text/css">

@charset "UTF-8";
body {
	font-family: Lato;
	background:url(https://images.unsplash.com/photo-1511184150666-9bb7d41a88f4?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=be5c750379d253e0b07d1200c1361991&auto=format&fit=crop&w=1050&q=80);
	background-size:cover;
	background-position: center;
}

h1 {
	
	font-size: 50px;
	text-align:center;
	font-weight:700;
}




.sidebar {
    height: 100%;
    width: 100px;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color:#F8F8F8;
    overflow-x: hidden;
    padding-top: 50px;
    
    
}

.sidebar a {
    padding: 6px 8px 6px 16px;
    text-decoration: none;
    font-size: 15px;
    color: #818181;
    display: block;
}

.sidebar a:hover {
    color: #111;
}

.content {
    padding: 20px;
	width: 600px;
	margin: 20px auto auto auto;
    border: 20px solid #bdc3c7;
}

.post {
	margin-bottom: 20px;
}

hr {
    border: 0;
    height: 1px;
    background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
}

.format {

            float: left;
			clear: left;
			width: 3em;
}

</style>


</head>
<body>

<%
	User[] users = (User[]) request.getAttribute("users");
%>

<nav class="navbar navbar-default">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="Login.do">Blog Master</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-expand-md">
        <li><a href="Login.do">Home</a></li>
      </ul>
       
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
        <c:when test="${ (empty user) }">
        <li><a href="Register.do">Register <i class="fas fa-user-plus"></i></a></li>
        <li><a href="Login.do">Login <i class="fas fa-user"></i></a></li>
        </c:when>
        <c:otherwise>
        <c:set var="user" value="${user}"/>
        <li><a href="Logout.do">Logout  <i class="fas fa-sign-out-alt"></i></a></li>
        
        </c:otherwise> 
        </c:choose>
         
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

               
                <div  class = "container">
               <div class="sidebar">
               
        <c:forEach var="usertmp" items="${users}">
	    <c:if test="${user.email eq usertmp.email}">
	    <ul><form action = "Home.do" method = "POST">
	    <input type="hidden" name="email" value= "${usertmp.email}">
		<a href="javascript:;" onclick="parentNode.submit();"  style="float: right;">${usertmp.firstname} ${usertmp.lastname}</a>
	    </form></ul>
	    </c:if>
	    <c:if test="${user.email ne usertmp.email}">
		<ul><form action="Visitor.do" method="POST">
		<input type="hidden" name="email" value= "${usertmp.email}">
		<a href="javascript:;" onclick="parentNode.submit();"  style="float: right;">${usertmp.firstname} ${usertmp.lastname}</a>
        </form></ul>
      </c:if>
	</c:forEach>
	
	
                


</div>
</div>



