package com.practice.boredomo.activity;


import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.practice.boredomo.fragment.ResultFragment;

/**
 * Activity that will host the ResultFragment
 * @author Aaron Alba
 */
public class ResultActivity extends SingleFragmentActivity {
    public static final String EXTRA_ACTIVITY = "com.practice.boredomo.activity.activity";
    public static final String EXTRA_LINK = "com.practice.boredomo.activity.link";

    @Override
    protected Fragment createFragment() {
        return new ResultFragment();
    }


    /**
     * Creates an Intent that can be used to start this activity.
     * @param activity the activity title that the ResultFragment will show.
     * @param link the link that the ResultFragment will show.
     * @return Intent object.
     */
    public static Intent newIntent(Context packageContext, String activity, String link) {
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_ACTIVITY, activity);
        intent.putExtra(EXTRA_LINK, link);

        return intent;
    }
}
