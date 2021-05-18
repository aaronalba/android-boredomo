package com.practice.boredomo.utils;

import android.net.Uri;

import com.practice.boredomo.model.FetcherTaskParameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class that implements the fetching of data from the BoredAPI
 * @author Aaron Alba
 */
public class NetworkUtils {
    private static final String BORED_API_URL = "https://www.boredapi.com/api/activity/";
    private static final String KEY_PARAM = "key";
    private static final String TYPE_PARAM = "type";
    private static final String PARTICIPANTS_PARAM = "participants";
    private static final String PRICE_PARAM = "price";
    private static final String MINPRICE_PARAM = "minprice";
    private static final String MAXPRICE_PARAM = "maxprice";


    /**
     * Sends a request to the Bored API server.
     * @return the JSON response from the server
     */
    public static String getTask(FetcherTaskParameter params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        // create the uri which will be used to create the url
        Uri.Builder uriBuilder = Uri.parse(BORED_API_URL).buildUpon();
        for(String t: params.getTypes()) {
            uriBuilder.appendQueryParameter(TYPE_PARAM, t);
        }

        uriBuilder.appendQueryParameter(PARTICIPANTS_PARAM, params.getParticipants()+"");

        Uri uri = uriBuilder.build();


        try {
            // create the connection using the url
            URL url = new URL(uri.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // check for response in the input stream
            InputStream in = connection.getInputStream();
            if (in == null) {
                return null;
            }

            // read data from the input stream
            StringBuilder buffer = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            // input stream was empty
            if (buffer.length() == 0) {
                return null;
            }

            // return the data
            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close the http connection
            if (connection != null) {
                connection.disconnect();
            }

            // reclaim the buffered reader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
