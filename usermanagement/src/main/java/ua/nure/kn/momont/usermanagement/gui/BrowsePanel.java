package ua.nure.kn.momont.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	
	private JPanel buttonPanel;
	
	private JButton addButton;
	private JButton addDetails;
	private JButton deleteButton;
	private JButton editButton;
	
	private JScrollPane tablePane;

	private JTable userTable;
	
	public BrowsePanel(MainFrame mainFrame) {
		parent = mainFrame;
		initialize();
	}
	
	private void initialize() {
		this.setName("browsePanel");
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
	}

	private JPanel getButtonsPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(), null);
			buttonPanel.add(getEditButton(), null);
			buttonPanel.add(getDeleteButton(), null);
			buttonPanel.add(getDetailsButton(), null);
		}
		return buttonPanel;
	} 

	private JScrollPane getTablePanel() {
		if (tablePane == null) {
			tablePane = new JScrollPane(getUserTable());
		}
		return tablePane;
	}
	
	private JTable getUserTable() {
		if (userTable == null) {
			userTable = new JTable();
			userTable.setName("userTable");
		}
		return userTable;
	}

	private JButton getDetailsButton() {
		if (addDetails == null) {
			addDetails = new JButton();
			addDetails.setText("Details");
			addDetails.setName("detailsButton");
			addDetails.addActionListener(this);
		}
		return addDetails;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("Delete");
			deleteButton.setName("deleteButton");
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getEditButton() {
		if (editButton == null) {
			editButton = new JButton();
			editButton.setText("Edit");
			editButton.setName("editButton");
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("Add");
			addButton.setName("addButton");
			addButton.addActionListener(this);
		}
		return addButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if ("add".equalsIgnoreCase(actionCommand)) {
			this.setVisible(false);
			parent.showAddPanel();
		}
		
	}

	

	
	
}
