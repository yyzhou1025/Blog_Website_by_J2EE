package hw5.controller;
import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import hw5.databean.User;
import hw5.formbean.IdForm;
import hw5.model.Model;
import hw5.model.PostDAO;
import org.formbeanfactory.FormBeanFactory;
public class DeletePostAction extends Action {
    private PostDAO postDAO;
    private FormBeanFactory<IdForm> formBeanFactory = new FormBeanFactory<>(IdForm.class);
    public DeletePostAction(Model model) {
        postDAO = model.getPostDAO();
    }

    public String getName() {
        return "deletepost.do";
    }

    public String performPost(HttpServletRequest request) {
        

        try {
            IdForm form = formBeanFactory.create(request);
            if (form.hasValidationErrors()) {
                return "Home.do";
            }
           
            postDAO.delete(form.getIdAsInt());

            request.setAttribute("posts",postDAO.getPosts());
            return "Home.do";

        } catch (RollbackException e) {
        		request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
