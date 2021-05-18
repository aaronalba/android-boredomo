package com.practice.boredomo.model;

import java.util.List;


/**
 * Class for creating the Parameter that will be passed to the FetcherTask asynchronous task.
 * @author Aaron Alba
 */
public class FetcherTaskParameter {
    private List<String> types;
    private String participants;

    public FetcherTaskParameter(List<String> t, String p) {
        types = t;
        participants = p;
    }


    public List<String> getTypes() {
        return types;
    }

    public String getParticipants() {
        return participants;
    }
}
