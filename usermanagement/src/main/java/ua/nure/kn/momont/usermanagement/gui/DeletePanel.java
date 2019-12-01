package ua.nure.kn.momont.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.momont.usermanagement.User;
import ua.nure.kn.momont.usermanagement.db.DaoFactory;
import ua.nure.kn.momont.usermanagement.db.DataBaseException;
import ua.nure.kn.momont.usermanagement.db.UserDao;

public class DeletePanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8427643754115331585L;
	
	private MainFrame parent;
	
	private UserDao userDao;

	private User user;

	private JPanel buttonPanel;
	
	private JButton cancelButton;
	private JButton okButton;

	private JLabel sureText;

	private JTextField lastNameField;

	private JTextField dateOfBirthField;

	private JTextField firstNameField;
	
	public DeletePanel(MainFrame parent) {
		this.parent = parent;
		initialize();
	}
	
	private void initialize() {
		this.setName("deletePanel");
		this.setLayout(new BorderLayout());
		this.add(getTextPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		userDao = DaoFactory.getInstance().getUserDao();
//		this.user = user;
	}
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}
	
	private JPanel getTextPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getSureText(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.setName("cancelButton");
			cancelButton.setActionCommand("cancel");
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("Ok");
			okButton.setName("okButton");
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return okButton;
	}
	
	private JLabel getSureText() {
		if (sureText == null) {
			sureText = new JLabel();
			sureText.setText("Are you sure to delete this user?");
			sureText.setName("sureText");
		}
		return sureText;
	}
	
	private JTextField getDateOfBirthField() {
		if (dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField"); //$NON-NLS-1$
		}
		return dateOfBirthField;
	}

	private JTextField getLastNameField() {
		if (lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField"); //$NON-NLS-1$
		}
		return lastNameField;
	}
	
	private JTextField getFirstNameField() {
		if (firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField"); //$NON-NLS-1$
		}
		return firstNameField;
	}
	
	public void setUser(User user) {
		getFirstNameField().setText(user.getFirstName());
		getLastNameField().setText(user.getLastName());
		DateFormat format = DateFormat.getDateInstance();
		getDateOfBirthField().setText(format.format(user.getDateOfBirth()));
	}

	public void actionPerformed(ActionEvent e) {
		if ("ok".equalsIgnoreCase(e.getActionCommand())) {
			try {
				userDao.delete(this.user);
			} catch (DataBaseException e1) {
				e1.printStackTrace();
			}
		}
		this.setVisible(false);
		parent.showBrowsePanel();
	}
}
