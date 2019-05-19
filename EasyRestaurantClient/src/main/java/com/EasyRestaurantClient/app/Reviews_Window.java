package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Reviews_Window extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textFieldScore;
	private JButton btnAddReview;
	private JSONObject current;
	private JSONArray reviews_list;
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private JList list;
	private Reviews reviews = new Reviews();

	public void update(String restaurant){
		JSONObject filters = new JSONObject();
		reviews_list = reviews.reviews_list(restaurant);
		listModel = new DefaultListModel<>();
		for (int i=0;i<reviews_list.length();i++) {
			JSONObject explrObject = reviews_list.getJSONObject(i);
			listModel.addElement(explrObject.getString("date")+"-"+Integer.toString(explrObject.getInt("id")));
		}
		list.setModel(listModel);
	}

	/**
	 * Create the frame.
	 */
	public Reviews_Window(final String name_of_Restaurant) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		reviews_list = reviews.reviews_list(name_of_Restaurant);
		setBounds(100, 100, 582, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelPrincipal = new JPanel();
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		
		JLabel lblListOfReviews = new JLabel("List of reviews:");
		lblListOfReviews.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListOfReviews.setBounds(54, 19, 140, 25);
		panelPrincipal.add(lblListOfReviews);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComments.setBounds(325, 37, 95, 17);
		panelPrincipal.add(lblComments);
		
		final JTextPane textPaneComments = new JTextPane();
		textPaneComments.setBounds(325, 67, 203, 155);
		textPaneComments.setEditable(false);
		panelPrincipal.add(textPaneComments);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setBounds(325, 247, 54, 17);
		panelPrincipal.add(lblScore);
		
		textFieldScore = new JTextField();
		textFieldScore.setBounds(378, 245, 116, 22);
		panelPrincipal.add(textFieldScore);
		textFieldScore.setEditable(false);
		textFieldScore.setColumns(10);
		
		btnAddReview = new JButton("Add Review");
		btnAddReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton pressedButton = (JButton)e.getSource();
				if(pressedButton == btnAddReview) {
					Create_Review cr = new Create_Review(name_of_Restaurant, Reviews_Window.this);
					cr.setVisible(true);
				}	
			}
		});
		btnAddReview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddReview.setBounds(362, 302, 106, 39);

		Panel panel = new Panel();
		panel.setBounds(26, 50, 150, 240);
		panel.setLayout(new BorderLayout(0, 0));
		for (int i=0;i<reviews_list.length();i++) {
			JSONObject explrObject = reviews_list.getJSONObject(i);
			listModel.addElement(explrObject.getString("date")+"-"+Integer.toString(explrObject.getInt("id")));
		}
		list = new JList<>(listModel);
		list.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i=0;i<reviews_list.length();i++) {
					JSONObject explrObject = reviews_list.getJSONObject(i);
					if ((explrObject.getString("date")+"-"+Integer.toString(explrObject.getInt("id"))).equals(list.getSelectedValue())){
						current = explrObject;
						float score = explrObject.getFloat("score");
						textFieldScore.setText(Float.toString(score));
						textPaneComments.setText(explrObject.getString("comments"));
						break;
					}

				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		panel.add(list, BorderLayout.CENTER);
		panelPrincipal.add(panel);
//		panelPrincipal.add(list);
		panelPrincipal.add(btnAddReview);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub			
	}
}
