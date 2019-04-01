package com.EasyRestaurantClient.app;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class UserAuthentication
{

    static public void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }


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


                if (!reply.toString().contains("Invalid")) {
                    System.out.println("Correct");
                    resp = "Correct";
                } else {
                    System.out.println("Incorrect");
                    resp = "Incorrect";
                }
            } else {
                System.out.println("Bad conenction");
                resp = "Bad connection";
            }
                con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return resp;
    }

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


                if (!reply.toString().contains("exists")) {
                    System.out.println("User created");
                    resp = "User created";
                } else {
                    System.out.println("User already exists");
                    resp = "User already exists";
                }
            } else {
                System.out.println("Bad conenction");
                resp = "Bad connection";
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

}
