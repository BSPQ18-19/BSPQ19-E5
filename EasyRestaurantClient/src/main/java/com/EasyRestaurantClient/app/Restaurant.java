package com.EasyRestaurantClient.app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class Restaurant extends JPanel {
	private JTextField txtNamefield;
	private JTextField txtLocationfield;
	private JTextField txtDatefield;
	private JTextField txtScorefield;

	/**
	 * Create the panel.
	 */
	public Restaurant() {
		setBorder(null);
		setLayout(null);
		ResourceBundle mybundle = ResourceBundle.getBundle("Resource");

		JLabel lblName = new JLabel(mybundle.getString("Name"));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(70, 52, 64, 14);
		add(lblName);
		
		JLabel lblLocation = new JLabel(mybundle.getString("location" )+":");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocation.setBounds(70, 102, 100, 14);
		add(lblLocation);
		
		txtNamefield = new JTextField();
		txtNamefield.setBounds(191, 50, 225, 23);
		add(txtNamefield);
		txtNamefield.setColumns(10);
		
		txtLocationfield = new JTextField();
		txtLocationfield.setBounds(191, 100, 225, 23);
		add(txtLocationfield);
		txtLocationfield.setColumns(10);
		
		JLabel lblSpecialty = new JLabel(mybundle.getString("speciality"));
		lblSpecialty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecialty.setBounds(34, 160, 64, 27);
		add(lblSpecialty);
		
		JCheckBox chckbxFastFood = new JCheckBox(mybundle.getString("fast.food"));
		chckbxFastFood.setBounds(34, 194, 97, 23);
		add(chckbxFastFood);
		
		JCheckBox chckbxCommon = new JCheckBox(mybundle.getString("common"));
		chckbxCommon.setBounds(150, 194, 110, 23);
		add(chckbxCommon);
		
		JCheckBox chckbxDessert = new JCheckBox(mybundle.getString("dessert"));
		chckbxDessert.setBounds(34, 231, 97, 23);
		add(chckbxDessert);
		
		JCheckBox chckbxInnovative = new JCheckBox(mybundle.getString("innovative"));
		chckbxInnovative.setBounds(150, 231, 97, 23);
		add(chckbxInnovative);
		
		JLabel lblType = new JLabel(mybundle.getString("type"));
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(297, 160, 58, 19);
		add(lblType);
		
		JCheckBox chckbxAsiatic = new JCheckBox(mybundle.getString("asiatic"));
		chckbxAsiatic.setBounds(297, 194, 97, 23);
		add(chckbxAsiatic);
		
		JCheckBox chckbxItalian = new JCheckBox(mybundle.getString("italian"));
		chckbxItalian.setBounds(297, 231, 97, 23);
		add(chckbxItalian);
		
		JCheckBox chckbxMexican = new JCheckBox(mybundle.getString("mexican"));
		chckbxMexican.setBounds(405, 194, 97, 23);
		add(chckbxMexican);
		
		JCheckBox chckbxLocal = new JCheckBox(mybundle.getString("local"));
		chckbxLocal.setBounds(405, 231, 97, 23);
		add(chckbxLocal);
		
		JLabel lblDate = new JLabel(mybundle.getString("Date"));
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(312, 366, 53, 14);
		add(lblDate);
		
		txtDatefield = new JTextField();
		txtDatefield.setText("mm/dd/yyyy");
		txtDatefield.setBounds(362, 363, 126, 17);
		add(txtDatefield);
		txtDatefield.setColumns(10);
		
		JLabel lblScore = new JLabel(mybundle.getString("score"));
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblScore.setBounds(52, 366, 46, 14);
		add(lblScore);
		
		txtScorefield = new JTextField();
		txtScorefield.setBounds(126, 363, 86, 20);
		add(txtScorefield);
		txtScorefield.setColumns(10);
		
		JButton btnSearch = new JButton(mybundle.getString("search"));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				makeReservation();
			}
		});
		btnSearch.setBounds(223, 300, 100, 23);
		add(btnSearch);
		

	}
	public void makeReservation() {
	
		      //Make the reservation JSON and send it to the server.
		
	}
}
