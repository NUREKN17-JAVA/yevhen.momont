package ua.nure.kn.momont.usermanagement.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.nure.kn.momont.usermanagement.User;

class BrowseServletTest extends MockServletTestCase {

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}

	public void testBrowse() {
		User user = new User(new Long(1000), "John", "Joe", new Date());
		List list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session", collection);
		assertSame(list, collection);
	}
	
	public void testEdit() {
		User user = new User(new Long(1000), "John", "Joe", new Date());
		getMockUserDao().expectAndReturn("find", new Long(1000), user);
		addRequestParameter("editButton", "edit");
		addRequestParameter("id", "1000");
		doPost();
		User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find user in session", user);
		assertSame(user, userInSession);
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
