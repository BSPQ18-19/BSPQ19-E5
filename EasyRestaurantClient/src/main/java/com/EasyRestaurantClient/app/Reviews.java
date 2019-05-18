package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class Reviews {

    private static Logger logger = Logger.getLogger(Reviews.class.getName());

    static public void main(String[] args) {
        Reviews reviews = new Reviews();
        JSONArray test = reviews.reviews_list("Txacoli");
        reviews.make_review("Txacoli", "Good food", 3.5);
    }

    JSONArray reviews_list(String restaurant){
        InputStream in = null;
        String url;
        String reply = "";
        JSONArray jsonArray;
        try {
            url = "http://127.0.0.1:8000/reviews?restaurant="+restaurant.replaceAll(" ", "+");
            logger.info(url);
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-filters");
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

    Boolean make_review(String restaurant, String comments, Double score){
        InputStream in = null;
        OutputStream out = null;
        Boolean response = true;
        String reply = "";

        try {
            String url = "http://127.0.0.1:8000/reviews/";
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-filters");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("restaurant", restaurant);
            json.put("score", score);
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

                logger.info("response content " + reply);

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
}
