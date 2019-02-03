package hw5.model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import hw5.databean.PostBean;

public class PostDAO extends GenericDAO<PostBean> {
	public PostDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PostBean.class, tableName, cp);
	}

	public void addToTop(PostBean post) throws RollbackException {
		try {
			Transaction.begin();

			// Get item at top of list
			PostBean[] a = match(MatchArg.min("position"));

			PostBean topBean;
			if (a.length == 0) {
				topBean = null;
			} else {
				topBean = a[0];
			}

			int newPos;
			if (topBean == null) {
				// List is empty...just add it with position = 1
				newPos = 1;
			} else {
				// Create the new item with position one less than the top
				// bean's position
				newPos = topBean.getPosition() - 1;
			}

			post.setPosition(newPos);

			// Create a new ItemBean in the database with the next id number
			// Note that GenericDAO.create() will use auto-increment when
			// the primary key field is an int or a long.
			create(post);

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}


	public PostBean[] getPosts() throws RollbackException {

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans
		PostBean[] items = match();
		
		Arrays.sort(items, (PostBean i1, PostBean i2) -> i1.getPosition() - i2.getPosition());

		return items;
	}

}