package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;


/**
 * Request to the API for reviews management
 */
public class Reviews_API {

    private static Logger logger = Logger.getLogger(Reviews_API.class.getName());

    static public void main(String[] args) {
        Reviews_API reviewsAPI = new Reviews_API();
        JSONArray test = reviewsAPI.reviews_list("Txacoli");
        reviewsAPI.make_review("Txacoli", "Good food", 3.5);
    }

    /**
     * Gets list of reviews
     * @param restaurant Restaurant to get the reviews
     * @return The list of reviews in a JSONArray format
     */
    public JSONArray reviews_list(String restaurant){
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

    /**
     * Creates a review.
     * @param restaurant Selected restaurant for the review.
     * @param comments Comments related to the review.
     * @param score Score of the restaurant.
     * @return True or False deepending if the creation was succesful.
     */
    public Boolean make_review(String restaurant, String comments, Double score){
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
