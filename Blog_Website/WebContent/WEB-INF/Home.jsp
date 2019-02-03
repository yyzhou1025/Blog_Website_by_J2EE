<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>
	

	<jsp:include page="template.jsp"/>

 <div class="container">
		<h1>${user.firstname} ${user.lastname }'s Home Page</h1> 
</div>
    <div class = "content">
    <div class = "post">
    <ol>
        <c:forEach var="post" items="${posts}">
            <li>
                <form class="delete-form" method="POST" action="deletepost.do">
                    <input type="hidden" name="id" value="${post.id}" />
                    <button class="btn btn-danger" type="submit"><i class="fas fa-trash-alt"></i> Delete</button>
                </form>
	           <p style= "background-color: #CCCCCC;"><c:out value="${post.content}" /> </p> 
                <span class="details"> posted by ${user.firstname} ${user.lastname} at ${post.date}
                </span>
            </li>
            <ol>
        <c:forEach var="comment" items="${comments}">
        <c:if test = "${comment.getPostId() eq post.id }">
            <li>
                <form class="delete-form" method="POST" action="deletecomment.do">
                    <input type="hidden" name="id" value="${comment.id}" />
                    <button type="submit" class="btn btn-danger"><i class="fas fa-trash-alt"></i> Delete</button>
                </form>
	           <p style= "background-color: #CCCCCC;"><c:out value="${comment.content}" /> </p>
                <span class="details"> commented by ${userDAO.read(comment.getUserEmail()).firstname} ${userDAO.read(comment.getUserEmail()).lastname} at ${comment.date}
                </span>
            </li>
            </c:if>
		</c:forEach>
		</ol>
		</c:forEach>
	</ol>


  <form class="add-form" method="POST" action="addpost.do">
         <table>
           <c:forEach var="field" items="${form.visibleFields}">
                    <tr>
                        <td style="font-size: x-large">
                         <label>${field.label}</label> 
                    </td>
                        <td>
                        
                            <textarea type="${field.type}" name="${field.name}" value="${field.value}" style="width: 450px; height: 80px; "> </textarea>
                           
                        </td>
                    <td style="color:red">
                        ${field.error}
                    </td>
                    </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td >
                        <button type="submit" name="action" value="post" class="btn btn-primary" style="float: right;"><i class="fas fa-share-square"></i> Post</button>
                </td>
            </tr>
        </table>
    </form> 
    
    

    <c:forEach var="error" items="${form.formErrors}">
		<p class="error"> ${error} </p>
    </c:forEach>	
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>