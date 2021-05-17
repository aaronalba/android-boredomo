package com.practice.boredomo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.Slider;
import com.practice.boredomo.R;


/**
 * Fragment for controlling the Searching of a Random Activity
 * @author Aaron Alba
 */
public class SearchFragment extends Fragment {
    private TextView mParticipantValue;
    private Slider mParticipantSlider;
    private ProgressBar mProgressBar;
    private Button mSearchBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // get references to the widgets
        mParticipantSlider = view.findViewById(R.id.search_participant_slider);
        mParticipantValue = view.findViewById(R.id.search_participant_value);
        mProgressBar = view.findViewById(R.id.search_progressBar);
        mSearchBtn = view.findViewById(R.id.search_button);


        // set listener to the slider
        mParticipantSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                String text = ((int)value)+"";
                mParticipantValue.setText(text);
            }
        });


        // set onClick to the search button
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });

        return view;
    }


    /**
     * Searches the API with the given paramters
     * @param v The Button that was clicked
     */
    public void search(View v) {
        // show the loading progress bar
        mProgressBar.setVisibility(View.VISIBLE);

        // compile the data to be sent to the api

        // send the http request to the api

    }
}
