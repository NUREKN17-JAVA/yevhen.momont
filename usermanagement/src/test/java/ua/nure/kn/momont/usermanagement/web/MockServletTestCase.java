package ua.nure.kn.momont.usermanagement.web;

import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ua.nure.kn.momont.usermanagement.db.DaoFactory;
import ua.nure.kn.momont.usermanagement.db.MockDaoFactory;
import ua.nure.kn.momont.usermanagement.db.MockUserDao;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {
	
	private Mock mockUserDao;

	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockUserDao.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
	}

	@AfterEach
	protected void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
