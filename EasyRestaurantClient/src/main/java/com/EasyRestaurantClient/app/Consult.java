package com.EasyRestaurantClient.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class Consult extends JFrame {

	private JPanel contentPane;
	private JTabbedPane pestañas;
	private String user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consult frame = new Consult("carlos");
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
	public Consult(String user ) {
		this.user = user;
		initialize();
	}
	
	public void initialize() {
		//Language settings
		int lang = 0;
		final Locale englishLocale = new Locale("en_US");
		final Locale greekLocale = new Locale("el_GR");
		Locale.setDefault(greekLocale);
		if(lang == 0) Locale.setDefault(englishLocale);
		else Locale.setDefault(greekLocale);

		System.out.println("Consult Locale is " +Locale.getDefault().toString());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Restaurant r = new Restaurant();
		Favourites f = new Favourites(user,0);
		Books b= new Books(user);
		pestañas = new JTabbedPane(JTabbedPane.TOP);
		pestañas.add("Restaurants", r);
		pestañas.add("Favourites", f);
		pestañas.add("Books", b);
		contentPane.add(pestañas, BorderLayout.CENTER);
	}

}
