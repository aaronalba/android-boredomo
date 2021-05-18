package com.practice.boredomo.fragment;

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
import com.practice.boredomo.model.Task;

/**
 * Fragment for showing the result from the Search Random Activity
 * @author Aaron Alba
 */
public class ResultFragment extends Fragment {
    private TextView mResultHeader;
    private TextView mResultLearnMore;
    private TextView mResultLink;
    private Button mRetryBtn;
    private Task mTask;

    private static final String ARGS_ACTIVITY = "activity";
    private static final String ARGS_LINK = "link";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        mResultHeader = view.findViewById(R.id.result_activity);
        mResultLearnMore = view.findViewById(R.id.result_learn_more);
        mResultLink = view.findViewById(R.id.result_link);
        mRetryBtn = view.findViewById(R.id.result_retry_btn);

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
