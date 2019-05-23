package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Request to the API for restaurants management
 */
public class Restaurants_API {

    private static Logger logger = Logger.getLogger(Restaurants_API.class.getName());

    /**
     * Get list of restaurants with provided filters.
     * @param filters JSONObject containing the filters selected.
     * @return JSONArray with restaurants list.
     */
    public JSONArray restaurant_list(JSONObject filters){
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
     * Adds as a favourite the selected restaurant.
     * @param id Id of the restaurant.
     * @param user User who wants to add the restaurant as favourite.
     * @return rue or False deepending if the assignment was succesful.
     */
    public Boolean add_favourite(Integer id, String user){
        InputStream in = null;
        OutputStream out = null;
        Boolean response = true;
        String reply = "";

        try {
            String url = "http://127.0.0.1:8000/favourite/";
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-filters");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("user", user);

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
