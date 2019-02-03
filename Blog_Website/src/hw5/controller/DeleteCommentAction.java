package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import hw5.databean.User;
import hw5.formbean.IdForm;
import hw5.model.Model;
import hw5.model.UserDAO;
import hw5.model.CommentDAO;

public class DeleteCommentAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = new FormBeanFactory<>(IdForm.class);
	
	private UserDAO userDAO;
    private CommentDAO commentDAO;
    
    public DeleteCommentAction(Model model) {
        commentDAO = model.getCommentDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "deletecomment.do";
    }

    public String performPost(HttpServletRequest request) {
       
    		HttpSession session = request.getSession();
        try {
            IdForm form = formBeanFactory.create(request);
           
            if (form.hasValidationErrors()) {
                return "Visitor.do";
            }
            boolean flag = false;
            
            User deletor = userDAO.read(commentDAO.read(form.getIdAsInt()).getUserEmail());
            if(deletor.getEmail().equals(((User)session.getAttribute("user")).getEmail())) {
            	flag = true;
            }
            commentDAO.delete(form.getIdAsInt());

            request.setAttribute("comments", commentDAO.getComments());
            if (flag == true)
            		return "Visitor.do";
            else {
            		return "Home.do";
            }

        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
