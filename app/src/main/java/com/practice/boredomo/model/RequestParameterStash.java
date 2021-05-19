package com.practice.boredomo.model;


import java.util.ArrayList;

/**
 * Singleton class that stores the most recent Request Parameter that was sent to the API
 * @author Aaron Alba
 */
public class RequestParameterStash {
    private static RequestParameterStash sRequestParameterStash;
    private ArrayList<RequestParameter> mRequestParameters;

    // private constructor to avoid multiple instantiation
    private RequestParameterStash() {
        mRequestParameters = new ArrayList<>();
    }


    /**
     * Returns an instance of this RequestParameterStash and creates one if it is not yet created.
     * @return an instance of RequestParameterStash
     */
    public static RequestParameterStash getInstance() {
        if (sRequestParameterStash == null)
            sRequestParameterStash = new RequestParameterStash();

        return sRequestParameterStash;
    }


    /**
     * Returns the most recent request made
     * @return most recent RequestParameter, null of no request has been made yet
     */
    public RequestParameter getRecent() {
        if (!mRequestParameters.isEmpty())
            return mRequestParameters.get(0);
        return null;
    }


    /**
     * Updates the most recent request parameter made
     * @param params the new request parameter
     */
    public void update(RequestParameter params) {
        mRequestParameters.clear(); // remove other request parameters
        mRequestParameters.add(params); // add the new req params
    }
}
