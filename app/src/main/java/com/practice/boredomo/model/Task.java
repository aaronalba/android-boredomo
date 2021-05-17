package com.practice.boredomo.model;


import androidx.annotation.NonNull;

/**
 * Class that models the Task that will be returned by the Bored API
 * @author Aaron Alba
 */
public class Task {
    private String mTitle;
    private String mURL;
    private int mKey;

    public Task (String t, String u, int k) {
        mTitle = t;
        mURL = u;
        mKey = k;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getURL() {
        return mURL;
    }

    public int getKey() {
        return mKey;
    }

    @NonNull
    @Override
    public String toString() {
        return "{title: " + mTitle + ", link: " + mURL + " , key: " + mKey + " }";
    }
}
