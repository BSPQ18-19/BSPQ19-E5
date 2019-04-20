package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Restaurants {

    static public void main(String[] args) {
        Restaurants restaurants = new Restaurants();
        JSONArray list = restaurants.restaurant_list();
        /*
        for (int i=0;i<list.length();i++) {
            JSONObject explrObject = list.getJSONObject(i);
            System.out.println(explrObject);
        }*/
    }

    JSONArray restaurant_list(){
        InputStream in = null;
        String reply = "";
        JSONArray jsonArray;
        try {
            String url = "http://127.0.0.1:8000/restaurants";
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-json");
            con.setRequestMethod("GET");
            con.setDoOutput(true);

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
                jsonArray = new JSONArray(reply);

            } else {
                System.out.println("Bad conenction");
                return null;
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }
}
