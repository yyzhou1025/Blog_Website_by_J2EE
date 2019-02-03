package hw5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.genericdao.RollbackException;


import hw5.databean.User;
import hw5.formbean.LoginForm;
import hw5.model.Model;
import hw5.model.UserDAO;

public class LoginAction extends Action {
    private UserDAO userDAO;
   // private FormBeanFactory<LoginForm> formBeanFactory = new FormBeanFactory<>(LoginForm.class);
    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "Login.do";
    }
    
    
    public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to home.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "Home.do";
        }
        
        // Otherwise, just display the login page.
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        try {
            request.setAttribute("users", userDAO.getUsers());
            return "Login.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
        
    }

    
    public String performPost(HttpServletRequest request) {
        // If user is already logged in, redirect to home.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "Home.do";
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
        try {
            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);
            request.setAttribute("users", userDAO.getUsers());
            
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            		return "Login.jsp";
            }
            
            if (form.getButton().equals("Register")) {
                return "Register.do";
            }
            // Look up the user
            User user = userDAO.read(form.getEmail());
            if (user == null) {
                errors.add("No such user, please register first");
                return "Login.jsp";
            }

            if (!form.getPassword().equals(user.getPassword())) {
                errors.add("Incorrect password");
                return "Login.jsp";
            }
            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);
            
            // If redirectTo is null, redirect to the "todolist" action
            return "Home.do";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
