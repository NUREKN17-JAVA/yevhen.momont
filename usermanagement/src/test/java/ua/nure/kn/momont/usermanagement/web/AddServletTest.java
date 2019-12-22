package ua.nure.kn.momont.usermanagement.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.nure.kn.momont.usermanagement.User;

class AddServletTest extends MockServletTestCase {

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}
	
	public void testAdd() {
		Date date = new Date();
		User user = new User(new Long(1000), "John", "Joe", date);
		User newUser = new User("John", "Joe", date);
		getMockUserDao().expectAndReturn("create", newUser, user);
		
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}

	public void testAddEmptyDate() {
		Date date = new Date();
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message", errorMessage);
	}
	public void testAddEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", "");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message", errorMessage);
	}
	public void testAddEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message", errorMessage);
	}
	public void testAddEmptyLastName() {
		Date date = new Date();
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message", errorMessage);
	}

}
