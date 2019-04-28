package com.EasyRestaurantClient.app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import com.toedter.calendar.JCalendar;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.List;
import com.toedter.calendar.JDateChooser;

public class Favourites extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public Favourites() {
		setBackground(Color.DARK_GRAY);
		initialize();
	}
	
	public void initialize() {
		setLayout(null);
		
		JLabel lblListOfFavourites = new JLabel("List of favourites:");
		lblListOfFavourites.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListOfFavourites.setForeground(Color.WHITE);
		lblListOfFavourites.setBackground(Color.WHITE);
		lblListOfFavourites.setBounds(26, 30, 119, 16);
		add(lblListOfFavourites);
		
		JButton btnNewButton = new JButton("Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(178, 281, 70, 25);
		add(btnNewButton);
		
		JButton btnReview = new JButton("Review");
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReview.setBounds(260, 281, 79, 25);
		add(btnReview);
		
		JButton btnCheckMenu = new JButton("Check Menu");
		btnCheckMenu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCheckMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCheckMenu.setBounds(351, 281, 119, 25);
		add(btnCheckMenu);
		
		JLabel lblNmae = new JLabel("Name:");
		lblNmae.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmae.setForeground(Color.WHITE);
		lblNmae.setBounds(209, 30, 56, 16);
		add(lblNmae);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocation.setForeground(Color.WHITE);
		lblLocation.setBounds(209, 65, 70, 16);
		add(lblLocation);
		
		JLabel lblSpeciality = new JLabel("Speciality:");
		lblSpeciality.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpeciality.setForeground(Color.WHITE);
		lblSpeciality.setBounds(209, 100, 70, 16);
		add(lblSpeciality);
		
		textField = new JTextField();
		textField.setBounds(309, 28, 161, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(309, 63, 161, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Type:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(209, 135, 56, 16);
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(309, 98, 103, 22);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(309, 133, 103, 22);
		add(comboBox_1);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setForeground(Color.WHITE);
		lblScore.setBounds(209, 170, 56, 16);
		add(lblScore);
		
		JLabel lblSchedule = new JLabel("Schedule:");
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSchedule.setForeground(Color.WHITE);
		lblSchedule.setBounds(209, 205, 70, 16);
		add(lblSchedule);
		
		textField_2 = new JTextField();
		textField_2.setBounds(309, 168, 161, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		Panel panel = new Panel();
		panel.setBounds(26, 52, 147, 254);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		List list = new List();
		panel.add(list, BorderLayout.CENTER);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(309, 205, 105, 22);
		add(dateChooser);
	}
}
