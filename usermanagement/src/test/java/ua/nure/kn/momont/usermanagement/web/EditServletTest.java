package ua.nure.kn.momont.usermanagement.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.nure.kn.momont.usermanagement.User;

class EditServletTest extends MockServletTestCase {

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}

	public void testEdit() {
		Date date = new Date();
		User user = new User(new Long(1000), "John", "Joe", date);
		getMockUserDao().expect("update", user);
		
		addRequestParameter("id", "1000");
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}
	
	public void testEditEmptyDate() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message", errorMessage);
	}
	public void testEditEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", "");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message", errorMessage);
	}
	public void testEditEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message", errorMessage);
	}
	public void testEditEmptyLastName() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstname", "John");
		addRequestParameter("lastname", "Joe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message", errorMessage);
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
