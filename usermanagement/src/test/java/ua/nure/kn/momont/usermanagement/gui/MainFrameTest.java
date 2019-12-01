package ua.nure.kn.momont.usermanagement.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn.momont.usermanagement.User;
import ua.nure.kn.momont.usermanagement.db.DaoFactory;
import ua.nure.kn.momont.usermanagement.db.DaoFactoryImplementation;
import ua.nure.kn.momont.usermanagement.db.MockDaoFactory;
import ua.nure.kn.momont.usermanagement.db.MockUserDao;

public class MainFrameTest extends JFCTestCase {

	private static final Date DATE = new Date();
	private static final String LASTNAME = "Doe";
	private static final String FIRSTNAME = "John";

	private MainFrame mainFrame;
	
	private Mock mockUserDao;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		Properties properties = new Properties();
//		properties.setProperty("dao.ua.nure.kn.momont.usermanagement.db.UserDao", MockUserDao.class.getName());
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		
		mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
		mockUserDao.expectAndReturn("findAll", new ArrayList());
		
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Component find(Class componentClass, String name) {
		NamedComponentFinder componentFinder;
		componentFinder = new NamedComponentFinder(componentClass, name);
		componentFinder.setWait(0);
		Component component = componentFinder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component);
		return component;
	}
	
	public void testBrowseControls() {
		find(JPanel.class, "browsePanel");
		
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(3, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("Имя", table.getColumnName(1));
		assertEquals("Фамилия", table.getColumnName(2));
		
		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailsButton");
	}

	public void testAddUser() {

			try {
//				String firstname = FIRSTNAME;
//				String lastname = LASTNAME;
//				Date now = DATE;
				
				User user = new User(FIRSTNAME, LASTNAME, DATE);
				
				User expectedUser = new User(new Long(1), FIRSTNAME, LASTNAME, DATE);
				mockUserDao.expectAndReturn("create", user, expectedUser);
				
				ArrayList users = new ArrayList();
				users.add(expectedUser);
				mockUserDao.expectAndReturn("findAll", users);
				
				JTable table = (JTable) find(JTable.class, "userTable");
				assertEquals(0, table.getRowCount());
				
				JButton addButton = (JButton) find(JButton.class, "addButton");
				getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
				
				find(JPanel.class, "addPanel");
				
				JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
				JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
				JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
				
				JButton okButton = (JButton) find(JButton.class, "okButton");
				find(JButton.class, "cancelButton");
				
				
				getHelper().sendString(new StringEventData(this, firstNameField, FIRSTNAME));
				
				getHelper().sendString(new StringEventData(this, lastNameField, FIRSTNAME));
				DateFormat formatter = DateFormat.getDateInstance();
				
				String date = formatter.format(DATE);
				getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
				
				getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
				
				find(JPanel.class, "browsePanel");
				table = (JTable) find(JTable.class, "userTable");
				assertEquals(1, table.getRowCount());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	public void testEditUser() {
		User expectedUser = new User(new Long(1), FIRSTNAME, LASTNAME, DATE);
		
		//mockUserDao.expectAndReturn("update", expectedUser);
		mockUserDao.expect("update", expectedUser);
		ArrayList users = new ArrayList();
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll", users);
		
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
		
		
		JButton editButton = (JButton) find(JButton.class, "editButton");
		getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
		getHelper().enterClickAndLeave(new MouseEventData(this, editButton));
		
		find(JPanel.class, "editPanel");
		
		JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
		
		getHelper().sendString(new StringEventData(this, firstNameField, FIRSTNAME));
		getHelper().sendString(new StringEventData(this, lastNameField, LASTNAME));
		
		JButton okButton = (JButton) find(JButton.class, "okButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, "browsePanel");
		
		table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
	}
	
	public void testDeletePanel() {
		User expectedUser = new User(new Long(1), FIRSTNAME, LASTNAME, DATE);
		mockUserDao.expect("delete", expectedUser);
		List<User> users = new ArrayList<User>();
		mockUserDao.expectAndReturn("findAll", users);
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
        JButton deleteButton = (JButton) find(JButton.class, "deleteButton");
        getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
        getHelper().enterClickAndLeave(new MouseEventData(this, deleteButton));
        find(JPanel.class, "browsePanel");
        table = (JTable) find(JTable.class, "userTable");
        assertEquals(0, table.getRowCount());
	}
	
	public void testDetailsPanel() {
		
	}
	
}
