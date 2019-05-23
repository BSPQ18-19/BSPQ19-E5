package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Request to the API for reservations management
 */
public class Reservations_API {

    private static Logger logger = Logger.getLogger(Reservations_API.class.getName());

    /**
     * Get list of reservations
     * @param filters Filter reservations depending on the user or restaurant
     * @return JSONArray of reservations
     */
    public JSONArray reservation_list(JSONObject filters){
        InputStream in = null;
        String url;
        String reply = "";
        JSONArray jsonArray;
        try {
            String filter_url = "?";
            for (String key: filters.keySet()){
                filter_url += key + "=" + filters.getString(key) + ";";
            }
            url = "http://127.0.0.1:8000/reservations"+filter_url;
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
     * Updates a reservation
     * @param date New date
     * @param number_clients New number of clients
     * @param id Id of the reservation
     * @return True or False deepending if the modification was succesful.
     */
    public Boolean update_reservation(String date, Integer number_clients, String id){
        InputStream in = null;
        OutputStream out = null;
        String url;
        String reply = "";
        JSONArray jsonArray;
        boolean result = false;
        try {
            url = "http://127.0.0.1:8000/update/reservations/";
            logger.info(url);
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-filters");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("date", date);
            json.put("number_clients", number_clients);

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
                if (reply.contains("Updated")){
                    result =  true;
                }

            } else {
                logger.info("Bad conenction");
                result =  false;
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            result =  false;
        }
        return result;
    }

    /**
     * Removes a reservation
     * @param id Id of the reservation
     * @return True or False deepending if the removal was succesful.
     */
    public Boolean remove_reservation(String id){
        InputStream in = null;
        String url;
        String reply = "";
        JSONArray jsonArray;
        Boolean result = false;
        try {
            url = "http://127.0.0.1:8000/remove/reservations?id="+id;
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
                result =  true;

            } else {
                logger.info("Bad conenction");
                result =  false;
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
