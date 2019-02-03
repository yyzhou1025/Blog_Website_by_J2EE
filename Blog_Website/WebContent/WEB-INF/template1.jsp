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

h1 {
	font-size: 50px;
	text-align:center;
	padding-top: 1em;
	padding-bottom: 1em;
	font-weight:700;
	color:black;
}

hr {
	width:400px;
	border-top: 1px  solid #f8f8f8;
	border-bottom: 1px solid rgba(0,0,0,0.2); 
}

.form-group{
	margin-bottom: 15px;
}

label{
	margin-bottom: 15px;
}

input,
input::-webkit-input-placeholder {
    font-size: 11px;
    padding-top: 1px;
}

.main-login{
 	background-color: #fff;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

}

.main-center{
 	margin-top: 30px;
 	margin: 0 auto;
 	max-width: 330px;
    padding: 40px 40px;

}

.login-button{
	margin-top: 5px;
}

.login-register{
	font-size: 11px;
	text-align: center;
}
.error {
font-size: 11px;
text-align: center;
}
                   

</style>


</head>
<body>

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

  