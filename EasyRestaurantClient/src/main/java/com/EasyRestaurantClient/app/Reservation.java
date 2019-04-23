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


import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
public class Reservation {
    final static String DATE_FORMAT = "yyyy-MM-dd";

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
    private JTextPane numofguests;


    private static int language;
    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public Reservation() {




        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                //check if date has a correct form


                String date = YearInput.getSelectedItem().toString()+"-"+MonthInput.getSelectedItem().toString()+"-"+DayInput.getSelectedItem().toString();
                String daytime = date+" "+HourInput.getSelectedItem().toString()+":"+MinuteInput.getSelectedItem().toString();
                System.out.println(daytime);
                String str_number_clients = (GuestsInput.getSelectedItem().toString());
                int number_clients = Integer.parseInt(str_number_clients);
                final boolean response = make_reservation(NameInput.getText(), RestaurantInput.getText(),daytime, number_clients, CommentInput.getText());

                try {
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("Reservation is  " + response);
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
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });



    }

    static public void main(String[] args) {

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
        Boolean response = false;
        String reply = "";


        try {
            String url = "http://127.0.0.1:8000/reservations/";
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-json");
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
            System.out.println("response code " + respCode);


            if (respCode == HttpURLConnection.HTTP_OK) {
                String line;
                in = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((line = reader.readLine()) != null) {
                    reply += line;
                }

                reader.close();
                in.close();


                System.out.println("\nresponse content " + reply);

                response = !reply.contains("Wrong");
            } else {
                response = false;
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void makeResInterface(){
        Locale englishLocale = new Locale("en_US");
        Locale greekLocale = new Locale("el_GR");
        if (language==0) Locale.setDefault(englishLocale);
        else Locale.setDefault(greekLocale);

        JFrame frame = new JFrame("My Easy Restaurant");
        frame.setContentPane(new Reservation().reservationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
    public static void negRespondMessage() {
        JFrame myFrame2 = new JFrame();

        myFrame2.setVisible(true);
        myFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame2.setBounds(100, 100, 500, 400);
        myFrame2.setTitle("The Easy Restaurant");
        myFrame2.getContentPane().setLayout(null);

        JLabel hFail = new JLabel("Sorry but the reservation request was unable to proceed.");
        hFail.setBounds(100, 100, 1000, 14);
        myFrame2.getContentPane().add(hFail);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                try {
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnOK.setBounds(200, 300, 74, 23);
        myFrame2.getContentPane().add(btnOK);

    }


    public static void posRespondMessage() {
        JFrame myFrame2 = new JFrame();

        myFrame2.setVisible(true);
        myFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame2.setBounds(100, 100, 500, 400);
        myFrame2.setTitle("My Easy Restaurant");
        myFrame2.getContentPane().setLayout(null);

        JLabel hFail = new JLabel("Your reservation has been made. Thank you !");
        hFail.setBounds(100, 100, 1000, 14);
        myFrame2.getContentPane().add(hFail);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                try {
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnOK.setBounds(200, 300, 74, 23);
        myFrame2.getContentPane().add(btnOK);

    }



}