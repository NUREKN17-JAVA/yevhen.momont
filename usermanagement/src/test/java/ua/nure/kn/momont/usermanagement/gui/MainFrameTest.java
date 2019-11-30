package ua.nure.kn.momont.usermanagement.gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;
	
	protected void setUp() throws Exception {
		super.setUp();
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper().cleanUp(this);
		super.tearDown();
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
		find(JTable.class, "userTable");
		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailsButton");
	}

	public void testAddUser() {
		JButton addButton = (JButton) find(JButton.class, "addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		
		find(JPanel.class, "addPanel");
		
		find(JTextField.class, "firstNameField");
		find(JTextField.class, "lastNameField");
		find(JTextField.class, "dateOfBirthField");
		
		find(JButton.class, "okButton");
		find(JButton.class, "cancelButton");
	}
	
}
