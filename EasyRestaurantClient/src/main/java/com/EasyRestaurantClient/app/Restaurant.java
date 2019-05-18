package com.EasyRestaurantClient.app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import org.json.JSONArray;
import org.json.JSONObject;

public class Restaurant extends JPanel {
	private JTextField txtNamefield;
	private JTextField txtLocationfield;
	private JTextField txtDatefield;
	private JTextField txtScorefield;
	JSONObject json=new JSONObject();

	/**
	 * Create the panel.
	 */
	public Restaurant() {
		
		setBorder(null);
		setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(34, 34, 64, 14);
		add(lblName);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocation.setBounds(34, 85, 64, 14);
		add(lblLocation);
		
		txtNamefield = new JTextField();
		txtNamefield.setBounds(108, 32, 225, 23);
		add(txtNamefield);
		json.put("Name", txtNamefield.getText());
		txtNamefield.setColumns(10);
		
		txtLocationfield = new JTextField();
		txtLocationfield.setBounds(108, 83, 225, 23);
		add(txtLocationfield);
		json.put("Location", txtLocationfield.getText());
		txtLocationfield.setColumns(10);
		
		JLabel lblSpecialty = new JLabel("Specialty");
		lblSpecialty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecialty.setBounds(79, 129, 64, 27);
		add(lblSpecialty);
		
		JCheckBox chckbxFastFood = new JCheckBox("Fast Food");
		chckbxFastFood.setBounds(20, 163, 97, 23);
		if(chckbxFastFood.isSelected()) {
			json.put("FastFood",true);
		}else {
			json.put("FastFood",false);
		}
		add(chckbxFastFood);
		
		JCheckBox chckbxCommon = new JCheckBox("Common");
		chckbxCommon.setBounds(124, 163, 97, 23);
		add(chckbxCommon);
		if(chckbxCommon.isSelected()) {
			json.put("Common",true);
		}else {
			json.put("Common",false);
		}
		
		JCheckBox chckbxDessert = new JCheckBox("Dessert");
		chckbxDessert.setBounds(20, 194, 97, 23);
		add(chckbxDessert);
		if(chckbxDessert.isSelected()) {
			json.put("Dessert",true);
		}else {
			json.put("Dessert",false);
		}
		
		JCheckBox chckbxInnovative = new JCheckBox("Innovative");
		chckbxInnovative.setBounds(124, 194, 97, 23);
		add(chckbxInnovative);
		if(chckbxInnovative.isSelected()) {
			json.put("Innovative",true);
		}else {
			json.put("Innovative",false);
		}
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(307, 133, 58, 19);
		add(lblType);
		
		JCheckBox chckbxAsiatic = new JCheckBox("Asiatic");
		chckbxAsiatic.setBounds(281, 163, 64, 23);
		add(chckbxAsiatic);
		if(chckbxAsiatic.isSelected()) {
			json.put("Asiatic",true);
		}else {
			json.put("Asiatic",false);
		}
		
		JCheckBox chckbxItalian = new JCheckBox("Italian");
		chckbxItalian.setBounds(281, 194, 64, 23);
		add(chckbxItalian);
		if(chckbxItalian.isSelected()) {
			json.put("Italian",true);
		}else {
			json.put("Italian",false);
		}
		
		JCheckBox chckbxMexican = new JCheckBox("Mexican");
		chckbxMexican.setBounds(353, 163, 97, 23);
		add(chckbxMexican);
		if(chckbxMexican.isSelected()) {
			json.put("Mexican",true);
		}else {
			json.put("Mexican",false);
		}
		
		JCheckBox chckbxLocal = new JCheckBox("Local");
		chckbxLocal.setBounds(353, 194, 97, 23);
		add(chckbxLocal);
		if(chckbxLocal.isSelected()) {
			json.put("Local",true);
		}else {
			json.put("Local",false);
		}
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(312, 366, 53, 14);
		add(lblDate);
		
		txtDatefield = new JTextField();
		txtDatefield.setText("mm/dd/yyyy");
		txtDatefield.setBounds(362, 363, 126, 17);
		add(txtDatefield);
		json.put("Date",txtDatefield.getText());
		txtDatefield.setColumns(10);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblScore.setBounds(52, 366, 46, 14);
		add(lblScore);
		
		txtScorefield = new JTextField();
		txtScorefield.setBounds(126, 363, 86, 20);
		add(txtScorefield);
		json.put("Score",txtScorefield.getText());
		txtScorefield.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				makeReservation(json);
				Book book=new Book();
				
			}
		});
		btnSearch.setBounds(193, 234, 89, 23);
		add(btnSearch);
		

	}
	public void makeReservation(JSONObject json) {
		
	
		      //Send the JSON TO the server.
		
	}
}
