package com.EasyRestaurantClient.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.util.ResourceBundle;

public class Menu extends JFrame {

	private JPanel contentPane;

	
	public Menu(String menu_r) {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("Resource");

		setTitle(resourceBundle.getString("restaurant.menu"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 200, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblMenu = new JLabel(resourceBundle.getString("menu"));
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMenu, BorderLayout.NORTH);
		
		JTextArea textAreaMenu = new JTextArea();
		textAreaMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAreaMenu.setEditable(false);
		String text_menu = "";
		for (String item: menu_r.split(";")){
			text_menu += item + '\n';
		}
		textAreaMenu.setText(text_menu);
		JScrollPane scroll = new JScrollPane(textAreaMenu);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll, BorderLayout.CENTER);
		
		
	}

}
