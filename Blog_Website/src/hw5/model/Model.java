package hw5.model;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
    private PostDAO postDAO;
    private UserDAO userDAO;
    private CommentDAO commentDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL = config.getInitParameter("jdbcURL");

            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

            userDAO = new UserDAO(pool, "yuanyuaz_user");
            postDAO = new PostDAO(pool, "yuanyuaz_post");
            commentDAO = new CommentDAO(pool,"yuanyuaz_comment");
            
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }
    public PostDAO getPostDAO() {
        return postDAO;
    }
    public UserDAO getUserDAO() {
        return userDAO;
    }
}
