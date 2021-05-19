package com.practice.boredomo.network;

import android.net.Uri;

import com.practice.boredomo.model.RequestParameter;
import com.practice.boredomo.model.RequestResult;

import org.json.JSONException;
import org.json.JSONObject;

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
public class RequestSender {
    private static final String BORED_API_URL = "https://www.boredapi.com/api/activity/";
    private static final String KEY_PARAM = "key";
    private static final String TYPE_PARAM = "type";
    private static final String PARTICIPANTS_PARAM = "participants";
    private static final String MIN_PARTICIPANTS_PARAM = "minparticipants";


    /**
     * Sends a request to the Bored API server.
     * @return the JSON response from the server
     */
    public static RequestResult getTask(RequestParameter params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        // create the uri which will be used to create the url
        Uri.Builder uriBuilder = Uri.parse(BORED_API_URL).buildUpon();
        for(String t: params.getTypes()) {
            uriBuilder.appendQueryParameter(TYPE_PARAM, t);
        }

        switch(params.getParticipants()) {
            case "one":
                uriBuilder.appendQueryParameter(PARTICIPANTS_PARAM, "1");
                break;
            case "two":
                uriBuilder.appendQueryParameter(PARTICIPANTS_PARAM, "2");
                break;
            case "many":
                uriBuilder.appendQueryParameter(MIN_PARTICIPANTS_PARAM, "3");
                break;
        }

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
                return new RequestResult(RequestResult.EMPTY);
            }

            String json = buffer.toString();
            if (new JSONObject(json).has("error")) {
                return new RequestResult(RequestResult.NO_ACTIVITY_FOUND);
            }

            // return the data
            return new RequestResult(RequestResult.SUCCESS, json);

        } catch (IOException | JSONException e) {
            return new RequestResult(RequestResult.ERROR);

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
    }
}
