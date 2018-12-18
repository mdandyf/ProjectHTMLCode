package com.mitrais;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Code {
    static String query = "https://jsonmock.hackerrank.com/api/movies/search/";
    static String title = "?Title=";
    static String page = "page =";
    static int pageInt = 1;

    public static void main(String[] args) {

        String urlString = query;

        String output = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            output = result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(output != null) {
            List<String> listTitle = new ArrayList<String>();
            try {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(output);
                JSONArray arrayData = (JSONArray) jsonObject.get("data");
                if(arrayData != null) {
                    for(int i = 0; i < arrayData.size(); i++) {
                        JSONObject data = (JSONObject) arrayData.get(i);
                        if(!listTitle.contains(data.get("Title"))) {
                            listTitle.add((String) data.get("Title"));
                        }
                    }
                }

                listTitle.sort(Comparator.comparing(String::new));

                System.out.println(listTitle);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }



    }
}
