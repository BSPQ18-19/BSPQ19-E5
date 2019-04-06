package com.EasyRestaurantClient.app;


import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class Reservation {

    static public void main(String[] args) {
        System.out.println("Hello World!");

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                request_reservation();
            }
        });
    }


    /**
     * Function to request the server to create a reservation
     *
     * @param username       Username of the user
     * @param restaurant     Name of the restaurant where the reservation should be done
     * @param date           Date in a string format YYYY-MM-DD HH:MM
     * @param number_clients
     * @param comments       Comments in case there were any
     * @return True if it is correct
     */


    static public void request_reservation() {

        final JFrame myFrame = new JFrame();
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setBounds(100, 100, 500, 400);
        myFrame.setTitle("The Easy Restaurant");
        myFrame.getContentPane().setLayout(null);

        // Header
        final JLabel hReqRes = new JLabel("Make a Reservation");
        hReqRes.setBounds(144, 21, 132, 14);
        myFrame.getContentPane().add(hReqRes);

        //Creating Label for the fields
        JLabel hUsername = new JLabel("Username  :");
        hUsername.setBounds(100, 51, 89, 14);
        myFrame.getContentPane().add(hUsername);

        JLabel hRestaurant = new JLabel("Restaurant  :");
        hRestaurant.setBounds(100, 76, 89, 14);
        myFrame.getContentPane().add(hRestaurant);

        JLabel hDate = new JLabel("Date  :");
        hDate.setBounds(100, 100, 89, 14);
        myFrame.getContentPane().add(hDate);

        JLabel hNumber_clients = new JLabel("Number of guests  :");
        hNumber_clients.setBounds(100, 126, 120, 14);
        myFrame.getContentPane().add(hNumber_clients);

        JLabel hComments = new JLabel("Comments  :");
        hComments.setBounds(100, 154, 89, 14);
        myFrame.getContentPane().add(hComments);

        //Creating TextFields

        final TextField txtUsername = new TextField();
        txtUsername.setBounds(207, 51, 200, 20);
        myFrame.getContentPane().add(txtUsername);

        final TextField txtRestaurant = new TextField();
        txtRestaurant.setBounds(207, 76, 200, 20);
        myFrame.getContentPane().add(txtRestaurant);

        final TextField txtDate = new TextField();
        txtDate.setBounds(207, 101, 200, 20);
        myFrame.getContentPane().add(txtDate);


        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField txtNumber_clients = new JFormattedTextField(formatter);

        //final TextField  = new TextField();
        txtNumber_clients.setBounds(230, 127, 30, 20);
        myFrame.getContentPane().add(txtNumber_clients);

        final TextField txtComments = new TextField();
        txtComments.setBounds(207, 153, 200, 60);
        myFrame.getContentPane().add(txtComments);


        // Variables
        final String username = txtUsername.getText();
        final String restaurant = txtRestaurant.getText();
        final String date = txtDate.getText();
        Object obj_number_clients = 0;
        obj_number_clients = txtNumber_clients.getValue();
        final Integer number_clients = (Integer) obj_number_clients;
        final String comments = txtComments.getText();


        // Button OK
        JButton btnOK = new JButton("Submit");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final boolean response = make_reservation(username, restaurant, date, number_clients, comments);
                try {
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("Reservation is  " + response);
                myFrame.dispose();
                if (!response)negRespondMessage();
                else posRespondMessage();
            }

        });
        btnOK.setBounds(170, 300, 74, 23);
        myFrame.getContentPane().add(btnOK);

        // Cancel Button

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                try {
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnCancel.setBounds(280, 300, 74, 23);
        myFrame.getContentPane().add(btnCancel);


    }

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
        myFrame2.setTitle("The Easy Restaurant");
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