package com.practice.boredomo.model;

import java.util.List;


/**
 * Class for creating the Parameter that will be passed to the FetcherTask asynchronous task.
 * @author Aaron Alba
 */
public class FetcherTaskParameter {
    private List<String> types;
    private String cost;
    private int participants;

    public FetcherTaskParameter(List<String> t, String c, int p) {
        types = t;
        cost = c;
        participants = p;
    }


    public List<String> getTypes() {
        return types;
    }

    public String getCost() {
        return cost;
    }

    public int getParticipants() {
        return participants;
    }
}
