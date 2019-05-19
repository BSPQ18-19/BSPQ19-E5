package com.EasyRestaurantClient.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JList;

public class Menu extends JFrame {

	private JPanel contentPane;

	
	public Menu() {
		setTitle("Restaurant Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMenu, BorderLayout.NORTH);
		
		JTextArea textAreaMenu = new JTextArea();
		contentPane.add(textAreaMenu, BorderLayout.CENTER);
		
		
	}

}
