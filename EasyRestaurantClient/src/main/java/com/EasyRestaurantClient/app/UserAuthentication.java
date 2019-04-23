package com.EasyRestaurantClient.app;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.json.JSONObject;

import javax.jws.soap.SOAPBinding;

/**
 * Hello world!
 *
 */
public class UserAuthentication
{
    private static Logger logger = Logger.getLogger(UserAuthentication.class.getName());

    /**
     * Tries to authenticate the user on the server
     * @param username Username of the client
     * @param password Password of the client
     * @return Correct if there hasn't been any problems
     */
    String login(final String username, final String password) {
        InputStream in = null;
        OutputStream out = null;
        String reply = "";

        String resp = "Incorrect";

        try {
            String url = "http://127.0.0.1:8000/login/";
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-json");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);

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


                if (!reply.toString().contains("Invalid")) {
                    resp = "Correct";
                } else {
                    resp = "Incorrect";
                }

            } else {
                resp = "Bad connection";
            }
            logger.info(resp);
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * Tries to register a new client
     * @param username Username of the client
     * @param password Password of the client
     * @param name Name of the client
     * @param email Email of the client
     * @return User created if everything went fine
     */
    String register(String username, String password, String name, String email){
        InputStream in = null;
        OutputStream out = null;
        String reply = "";

        String resp = "Incorrect";

        try {
            String url = "http://127.0.0.1:8000/register/";
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestProperty("Content-Type", "application-json");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);
            json.put("name", name);
            json.put("email", email);

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


                if (!reply.toString().contains("exists")) {
                    resp = "User created";
                } else {
                    resp = "User already exists";
                }
            } else {
                resp = "Bad connection";
            }
            logger.info(resp);
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

}
