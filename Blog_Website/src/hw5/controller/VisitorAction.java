package hw5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import hw5.databean.CommentBean;
import hw5.databean.PostBean;
import hw5.databean.User;
import hw5.formbean.CommentForm;
import hw5.formbean.PostForm;
import hw5.model.CommentDAO;
import hw5.model.PostDAO;
import hw5.model.UserDAO;
import hw5.model.Model;

public class VisitorAction extends Action {
    private CommentDAO commentDAO;
    private PostDAO postDAO;
    private UserDAO userDAO;
    public VisitorAction(Model model) {
        commentDAO = model.getCommentDAO();
        postDAO = model.getPostDAO();
        userDAO = model.getUserDAO();
        
    }

    public String getName() {
        return "Visitor.do";
    }
    
    public String performGet(HttpServletRequest request) {
    		return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
    		request.setAttribute("form", new CommentForm());
    		String email = (String)request.getParameter("email");
    	 	HttpSession session = request.getSession();
    	 	session.setAttribute("home", (boolean)false);
        User[] users = null;
 		try {
 			users = (User[])userDAO.getUsers();
 		} catch (RollbackException e) {
 			e.printStackTrace();
 		}
 		User currentUser = null;
		try {
			if(email != null) {
				currentUser = userDAO.read(email);
			}
			else {
				currentUser = ((User)request.getSession().getAttribute("currentUser"));
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
 		session.setAttribute("currentUser", currentUser);
 		session.setAttribute("users", users);
 		session.setAttribute("userDAO", userDAO);
 		User user = (User)session.getAttribute("user");
        
        try {
            if(currentUser != null) {
        	    PostBean[] totalposts = postDAO.getPosts();
        		List<PostBean> listOfpost = new ArrayList<>();
        		for(PostBean post: totalposts) {
        			if(post.getUserEmail().equals(currentUser.getEmail())) {
        				listOfpost.add(post);
        			}
        		}
        	
        		session.setAttribute("posts", listOfpost);
        			
        		CommentBean[] totalcomments = commentDAO.getComments();
        		List<CommentBean> listOfcomments = new ArrayList<>();
        		for(CommentBean comment: totalcomments) {
        			if(comment.getToUserEmail().equals(currentUser.getEmail())) {
        				listOfcomments.add(comment);
        			}
        		} 	
        		session.setAttribute("comments", listOfcomments);  
        	}
        		return "Visitor.jsp";
        } catch (RollbackException e) {
        	request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}

