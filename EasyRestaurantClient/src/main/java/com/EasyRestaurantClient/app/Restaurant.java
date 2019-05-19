package com.EasyRestaurantClient.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class Restaurant extends JPanel {
	private JTextField txtNamefield;
	private JTextField txtLocationfield;
	private JTextField txtDatefield;
	private JTextField txtScorefield;
	private JTextField schedule_field;
	private JSONObject filters =new JSONObject();
	private JTextField name_field;
	private JTextField location_field;
	private JTextField score_field;
	private JTextField speciality_field;
	private JTextField type_field;
	private String user;
	private  String name_of_Restaurant;
	private JSONObject current;

	/**
	 * Create the panel.
	 */
	public Restaurant(final String user) {
		this.user = user;
		initialize_filter();
	}
	public void initialize_filter(){
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
		txtNamefield.setColumns(10);

		txtLocationfield = new JTextField();
		txtLocationfield.setBounds(108, 83, 225, 23);
		add(txtLocationfield);
		txtLocationfield.setColumns(10);

		JLabel lblSpecialty = new JLabel("Specialty");
		lblSpecialty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecialty.setBounds(79, 129, 64, 27);
		add(lblSpecialty);

		JCheckBox chckbxFastFood = new JCheckBox("Fast Food");
		chckbxFastFood.setBounds(20, 163, 97, 23);
		if(chckbxFastFood.isSelected()) {
			filters.put("speciality","Fast Food");
		}
		add(chckbxFastFood);

		JCheckBox chckbxCommon = new JCheckBox("Common");
		chckbxCommon.setBounds(124, 163, 97, 23);
		add(chckbxCommon);
		if(chckbxCommon.isSelected()) {
			filters.put("speciality","Fast Food");
		}

		JCheckBox chckbxDessert = new JCheckBox("Dessert");
		chckbxDessert.setBounds(20, 194, 97, 23);
		add(chckbxDessert);
		if(chckbxDessert.isSelected()) {
			filters.put("speciality","Dessert");
		}

		JCheckBox chckbxInnovative = new JCheckBox("Innovative");
		chckbxInnovative.setBounds(124, 194, 97, 23);
		add(chckbxInnovative);
		if(chckbxInnovative.isSelected()) {
			filters.put("speciality","Innovative");
		}

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(307, 133, 58, 19);
		add(lblType);

		JCheckBox chckbxAsiatic = new JCheckBox("Asiatic");
		chckbxAsiatic.setBounds(281, 163, 64, 23);
		add(chckbxAsiatic);
		if(chckbxAsiatic.isSelected()) {
			filters.put("type","Asiatic");
		}

		JCheckBox chckbxItalian = new JCheckBox("Italian");
		chckbxItalian.setBounds(281, 194, 64, 23);
		add(chckbxItalian);
		if(chckbxItalian.isSelected()) {
			filters.put("type","Italian");
		}

		JCheckBox chckbxMexican = new JCheckBox("Mexican");
		chckbxMexican.setBounds(353, 163, 97, 23);
		add(chckbxMexican);
		if(chckbxMexican.isSelected()) {
			filters.put("type","Mexican");
		}

		JCheckBox chckbxLocal = new JCheckBox("Local");
		chckbxLocal.setBounds(353, 194, 97, 23);
		add(chckbxLocal);
		if(chckbxLocal.isSelected()) {
			filters.put("type","Local");
		}

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(312, 366, 53, 14);
		add(lblDate);

		txtDatefield = new JTextField();
		txtDatefield.setText("mm/dd/yyyy");
		txtDatefield.setBounds(362, 363, 126, 17);
		add(txtDatefield);
		txtDatefield.setColumns(10);

		JLabel lblScore = new JLabel("Score");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblScore.setBounds(52, 366, 46, 14);
		add(lblScore);

		txtScorefield = new JTextField();
		txtScorefield.setBounds(126, 363, 86, 20);
		add(txtScorefield);
		txtScorefield.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (txtNamefield.getText().length() > 0) filters.put("name", txtNamefield.getText());
				if (txtLocationfield.getText().length() > 0) filters.put("location", txtLocationfield.getText());
//				filters.put("Date",txtDatefield.getText());
//				filters.put("Score",txtScorefield.getText());
				removeAll();
				initialize_list();
				revalidate();
				repaint();

			}
		});
		btnSearch.setBounds(193, 234, 89, 23);
		add(btnSearch);
	}

	public void initialize_list() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Resource");

		setLayout(null);
		final Restaurants restaurants = new Restaurants();
		final JSONArray favourites_list = restaurants.restaurant_list(filters);

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeAll();
				filters = new JSONObject();
				initialize_filter();
				revalidate();
				repaint();
			}
		});
		btnBack.setBounds(0, 0, 89, 23);
		add(btnBack);

		JButton btnFavourite = new JButton("Favourite");
		btnFavourite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				restaurants.add_favourite(current.getInt("id"), user);
			}
		});
		btnFavourite.setBounds(420, 0, 90, 23);
		add(btnFavourite);

		JLabel lblListOfFavourites = new JLabel("List of restaurants");
		lblListOfFavourites.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblListOfFavourites.setForeground(Color.WHITE);
//		lblListOfFavourites.setBackground(Color.WHITE);
		lblListOfFavourites.setBounds(26, 30, 119, 16);
		add(lblListOfFavourites);

		JButton bookButton = new JButton(resourceBundle.getString("book"));
		bookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservation res = new Reservation(user,name_of_Restaurant);
				res.makeResInterface();
			}
		});
//		bookButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		bookButton.setBounds(178, 281, 100, 25);
		add(bookButton);

		JButton btnReview = new JButton(resourceBundle.getString("review"));
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reviews_Window rw = new Reviews_Window(current.getString("name"));
				rw.setVisible(true);
			}
		});
		btnReview.setBounds(290, 281, 100, 25);
		add(btnReview);

		JButton btnCheckMenu = new JButton(resourceBundle.getString("check.menu"));
		btnCheckMenu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCheckMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String menu = current.getString("menu"); // To create menu window
				Menu menu_window = new Menu(menu);
				menu_window.setVisible(true);
			}
		});
		btnCheckMenu.setBounds(400, 281, 120, 25);
		add(btnCheckMenu);

		final JLabel lblNmae = new JLabel(resourceBundle.getString("Name"));
		lblNmae.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblNmae.setForeground(Color.WHITE);
		lblNmae.setBounds(209, 30, 56, 16);
		add(lblNmae);

		final JLabel lblLocation = new JLabel(resourceBundle.getString("location") + ":");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblLocation.setForeground(Color.WHITE);
		lblLocation.setBounds(209, 65, 70, 16);
		add(lblLocation);

		final JLabel lblSpeciality = new JLabel(resourceBundle.getString("speciality") + ":");
		lblSpeciality.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblSpeciality.setForeground(Color.WHITE);
		lblSpeciality.setBounds(209, 100, 70, 16);
		add(lblSpeciality);

		name_field = new JTextField();
		name_field.setBounds(309, 28, 161, 22);
		add(name_field);
		name_field.setColumns(10);

		location_field = new JTextField();
		location_field.setBounds(309, 63, 161, 22);
		add(location_field);
		location_field.setColumns(10);

		JLabel lblNewLabel = new JLabel(resourceBundle.getString("type") + ":");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(209, 135, 56, 16);
		add(lblNewLabel);

		speciality_field = new JTextField();
		speciality_field.setBounds(309, 98, 103, 22);
		add(speciality_field);

		type_field = new JTextField();
		type_field.setBounds(309, 133, 103, 22);
		add(type_field);

		final JLabel lblScore = new JLabel(resourceBundle.getString("score") + ":");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblScore.setForeground(Color.WHITE);
		lblScore.setBounds(209, 170, 56, 16);
		add(lblScore);

		JLabel lblSchedule = new JLabel(resourceBundle.getString("schedule") + ":");
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblSchedule.setForeground(Color.WHITE);
		lblSchedule.setBounds(209, 205, 70, 16);
		add(lblSchedule);
		schedule_field = new JTextField();
		schedule_field.setBounds(309, 203, 161, 22);
		add(schedule_field);
		schedule_field.setColumns(10);

		score_field = new JTextField();
		score_field.setBounds(309, 168, 161, 22);
		add(score_field);
		score_field.setColumns(10);

		Panel panel = new Panel();
		panel.setBounds(26, 52, 147, 254);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));


		final DefaultListModel<String> listModel = new DefaultListModel<>();
		for (int i=0;i<favourites_list.length();i++) {
			JSONObject explrObject = favourites_list.getJSONObject(i);
			listModel.addElement(explrObject.getString("name"));
		}
		final JList list = new JList<>(listModel);
		list.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i=0;i<favourites_list.length();i++) {
					JSONObject explrObject = favourites_list.getJSONObject(i);
					if (explrObject.getString("name").equals(list.getSelectedValue())){
						current = explrObject;
						name_of_Restaurant = explrObject.getString("name");
						location_field.setText(explrObject.getString("location"));
						Float score = explrObject.getFloat("score");
						score_field.setText(score.toString());
						speciality_field.setText(explrObject.getString("speciality"));
						type_field.setText(explrObject.getString("type"));
						name_field.setText(explrObject.getString("name"));
						schedule_field.setText(explrObject.getString("schedule"));
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

	}
}
