package com.EasyRestaurantClient.app;


import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * The Reservation class implements the part in the application necessary
 * to use for making a reservation.
 *
 *
 */
public class Reservation {
    final static String DATE_FORMAT = "yyyy-MM-dd";
    private String user; /**< name of user  */
    private String name_Of_Restaurant;
    private JPanel reservationPanel;
    private JButton cancelButton;
    private JButton OKButton;
    private JTextField NameInput;
    private JTextArea dayTextArea;
    private JComboBox YearInput;
    private JComboBox DayInput;
    private JComboBox MonthInput;
    private JTextField RestaurantInput;
    private JTextField CommentInput;
    private JComboBox HourInput;
    private JComboBox MinuteInput;
    private JComboBox GuestsInput;
    private JFrame frame;
    private static Logger logger = Logger.getLogger(Reservation.class.getName());





    public Reservation(String user, String name_Of_Restaurant) {

        this.user = user; /**< updating the User variable so we know who is our current user*/
        this. name_Of_Restaurant = name_Of_Restaurant; /**< restaurant to be reserved */
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if date has a correct form
                String date = YearInput.getSelectedItem().toString()+"-"+MonthInput.getSelectedItem().toString()+"-"+DayInput.getSelectedItem().toString();
                String daytime = date+" "+HourInput.getSelectedItem().toString()+":"+MinuteInput.getSelectedItem().toString();
                String str_number_clients = (GuestsInput.getSelectedItem().toString());
                int number_clients = Integer.parseInt(str_number_clients);
                final boolean response = make_reservation(NameInput.getText(), RestaurantInput.getText(),daytime, number_clients, CommentInput.getText());

                try {
                    frame.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                logger.info("Reservation is  " + response);
                reservationPanel.disable();
                if (!response)negRespondMessage(); // depending on if the reservation is valid or not, the apprpriate message appears.
                else posRespondMessage();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // by pressing this button you exit from the program.
                try {
                    frame.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });



    }

     public void main(String[] args) {


        makeResInterface();


    }


    /**
     * Function to request the server to create a reservation
     *
     * @param username       Username of the user
     * @param restaurant     Name of the restaurant where the reservation should be done
     * @param date           Date in a string format YYYY-MM-DD HH:MM
     * @param number_clients Number of clients expected
     * @param comments       Comments in case there were any
     * @return True if it is correct
     */
    static Boolean make_reservation(String username, String restaurant, String date, Integer number_clients, String comments) {
        InputStream in = null;
        OutputStream out = null;
        Boolean response = true;
        String reply = "";


        try {
            String url = "http://127.0.0.1:8000/reservations/";
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-filters");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("user", username);
            json.put("restaurant", restaurant);
            json.put("date", date);
            json.put("number_clients", number_clients);
            json.put("comments", comments);

            out = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(json.toString());
            writer.flush();
            writer.close();
            out.close();

            int respCode = con.getResponseCode();
            logger.info("response code " + respCode);


            if (respCode == HttpURLConnection.HTTP_OK) {
                String line;
                in = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((line = reader.readLine()) != null) {
                    reply += line;
                }

                reader.close();
                in.close();


                logger.info("\nresponse content " + reply);

                response = !reply.contains("Wrong");
            } else {
                response = false;
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return response;
    }



    //! A function about the graphic interface of the reservation.
    /*!
    Putting on the frame the designed form panel and adding the titles.

    */

    public void makeResInterface(){


        logger.info("Reservation Locale is " +Locale.getDefault().toString());

        JFrame frame = new JFrame("My Easy Restaurant");
        this.frame = frame;
        frame.setContentPane(this.reservationPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        RestaurantInput.setText(this.name_Of_Restaurant);
        NameInput.setText(this.user);
        frame.setVisible(true);


    }

    //! Negative respond appears when the reservation can not proceed.

    public static void negRespondMessage() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Resource");

        final JFrame myFrame2 = new JFrame();

        myFrame2.setVisible(true);
        myFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame2.setBounds(100, 100, 500, 400);
        myFrame2.setTitle("The Easy Restaurant");
        myFrame2.getContentPane().setLayout(null);

        JLabel hFail = new JLabel(resourceBundle.getString("RequestFail"));
        hFail.setBounds(100, 100, 1000, 14);
        myFrame2.getContentPane().add(hFail);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    myFrame2.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnOK.setBounds(200, 300, 74, 23);
        myFrame2.getContentPane().add(btnOK);

    }

    //! Positive respond appears when the reservation is made.
    public static void posRespondMessage() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Resource");

        final JFrame myFrame2 = new JFrame();

        myFrame2.setVisible(true);
        myFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame2.setBounds(100, 100, 500, 400);
        myFrame2.setTitle("My Easy Restaurant");
        myFrame2.getContentPane().setLayout(null);

        JLabel hSucc = new JLabel(resourceBundle.getString("SuccessReserv"));
        hSucc.setBounds(100, 100, 1000, 14);
        myFrame2.getContentPane().add(hSucc);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    myFrame2.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnOK.setBounds(200, 300, 74, 23);
        myFrame2.getContentPane().add(btnOK);

    }



}