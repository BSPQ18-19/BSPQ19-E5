package com.EasyRestaurantClient.app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.JButton;

public class BookModification extends JPanel {

	/**
	 * Create the panel.
	 */
	public BookModification() {
		setLayout(null);
		
		JLabel lblRestaurant = new JLabel("Restaurant");
		lblRestaurant.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRestaurant.setBounds(38, 35, 91, 20);
		add(lblRestaurant);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(158, 35, 205, 20);
		add(editorPane);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(38, 90, 91, 20);
		add(lblDate);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(158, 90, 205, 20);
		add(editorPane_1);
		
		JLabel lblHour = new JLabel("Hour");
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHour.setBounds(38, 139, 91, 20);
		add(lblHour);
		
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setBounds(158, 139, 205, 20);
		add(editorPane_2);
		
		JLabel lblComme = new JLabel("Commentary");
		lblComme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblComme.setBounds(189, 189, 104, 20);
		add(lblComme);
		
		JEditorPane editorPane_3 = new JEditorPane();
		editorPane_3.setBounds(33, 220, 428, 95);
		add(editorPane_3);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(189, 340, 89, 23);
		add(btnModify);

	}

}
