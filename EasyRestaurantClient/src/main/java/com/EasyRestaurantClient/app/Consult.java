package com.EasyRestaurantClient.app;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Consult extends JFrame {

	private JPanel contentPane;
	private JTabbedPane pestañas;
	private String user;
	private ResourceBundle mybundle;
	private Restaurant r;
	private Favourites f;
	private Books b;

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
		Locale.setDefault(Locale.getDefault());

		mybundle = ResourceBundle.getBundle("Resource");

//		System.out.println("Consult Locale is " +Locale.getDefault().toString());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		r = new Restaurant(user);
		f = new Favourites(user);
		b= new Books(user);
		pestañas = new JTabbedPane(JTabbedPane.TOP);
		pestañas.add(mybundle.getString("restaurants"), r);
		pestañas.add(mybundle.getString("favourites"), f);
		pestañas.add(mybundle.getString("books"), b);
		pestañas.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				switch (pestañas.getSelectedIndex()){
					case 0:
					case 1:
						f.update();
					case 2:
						b.update_list();

				}
			}
		});
		contentPane.add(pestañas, BorderLayout.CENTER);
	}

}
