package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Restaurants {

    private static Logger logger = Logger.getLogger(Restaurants.class.getName());
    static public void main(String[] args) {
        Restaurants restaurants = new Restaurants();
//        ArrayList<String> filters = new ArrayList<>();
        JSONObject filters = new JSONObject();
        filters.put("type", "Grill");
//        filters.put("user", "carlos");
        JSONArray list = restaurants.restaurant_list(filters);
        /*
        for (int i=0;i<list.length();i++) {
            JSONObject explrObject = list.getJSONObject(i);
            System.out.println(explrObject);
        }*/
    }

    JSONArray restaurant_list(JSONObject filters){
        InputStream in = null;
        String url;
        String reply = "";
        JSONArray jsonArray;
        try {
            if (filters.keySet().size()==0){
                url = "http://127.0.0.1:8000/restaurants";
            } else {
                String filter_url = "?";
                for (String key: filters.keySet()){
                    filter_url += key + "=" + filters.getString(key) + ";";
                }
                url = "http://127.0.0.1:8000/restaurants"+filter_url;
                logger.info(url);
            }
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-json");
            con.setRequestMethod("GET");
            con.setDoOutput(true);

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

                logger.info("response content " + reply);
                jsonArray = new JSONArray(reply);

            } else {
                logger.info("Bad conenction");
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
