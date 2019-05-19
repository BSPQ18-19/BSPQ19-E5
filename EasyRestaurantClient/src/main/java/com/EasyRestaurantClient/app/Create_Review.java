package com.EasyRestaurantClient.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Create_Review extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textFieldScore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Create_Review frame = new Create_Review();
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
	public Create_Review() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelAddReview = new JPanel();
		contentPane.add(panelAddReview, BorderLayout.CENTER);
		panelAddReview.setLayout(null);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComments.setBounds(23, 26, 100, 17);
		panelAddReview.add(lblComments);
		
		JTextPane textPaneComments = new JTextPane();
		textPaneComments.setBounds(23, 56, 338, 122);
		panelAddReview.add(textPaneComments);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setBounds(23, 191, 66, 17);
		panelAddReview.add(lblScore);
		
		textFieldScore = new JTextField();
		textFieldScore.setBounds(76, 191, 116, 22);
		panelAddReview.add(textFieldScore);
		textFieldScore.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(238, 205, 97, 25);
		panelAddReview.add(btnSubmit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
