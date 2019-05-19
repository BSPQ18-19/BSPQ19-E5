package com.EasyRestaurantClient.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reviews_Window extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textFieldScore;
	private JButton btnAddReview;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reviews_Window frame = new Reviews_Window();
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
	public Reviews_Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelPrincipal = new JPanel();
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		
		JPanel listOfReviews = new JPanel();
		listOfReviews.setBounds(54, 77, 231, 280);
		panelPrincipal.add(listOfReviews);
		
		JLabel lblListOfReviews = new JLabel("List of reviews:");
		lblListOfReviews.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListOfReviews.setBounds(54, 39, 140, 25);
		panelPrincipal.add(lblListOfReviews);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComments.setBounds(325, 77, 95, 17);
		panelPrincipal.add(lblComments);
		
		JTextPane textPaneComments = new JTextPane();
		textPaneComments.setBounds(325, 107, 203, 155);
		panelPrincipal.add(textPaneComments);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setBounds(325, 287, 54, 17);
		panelPrincipal.add(lblScore);
		
		textFieldScore = new JTextField();
		textFieldScore.setBounds(378, 285, 116, 22);
		panelPrincipal.add(textFieldScore);
		textFieldScore.setColumns(10);
		
		btnAddReview = new JButton("Add Review");
		btnAddReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton pressedButton = (JButton)e.getSource();
				if(pressedButton == btnAddReview) {
					Create_Review cr = new Create_Review();
					cr.setVisible(true);
				}	
			}
		});
		btnAddReview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddReview.setBounds(362, 342, 106, 39);
		panelPrincipal.add(btnAddReview);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub			
	}
}
