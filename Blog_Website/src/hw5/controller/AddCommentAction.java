package hw5.controller;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import hw5.databean.User;
import hw5.model.Model;
import hw5.model.PostDAO;
import hw5.databean.CommentBean;
import hw5.formbean.CommentForm;
import hw5.model.CommentDAO;
import org.formbeanfactory.FormBeanFactory;
public class AddCommentAction extends Action {
	private FormBeanFactory<CommentForm> formBeanFactory = new FormBeanFactory<>(CommentForm.class);
    private CommentDAO commentDAO;
    private PostDAO postDAO;

    public AddCommentAction(Model model) {
        commentDAO = model.getCommentDAO();
        postDAO = model.getPostDAO();
    }

    public String getName() {
        return "addcomment.do";
    }

    public String performPost(HttpServletRequest request) {
        try {
        	CommentForm form = formBeanFactory.create(request);
        	request.setAttribute("form", form);
            request.setAttribute("comments", commentDAO.getComments());
    		if(((User)request.getSession().getAttribute("user")) == null) {
    			return "Login.do";
    		}

            if (form.hasValidationErrors()) {
                return "Visitor.do";
            }
            if(postDAO.read(Integer.parseInt(form.getPostId())) == null || 
            		postDAO.read(Integer.parseInt(form.getPostId())).getUserEmail().equals(((User)request.getSession().getAttribute("user")).getEmail())) {
            		return "Visitor.do";
            }
            CommentBean bean = new CommentBean();
            bean.setContent(form.getComment());
            bean.setDate(String.valueOf(new Date()));
            bean.setUserEmail(((User)request.getSession().getAttribute("user")).getEmail());
            bean.setPostId(Integer.parseInt(form.getPostId()));
            User currentUser = ((User)request.getSession().getAttribute("currentUser"));
            
            bean.setToUserEmail(currentUser.getEmail());
            if (form.getAction().equals("comment")) {
                commentDAO.addToBottom(bean);
            }

            
            request.setAttribute("comments", commentDAO.getComments());

            return "Visitor.do";

        } catch (RollbackException e) {
        		request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
