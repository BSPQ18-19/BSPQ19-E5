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
import java.util.Locale;
import java.util.ResourceBundle;

public class Books extends JPanel {
	private JTextField restaurant_field;
	private JTextField number_field;
	private JTextField date_field;
	private String user;
	private Reservations reservations = new Reservations();
	private JSONObject current;
	private JSONArray reservations_list;
	private DefaultListModel<String> listModel;
	private JList list;
	private JSONObject filters;

	/**
	 * Create the panel.
	 */
	public Books(String user) {
//		setBackground(Color.DARK_GRAY);
		this.user = user;
		initialize();
	}

	public void update_list(){
		reservations_list = reservations.reservation_list(filters);
		listModel.clear();
		for (int i=0;i<reservations_list.length();i++) {
			JSONObject explrObject = reservations_list.getJSONObject(i);
			listModel.addElement(explrObject.getString("date")+"-"+String.valueOf(explrObject.getInt("id")));
		}
	}
	
	public void initialize() {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("Resource");


		setLayout(null);
		filters = new JSONObject();
		filters.put("user", user);
		reservations_list = reservations.reservation_list(filters);

		JLabel lblListOfBooks = new JLabel(resourceBundle.getString("list.of.books"));
		lblListOfBooks.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblListOfBooks.setForeground(Color.WHITE);
		lblListOfBooks.setBounds(40, 30, 119, 16);
		add(lblListOfBooks);
		
		JLabel lblName = new JLabel(resourceBundle.getString("Restname"));
//		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(234, 70, 89, 16);
		add(lblName);
		
		restaurant_field = new JTextField();
		restaurant_field.setBounds(335, 68, 130, 22);
		restaurant_field.setEditable(false);
		add(restaurant_field);
		restaurant_field.setColumns(10);
		
		JLabel lblLocation = new JLabel(resourceBundle.getString("number.of.clients"));
//		lblLocation.setForeground(Color.WHITE);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocation.setBounds(234, 127, 89, 16);
		add(lblLocation);
		
		number_field = new JTextField();
		number_field.setBounds(335, 125, 130, 22);
		add(number_field);
		number_field.setColumns(10);
		
		JLabel lblDate = new JLabel(resourceBundle.getString("Date") + ":");
//		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(234, 187, 56, 16);
		add(lblDate);

		date_field = new JTextField();
		date_field.setBounds(335, 185, 130, 22);
		add(date_field);
		date_field.setColumns(10);
		
		JButton btnModify = new JButton(resourceBundle.getString("modify"));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = String.valueOf(current.getInt("id"));
				reservations.update_reservation("2019-04-12 23:00", Integer.parseInt(number_field.getText()), id);
				update_list();
			}
		});
		btnModify.setBounds(254, 260, 97, 25);
		add(btnModify);
		
		JButton btnDelete = new JButton(resourceBundle.getString("delete"));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = String.valueOf(current.getInt("id"));
				reservations.remove_reservation(id);
				update_list();
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

		listModel = new DefaultListModel<>();
		for (int i=0;i<reservations_list.length();i++) {
			JSONObject explrObject = reservations_list.getJSONObject(i);
			listModel.addElement(explrObject.getString("date")+"-"+String.valueOf(explrObject.getInt("id")));
		}
		list = new JList<>(listModel);
		list.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i=0;i<reservations_list.length();i++) {
					JSONObject explrObject = reservations_list.getJSONObject(i);
					if ((explrObject.getString("date")+"-"+String.valueOf(explrObject.getInt("id"))).equals(list.getSelectedValue())){
						current = explrObject;
						restaurant_field.setText(explrObject.getString("restaurant"));
						number_field.setText(""+explrObject.getInt("number_clients"));
						date_field.setText(explrObject.getString("date"));
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


