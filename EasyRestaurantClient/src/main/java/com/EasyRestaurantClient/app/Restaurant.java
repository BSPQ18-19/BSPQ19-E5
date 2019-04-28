package IU;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import org.json.simple.JSONObject;

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
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(96, 62, 64, 14);
		add(lblName);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocation.setBounds(96, 112, 64, 14);
		add(lblLocation);
		
		txtNamefield = new JTextField();
		txtNamefield.setBounds(191, 60, 225, 23);
		add(txtNamefield);
		txtNamefield.setColumns(10);
		
		txtLocationfield = new JTextField();
		txtLocationfield.setBounds(191, 110, 225, 23);
		add(txtLocationfield);
		txtLocationfield.setColumns(10);
		
		JLabel lblSpecialty = new JLabel("Specialty");
		lblSpecialty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecialty.setBounds(34, 170, 64, 27);
		add(lblSpecialty);
		
		JCheckBox chckbxFastFood = new JCheckBox("Fast Food");
		chckbxFastFood.setBounds(34, 204, 97, 23);
		add(chckbxFastFood);
		
		JCheckBox chckbxCommon = new JCheckBox("Common");
		chckbxCommon.setBounds(150, 204, 97, 23);
		add(chckbxCommon);
		
		JCheckBox chckbxDessert = new JCheckBox("Dessert");
		chckbxDessert.setBounds(34, 241, 97, 23);
		add(chckbxDessert);
		
		JCheckBox chckbxInnovative = new JCheckBox("Innovative");
		chckbxInnovative.setBounds(150, 241, 97, 23);
		add(chckbxInnovative);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(297, 178, 68, 19);
		add(lblType);
		
		JCheckBox chckbxAsiatic = new JCheckBox("Asiatic");
		chckbxAsiatic.setBounds(297, 204, 97, 23);
		add(chckbxAsiatic);
		
		JCheckBox chckbxItalian = new JCheckBox("Italian");
		chckbxItalian.setBounds(297, 241, 97, 23);
		add(chckbxItalian);
		
		JCheckBox chckbxMexican = new JCheckBox("Mexican");
		chckbxMexican.setBounds(405, 204, 97, 23);
		add(chckbxMexican);
		
		JCheckBox chckbxLocal = new JCheckBox("Local");
		chckbxLocal.setBounds(405, 241, 97, 23);
		add(chckbxLocal);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(312, 376, 53, 14);
		add(lblDate);
		
		txtDatefield = new JTextField();
		txtDatefield.setText("mm/dd/yyyy");
		txtDatefield.setBounds(362, 373, 126, 17);
		add(txtDatefield);
		txtDatefield.setColumns(10);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblScore.setBounds(52, 376, 46, 14);
		add(lblScore);
		
		txtScorefield = new JTextField();
		txtScorefield.setBounds(126, 373, 86, 20);
		add(txtScorefield);
		txtScorefield.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				makeReservation();
			}
		});
		btnSearch.setBounds(223, 442, 89, 23);
		add(btnSearch);
		

	}
	public void makeReservation() {
	
		      //Make the reservation JSON and send it to the server.
		
	}
}
