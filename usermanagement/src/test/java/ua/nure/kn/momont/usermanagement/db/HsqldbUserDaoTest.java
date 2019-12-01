package ua.nure.kn.momont.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.momont.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;

	
	private static final String LASTNAME = "Momont";
	private static final String FIRSTNAME = "Yevhen";
	
	private static final String LASTNAME_UPDATE = "John";
	private static final String FIRSTNAME_UPDATE = "J";
	private static final Date DATE_OF_BIRTH_UPDATE = new Date(1999-20-12);
	
	private static final String LASTNAME_DELETE = "Jack";
	private static final String FIRSTNAME_DELETE = "Jo";
	private static final Date DATE_OF_BIRTH_DELETE = new Date(1999-20-12);
	
	private static final String LASTNAME_FIND = "Doe";
	private static final String FIRSTNAME_FIND = "John";
	
	public void testCreate() throws DataBaseException {
		User user = new User();
		user.setFirstName(FIRSTNAME);
		user.setLastName(LASTNAME);
		user.setDateOfBirth(new Date());
		assertNull(user.getId());
		User userTOCheck = (User) dao.create(user);
		assertNotNull(userTOCheck);
		assertNotNull(userTOCheck.getId());
	}
	
	public void testFindAll() throws DataBaseException {
		Collection<User> items = dao.findAll();
		assertNotNull(items);
		assertEquals(2, items.size());
	}
	
	public void testUpdate() throws DataBaseException {
		User user = new User();
		user.setFirstName(FIRSTNAME);
		user.setLastName(LASTNAME);
		user.setDateOfBirth(new Date());
		dao.create(user);
		Long id = user.getId();
		user.setFirstName(FIRSTNAME_UPDATE);
		user.setLastName(LASTNAME_UPDATE);
		user.setDateOfBirth(DATE_OF_BIRTH_UPDATE);
		dao.update(user);
		User check = dao.find(id);
		
		assertEquals(FIRSTNAME_UPDATE, check.getFirstName());
		assertEquals(LASTNAME_UPDATE, check.getLastName());
//		assertEquals(DATE_OF_BIRTH_UPDATE, check.getDateOfBirth());
	}
	
	public void testDelete() throws DataBaseException {
		User user = new User();
		user.setFirstName(FIRSTNAME_DELETE);
		user.setLastName(LASTNAME_DELETE);
		user.setDateOfBirth(DATE_OF_BIRTH_DELETE);
		dao.create(user);
		Long id = user.getId();
		dao.delete(user);
		User check = dao.find(id);
		assertNull(check.getId());
	}
	
	public void testFind() throws DataBaseException {
		User user = new User();
		user.setFirstName(LASTNAME_FIND);
		user.setLastName(FIRSTNAME_FIND);
		user.setDateOfBirth(new Date());;
		User check = dao.create(user);
		
		User finded = dao.find(check.getId());
		
		assertEquals(finded.getFirstName(), check.getFirstName());
		assertEquals(finded.getLastName(), check.getLastName());
	}
	
	protected void setUp() throws Exception {
		  super.setUp();
		  this.dao = new HsqldbUserDao(connectionFactory);
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImplementation("org.hsqldb.jdbcDriver",
				"jdbc:hsqldb:file:db/usermanagement", "sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(
		        getClass().getClassLoader().getResourceAsStream("userDataSet.xml"));
		return dataSet;
	}

}
