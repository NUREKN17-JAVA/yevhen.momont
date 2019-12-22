package ua.nure.kn.momont.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.momont.usermanagement.User;
import ua.nure.kn.momont.usermanagement.db.DaoFactory;
import ua.nure.kn.momont.usermanagement.db.DataBaseException;

public class AddServlet extends HttpServlet {

	
	protected void processUser(User user) throws DataBaseException {
		DaoFactory.getInstance().getUserDao().create(user);
	}
	
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}
}
