package com.EasyRestaurantClient.app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.json.JSONArray;
import org.json.JSONObject;

public class Book extends JPanel {

	/**
	 * Create the panel.
	 */
	public Book() {
		setLayout(null);
		JSONObject reservation=getBook();
		
		JLabel lblListOfBooks = new JLabel("List of books");
		lblListOfBooks.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfBooks.setBounds(65, 70, 184, 30);
		add(lblListOfBooks);
		
		JList list = new JList();
		list.setBounds(30, 128, 219, 346);
		add(list);
		
		JLabel lblRestaurantName = new JLabel("Restaurant name");
		lblRestaurantName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRestaurantName.setBounds(289, 142, 246, 20);
		lblRestaurantName.setText(reservation.getString("restaurant"));
		add(lblRestaurantName);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocation.setBounds(289, 255, 246, 20);
		lblLocation.setText(reservation.getString("localization"));
		add(lblLocation);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(289, 366, 246, 20);
		lblDate.setText(reservation.getString("date"));
		add(lblDate);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				BookModification modification= new BookModification();
				//Need to be added to the master frame
				
			}
		});
		btnModify.setBounds(283, 430, 89, 23);
		add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(415, 430, 89, 23);
		add(btnDelete);

	}
	public JSONObject getBook() {
		//need to be added the search reservation code
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("number_clients", 3);
		jsonObject.put("date", "2019-04-12T23:00:00Z");
		jsonObject.put("restaurant", "Txacoli");


		return jsonObject;
	}
}