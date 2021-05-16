package com.practice.boredomo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.practice.boredomo.R;

/**
 * Template class for activities that will host a single fragment on its screen.
 * @author Aaron Alba
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment==null) {
            // add the fragment to be hosted to the fragment manager
            fragment = createFragment();
            manager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }


    /**
     * Defines the fragment to be hosted by this Activity.
     * @return Fragment
     */
    protected abstract Fragment createFragment();
}
