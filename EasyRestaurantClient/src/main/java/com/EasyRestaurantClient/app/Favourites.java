package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Favourites extends JPanel {
	private JTextField name_field;
	private JTextField location_field;
	private JTextField score_field;
	private JTextField speciality_field;
	private JTextField type_field;
	private JTextField schedule_field;
	private JButton btnReview;
	private String user;
    private  String name_of_Restaurant;
    private JSONObject current;
	private JSONArray favourites_list;
	private DefaultListModel<String> listModel;
	private JList list;
	private Restaurants restaurants = new Restaurants();



    /**
	 * Create the panel.
	 */
	public Favourites(String user) {
//		setBackground(Color.DARK_GRAY);
		this.user = user;
		initialize();
	}

	public void update(){
		JSONObject filters = new JSONObject();
		filters.put("user", user);
		favourites_list = restaurants.restaurant_list(filters);
		listModel.clear();
		for (int i=0;i<favourites_list.length();i++) {
			JSONObject explrObject = favourites_list.getJSONObject(i);
			listModel.addElement(explrObject.getString("name"));
		}
	}
	
	public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Resource");

        setLayout(null);

		JSONObject filters = new JSONObject();
		filters.put("user", user);
		favourites_list = restaurants.restaurant_list(filters);

		JLabel lblListOfFavourites = new JLabel(resourceBundle.getString("list.of.favourites"));
		lblListOfFavourites.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblListOfFavourites.setForeground(Color.WHITE);
//		lblListOfFavourites.setBackground(Color.WHITE);
		lblListOfFavourites.setBounds(26, 30, 119, 16);
		add(lblListOfFavourites);

		JButton btnFavourite = new JButton(resourceBundle.getString("delete"));
		btnFavourite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				restaurants.add_favourite(current.getInt("id"), user);
				update();
			}
		});
		btnFavourite.setBounds(420, 0, 90, 23);
		add(btnFavourite);
		
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
		
		btnReview = new JButton(resourceBundle.getString("review"));
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton pressedButton = (JButton)e.getSource();
				if(pressedButton == btnReview) {
					Reviews_Window rw = new Reviews_Window(current.getString("name"));
					rw.setVisible(true);
				}	
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
		

		listModel = new DefaultListModel<>();
		for (int i=0;i<favourites_list.length();i++) {
			JSONObject explrObject = favourites_list.getJSONObject(i);
			listModel.addElement(explrObject.getString("name"));
		}
		list = new JList<>(listModel);
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
