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
		lblListOfBooks.setBounds(30, 23, 184, 30);
		add(lblListOfBooks);
		
		JList list = new JList();
		list.setBounds(10, 64, 219, 249);
		add(list);
		
		JLabel lblRestaurantName = new JLabel("Restaurant name");
		lblRestaurantName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRestaurantName.setBounds(239, 64, 246, 20);
		lblRestaurantName.setText(reservation.getString("restaurant"));
		add(lblRestaurantName);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocation.setBounds(239, 127, 246, 20);
		lblLocation.setText(reservation.getString("localization"));
		add(lblLocation);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(239, 246, 246, 20);
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
		btnModify.setBounds(236, 342, 89, 23);
		add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(376, 342, 89, 23);
		add(btnDelete);
		
		JButton btnFavourite = new JButton("Favourite");
		btnFavourite.setBounds(396, 11, 89, 23);
		add(btnFavourite);

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
