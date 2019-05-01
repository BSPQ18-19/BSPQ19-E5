package com.EasyRestaurantClient.app;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.List;
import java.awt.BorderLayout;

public class Books extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Books() {
//		setBackground(Color.DARK_GRAY);
		initialize();
	}
	
	public void initialize() {
		setLayout(null);
		
		JLabel lblListOfBooks = new JLabel("List of Books:");
		lblListOfBooks.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblListOfBooks.setForeground(Color.WHITE);
		lblListOfBooks.setBounds(40, 30, 119, 16);
		add(lblListOfBooks);
		
		JLabel lblName = new JLabel("Restaurant:");
//		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(234, 70, 89, 16);
		add(lblName);
		
		textField = new JTextField();
		textField.setBounds(335, 68, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location:");
//		lblLocation.setForeground(Color.WHITE);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocation.setBounds(234, 127, 89, 16);
		add(lblLocation);
		
		textField_1 = new JTextField();
		textField_1.setBounds(335, 125, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
//		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(234, 187, 56, 16);
		add(lblDate);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModify.setBounds(254, 260, 97, 25);
		add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(363, 260, 97, 25);
		add(btnDelete);

//		JDateChooser dateChooser = new JDateChooser();
//		dateChooser.setBounds(335, 187, 105, 22);
//		add(dateChooser);


		Panel panel = new Panel();
		panel.setBounds(32, 52, 180, 261);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JList list = new JList();
		panel.add(list, BorderLayout.CENTER);
	}
}
