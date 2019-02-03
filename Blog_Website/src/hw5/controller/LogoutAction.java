package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hw5.model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {

    public LogoutAction(Model model) {
    }

    public String getName() {
        return "Logout.do";
    }
    public String performGet(HttpServletRequest request) {
       
        return performPost(request);
    } 
    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);

        return "Login.do";
    }
}
