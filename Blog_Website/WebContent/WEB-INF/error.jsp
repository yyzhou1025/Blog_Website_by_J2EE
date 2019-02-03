
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Error</title>
    </head>
    
	<body>
	
        <c:if test="${!(empty errors)}">
                <c:forEach var="error" items="${ errors }">
                    <h3 style="color:red"> ${ error } </h3>
                </c:forEach>
        </c:if>
        
        	<c:choose>
			<c:when test="${ (empty user) }">
				Click <a href="Login.do">here</a> to login.
			</c:when>
			<c:otherwise>
				Click <a href="Home.do">here</a> to return to the home page.
			</c:otherwise>
		</c:choose>
		
	</body>
</html>
