package com.EasyRestaurantClient.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class Consult extends JFrame {

	private JPanel contentPane;
	private JTabbedPane pestañas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consult frame = new Consult();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Consult() {
		initialize();
	}
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Favourites f = new Favourites();
		Books b= new Books();
		pestañas = new JTabbedPane(JTabbedPane.TOP);
		pestañas.add("Restaurants", new JPanel());
		pestañas.add("Favourites", f);
		pestañas.add("Books", b);
		contentPane.add(pestañas, BorderLayout.CENTER);
	}

}
