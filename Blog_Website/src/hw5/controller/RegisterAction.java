package hw5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;


import hw5.formbean.RegisterForm;
import hw5.databean.User;
import hw5.formbean.LoginForm;
import hw5.model.Model;
import hw5.model.UserDAO;
import org.genericdao.Transaction;

public class RegisterAction extends Action {
    private UserDAO userDAO;
    //private FormBeanFactory<LoginForm> formBeanFactory = new FormBeanFactory<>(LoginForm.class);
    public RegisterAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "Register.do";
    }
    
    public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to home.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "Home.do";
        }
        
        // Otherwise, just display the register page.
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        try {
            request.setAttribute("users", userDAO.getUsers());
            return "Register.jsp";
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
            RegisterForm form = new RegisterForm(request);
            request.setAttribute("form", form);
            request.setAttribute("users", userDAO.getUsers());
            // get validation errors
            errors.addAll(form.getValidationErrors());
           // Any validation errors?
            if (errors.size() != 0) {
                return "Register.jsp";
            }
            //create a new user
            User user = new User();
            user.setEmail(form.getEmail());
            user.setPassword(form.getPassword1());
            user.setFirstname(form.getFirstName());
            user.setLastname(form.getLastName());
            try {
                userDAO.create(user);
                session.setAttribute("user", user);
                return ("Home.do");
            } catch (DuplicateKeyException e) {
                errors.add("A user with this email already exists");
                return "Register.jsp";
            }

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
