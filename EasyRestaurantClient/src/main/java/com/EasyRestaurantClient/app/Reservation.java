package com.EasyRestaurantClient.app;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Reservation {

    static public void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    Boolean make_reservation(String username, String restaurant, String date, Integer number_clients, String comments){
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

                response = !reply.contains("wrong");
            } else {
                response =  false;
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}