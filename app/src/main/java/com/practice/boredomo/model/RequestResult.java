package com.practice.boredomo.model;

/**
 * Class that will contain the result of the request from the API.
 * @author Aaron Alba
 */
public class RequestResult {
    // the result status
    private String mStatus;

    // the received json
    private String mJSON;


    public static final String ERROR = "error";
    public static final String NO_ACTIVITY_FOUND = "no activity";
    public static final String SUCCESS =  "success";
    public static final String EMPTY = "empty";


    public RequestResult(String s) {
        this.mStatus = s;
    }

    public RequestResult(String s, String j) {
        this.mStatus = s;
        this.mJSON = j;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getJSON() {
        return mJSON;
    }
}
