package com.practice.boredomo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.practice.boredomo.R;

/**
 * Fragment for showing the result from the Search Random Activity
 * @author Aaron Alba
 */
public class ResultFragment extends Fragment {
    private TextView mResultHeader;
    private TextView mResultLearnMore;
    private TextView mResultLink;
    private Button mMoreBtn;
    private String mHeader;
    private String mLink;

    private static final String ARGS_ACTIVITY = "activity";
    private static final String ARGS_LINK = "link";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retrieve the fragment arguments
        Bundle args = getArguments();
        mHeader = (String) args.getSerializable(ARGS_ACTIVITY);
        mLink = (String) args.getSerializable(ARGS_LINK);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        mResultHeader = view.findViewById(R.id.result_activity);
        mResultLearnMore = view.findViewById(R.id.result_learn_more);
        mResultLink = view.findViewById(R.id.result_link);
        mMoreBtn = view.findViewById(R.id.result_more_btn);

        // set the data to be shown
        mResultHeader.setText(mHeader);

        if (mLink.length() == 0) {
            mResultLearnMore.setVisibility(View.GONE);
            mResultLink.setVisibility(View.GONE);
        } else {
            mResultLink.setText(mLink);
        }

        // set listener for the retry button
        mMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        // set listener for the link
        mResultLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will fire an implicit intent to start the browser and go to the link
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mLink));

                startActivity(intent);
            }
        });

        return view;
    }


    /**
     * Creates a new ResultFragment with the given the argument.
     * @param task contains the data that will be shown by this fragment.
     * @return an instance of ResultFragment.
     */

    /**
     * Creates a new ResultFragment with the given the argument.
     * @param activity the title of the activity to be shown
     * @param link the link for further reading, optional
     * @return
     */
    public static ResultFragment newInstance(String activity, String link) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_ACTIVITY, activity);
        args.putSerializable(ARGS_LINK, link);

        fragment.setArguments(args);
        return fragment;
    }
}
