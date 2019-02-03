package hw5.controller;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;
import hw5.databean.User;
import hw5.model.Model;
import hw5.databean.PostBean;
import hw5.formbean.PostForm;
import hw5.model.PostDAO;

public class AddPostAction extends Action {
    private PostDAO postDAO;
    private FormBeanFactory<PostForm> formBeanFactory = new FormBeanFactory<>(PostForm.class);
    
    public AddPostAction(Model model) {
        postDAO = model.getPostDAO();
    }

    public String getName() {
        return "addpost.do";
    }

    public String performPost(HttpServletRequest request) {
        

        try {
        	PostForm form = formBeanFactory.create(request);
        	request.setAttribute("form", form);
            request.setAttribute("posts", postDAO.getPosts());   
            if (form.hasValidationErrors()) {
                return "Home.do";
            }
            
            PostBean bean = new PostBean();
            bean.setContent(form.getPost());
            bean.setDate(String.valueOf(new Date()));
            bean.setUserEmail(((User)request.getSession().getAttribute("user")).getEmail());
            
            if (form.getAction().equals("post")) {
                postDAO.addToTop(bean);
            }

            request.setAttribute("posts", postDAO.getPosts());
            return "Home.do";

        } catch (RollbackException e) {
        		request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
