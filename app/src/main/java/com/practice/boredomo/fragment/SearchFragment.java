package com.practice.boredomo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

//        // set listener to the slider
//        mParticipantSlider = view.findViewById(R.id.search_participant_slider);
//        mParticipantValue = view.findViewById(R.id.search_participant_value);
//        mParticipantSlider.addOnChangeListener(new Slider.OnChangeListener() {
//            @Override
//            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
//                String text = ((int)value)+"";
//                mParticipantValue.setText(text);
//            }
//        });


        return view;
    }
}
