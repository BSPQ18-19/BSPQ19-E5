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
import java.util.ResourceBundle;

public class Create_Review extends JFrame{

	private JPanel contentPane;
	private JTextField textFieldScore;
	private Reviews reviews = new Reviews();

	/**
	 * Create the frame.
	 */
	public Create_Review(final String name_of_restaurant, final Reviews_Window r) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Resource");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelAddReview = new JPanel();
		contentPane.add(panelAddReview, BorderLayout.CENTER);
		panelAddReview.setLayout(null);
		
		JLabel lblComments = new JLabel(resourceBundle.getString("comments"));
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComments.setBounds(23, 6, 100, 17);
		panelAddReview.add(lblComments);
		
		final JTextPane textPaneComments = new JTextPane();
		textPaneComments.setBounds(23, 36, 338, 122);
		panelAddReview.add(textPaneComments);
		
		JLabel lblScore = new JLabel(resourceBundle.getString("score")+":");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setBounds(23, 171, 66, 17);
		panelAddReview.add(lblScore);
		
		textFieldScore = new JTextField();
		textFieldScore.setBounds(76, 171, 116, 22);
		panelAddReview.add(textFieldScore);
		textFieldScore.setColumns(10);
		
		JButton btnSubmit = new JButton(resourceBundle.getString("submit"));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reviews.make_review(name_of_restaurant, textPaneComments.getText(), Double.valueOf(textFieldScore.getText()));
				r.update(name_of_restaurant);
				dispose();
			}
		});
		btnSubmit.setBounds(238, 171, 97, 25);
		panelAddReview.add(btnSubmit);
	}
}
