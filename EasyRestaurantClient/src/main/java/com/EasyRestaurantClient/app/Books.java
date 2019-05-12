package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Books extends JPanel {
	private JTextField restaurant_field;
	private JTextField number_field;
	private String user;

	/**
	 * Create the panel.
	 */
	public Books(String user) {
//		setBackground(Color.DARK_GRAY);
		this.user = user;
		initialize();
	}
	
	public void initialize() {
		setLayout(null);
		JSONObject filters = new JSONObject();
		filters.put("user", user);
		Reservations reservations = new Reservations();
		final JSONArray reservations_list = reservations.reservation_list(filters);

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
		
		restaurant_field = new JTextField();
		restaurant_field.setBounds(335, 68, 116, 22);
		add(restaurant_field);
		restaurant_field.setColumns(10);
		
		JLabel lblLocation = new JLabel("Number of clients:");
//		lblLocation.setForeground(Color.WHITE);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocation.setBounds(234, 127, 89, 16);
		add(lblLocation);
		
		number_field = new JTextField();
		number_field.setBounds(335, 125, 116, 22);
		add(number_field);
		number_field.setColumns(10);
		
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

		final DefaultListModel<String> listModel = new DefaultListModel<>();
		for (int i=0;i<reservations_list.length();i++) {
			JSONObject explrObject = reservations_list.getJSONObject(i);
			listModel.addElement(explrObject.getString("date"));
		}
		final JList list = new JList<>(listModel);
		list.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i=0;i<reservations_list.length();i++) {
					JSONObject explrObject = reservations_list.getJSONObject(i);
					if (explrObject.getString("date").equals(list.getSelectedValue())){
						restaurant_field.setText(explrObject.getString("restaurant"));
						number_field.setText(""+explrObject.getInt("number_clients"));
						break;
					}

				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		panel.add(list, BorderLayout.CENTER);
	}
}
