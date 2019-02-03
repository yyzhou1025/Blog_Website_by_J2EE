package hw5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import hw5.databean.CommentBean;
import hw5.databean.PostBean;
import hw5.databean.User;
import hw5.formbean.LoginForm;
import hw5.formbean.PostForm;
import hw5.model.CommentDAO;
import hw5.model.PostDAO;
import hw5.model.UserDAO;
import hw5.model.Model;

public class HomeAction extends Action {
    private CommentDAO commentDAO;
    private PostDAO postDAO;
    private UserDAO userDAO;
    public HomeAction(Model model) {
        commentDAO = model.getCommentDAO();
        postDAO = model.getPostDAO();
        userDAO = model.getUserDAO();
        
    }

    public String getName() {
        return "Home.do";
    }
    
    public String performGet(HttpServletRequest request) {
        request.setAttribute("form", new PostForm());
        return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
    	session.setAttribute("home", (boolean)true);
        User[] users = null;
 		try {
 			users = (User[])userDAO.getUsers();
 		} catch (RollbackException e) {
 			e.printStackTrace();
 		} 
 		session.setAttribute("users", users);
 		session.setAttribute("userDAO", userDAO);
 		User user = (User)session.getAttribute("user");
 		if(user == null) {
 			return "Login.jsp";
 		}
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
        	
        	//request.setAttribute("posts", postDAO.getPosts());
            /*request.setAttribute("comments", commentDAO.getComments()); */
            /*request.setAttribute("users", userDAO.getUsers()); */
            
        	
        	PostBean[] totalposts = postDAO.getPosts();
        	List<PostBean> listOfpost = new ArrayList<>();
        	for(PostBean post: totalposts) {
        		if(post.getUserEmail().equals(user.getEmail())) {
        			listOfpost.add(post);
        		}
        	}
        	session.setAttribute("posts", listOfpost);
        	
        	
            CommentBean[] totalcomments = commentDAO.getComments();
        	List<CommentBean> listOfcomments = new ArrayList<>();
        	for(CommentBean comment: totalcomments) {
        		if(comment.getToUserEmail().equals(user.getEmail())) {
        			listOfcomments.add(comment);
        		}
        	} 
        	session.setAttribute("comments", listOfcomments); 
            return "Home.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}

