package com.practice.boredomo.fragment;

import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;
import com.practice.boredomo.R;
import com.practice.boredomo.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragment for controlling the Searching of a Random Activity
 * @author Aaron Alba
 */
public class SearchFragment extends Fragment {
    private TextView mParticipantValue;
    private Slider mParticipantSlider;
    private ProgressBar mProgressBar;
    private Button mSearchBtn;
    private ChipGroup mTypeGroup;
    private ChipGroup mCostGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // get references to the widgets
        mParticipantSlider = view.findViewById(R.id.search_participant_slider);
        mParticipantValue = view.findViewById(R.id.search_participant_value);
        mProgressBar = view.findViewById(R.id.search_progressBar);
        mSearchBtn = view.findViewById(R.id.search_button);
        mTypeGroup = view.findViewById(R.id.search_type_chipGroup);
        mCostGroup = view.findViewById(R.id.search_cost_chipGroup);


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
    private void search(View v) {
        List<String> activityTypes = new ArrayList<>();

        // show the loading progress bar
        mProgressBar.setVisibility(View.VISIBLE);


        // get the activity types that was checked
        List<Integer> checkedTypes = mTypeGroup.getCheckedChipIds();
        for(int i : checkedTypes) {
            activityTypes.add(Utils.typeChipIdConverter(i));
        }

        // get the chosen cost
        int checkedCost = mCostGroup.getCheckedChipId();
        String cost = Utils.costChipIdConverter(checkedCost);


        // get the number of participants
        int participants = (int) mParticipantSlider.getValue();


        // send the http request to the api

        // TODO implement the async task that will fetch the data from the API
    }
}
