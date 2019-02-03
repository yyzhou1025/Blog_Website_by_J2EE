
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${currentUser.firstname}'s blog</title>
</head>
<body>
	
	<jsp:include page="template.jsp"/>
<div >
		<h1>${currentUser.firstname} ${currentUser.lastname} 's Home Page</h1>
	
 
 <!--  <div class="sub-title"> ${blogmaster.firstname} has ${fn:length(posts)} posts: </div> -->
 <div class = "content">
<div class = "post">

		<ol>
        <c:forEach var="post" items="${posts}">
            <li>
	           <!-- <c:out value="${post.content}" /> -->
	           <p style= "background-color: #CCCCCC;"><c:out value="${post.content}" /> </p> 
                <span class="details"> posted by ${currentUser.firstname} ${currentUser.lastname} at ${post.date}
                </span>
            </li>
        <td>
        <ol>
        <c:forEach var="comment" items="${comments}">
           
            	<c:if test ="${comment.getPostId() eq post.id }">
            	 <li>
           	 <c:if test = "${user ne null}">
           	 <c:if test = "${comment.getUserEmail() eq user.email}">
                <form class="delete-form" method="POST" action="deletecomment.do">
                    <input type="hidden" name="id" value="${comment.id}" />
                    <button type="submit" class="btn btn-danger"><i class="fas fa-trash-alt"></i> Delete</button>
                </form>
                </c:if>
              </c:if>
	           <!-- <c:out value="${comment.content}" /> -->
	           <p style= "background-color: #CCCCCC;"><c:out value="${comment.content}" /> </p> 
                <span class="details"> commented by ${userDAO.read(comment.getUserEmail()).firstname} ${userDAO.read(comment.getUserEmail()).lastname} at ${comment.date}
                </span>
                </li> 
             </c:if>
            
		</c:forEach>
		</ol>
		
		
 <form class="add-form" method="POST" action="addcomment.do">
         <table>
            <c:forEach var="field" items="${form.getAllFields()}">
            		<c:if test = "${field.name eq 'postId' }">
            		 <td>
                        <input type="${field.type}" name="${field.name}" value="${post.id}" />
                  </td>
            		</c:if>
                   <c:if test = "${field.name eq 'comment' }">
                    <tr>
                        <td style="font-size: large; height: 25px">
                        <!--   <label>${field.label}</label> -->
                    </td>
                        <td>
                        <div style="text-align: right; padding-right: 20px" >
                            <textarea type="${field.type}" name="${field.name}" value="${field.value}"  style="width: 450px; height: 80px; "> </textarea>
                            </div>
                        </td>
                     <td style="color:red">
                        ${field.error}
                    </td>
                    </tr>
                    </c:if>
            </c:forEach>
            <tr>
                <td></td>
                <td >
                        <!--  <button type ="submit" name ="action" value ="comment">comment</button>-->
                        <button type="submit" name="action" value="comment" class="btn btn-primary" style="float: right;"><i class="fas fa-share-square"></i> Comment</button>
                </td>
            </tr>
        </table>
         </form>              
		</c:forEach>
		</ol>
    <c:forEach var="error" items="${form.formErrors}">
		<p class="error"> ${error} </p>
    </c:forEach>	
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>

 