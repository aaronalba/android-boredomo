package com.practice.boredomo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

import com.practice.boredomo.R;
import com.practice.boredomo.activity.ResultActivity;
import com.practice.boredomo.model.RequestParameter;
import com.practice.boredomo.model.RequestParameterStash;
import com.practice.boredomo.model.RequestResult;
import com.practice.boredomo.model.Task;
import com.practice.boredomo.network.RequestSender;
import com.practice.boredomo.utils.Utils;

/**
 * Fragment for showing the result from the Search Random Activity
 * @author Aaron Alba
 */
public class ResultFragment extends Fragment {
    private TextView mResultHeader;
    private TextView mResultLearnMore;
    private TextView mResultLink;
    private Button mMoreBtn;
    private ProgressBar mResultProgressBar;
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

        // get references to the widgets
        mResultHeader = view.findViewById(R.id.result_activity);
        mResultLearnMore = view.findViewById(R.id.result_learn_more);
        mResultLink = view.findViewById(R.id.result_link);
        mMoreBtn = view.findViewById(R.id.result_more_btn);
        mResultProgressBar = view.findViewById(R.id.result_progressBar);

        updateUI();

        // set listener for the retry button
        mMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // resend the request that was sent by the search fragment to the API using the same parameters
                mResultProgressBar.setVisibility(View.VISIBLE);

                RequestParameter params = RequestParameterStash.getInstance().getRecent();
                new FetcherTask().execute(params);
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


    /*
        Class for executing a network request to an API using a background thread
    */
    private class FetcherTask extends AsyncTask<RequestParameter, Void, RequestResult> {
        @Override
        protected RequestResult doInBackground(RequestParameter... fetcherTaskParameters) {
            // send request to the API
            return RequestSender.getTask(fetcherTaskParameters[0]);
        }

        @Override
        protected void onPostExecute(RequestResult res) {
            // remove the progress bar
            mResultProgressBar.setVisibility(View.GONE);

            // process the result
            if (res.getStatus() == RequestResult.ERROR) {
                Toast.makeText(getContext(), getString(R.string.result_error), Toast.LENGTH_LONG).show();
                finish();
            } else if (res.getStatus() == RequestResult.EMPTY) {
                Toast.makeText(getContext(), getString(R.string.result_empty), Toast.LENGTH_LONG).show();
                finish();
            } else if (res.getStatus() == RequestResult.NO_ACTIVITY_FOUND) {
                Toast.makeText(getContext(), getString(R.string.result_no_activity), Toast.LENGTH_LONG).show();
                finish();
            } else if (res.getStatus() == RequestResult.SUCCESS) {
                // parse json here
                Task task = Utils.parseTask(res.getJSON());

                if (task==null) {
                    Toast.makeText(getContext(), getString(R.string.json_parse_failed), Toast.LENGTH_LONG).show();
                }

                // update the ui of this fragment
                mHeader = task.getTitle();
                mLink = task.getURL();
                updateUI();

            } else {
                Toast.makeText(getContext(), getString(R.string.result_unknown_error), Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }


    // convenience method that calls the finish method of the hosting activity
    private void finish() {
        getActivity().finish();
    }


    /*
        Updates the header and the link textviews of this fragment
     */
    private void updateUI() {
        // set the data to be shown
        mResultHeader.setText(mHeader);

        if (mLink.length() == 0) {
            mResultLearnMore.setVisibility(View.GONE);
            mResultLink.setVisibility(View.GONE);
        } else {
            mResultLink.setText(mLink);
        }
    }

}
