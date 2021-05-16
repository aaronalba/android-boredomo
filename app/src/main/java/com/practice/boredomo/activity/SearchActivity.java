package com.practice.boredomo.activity;

import androidx.fragment.app.Fragment;

import com.practice.boredomo.fragment.SearchFragment;

/**
 * Activity that will host the search fragment
 * @author Aaron Alba
 */
public class SearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SearchFragment();
    }
}
