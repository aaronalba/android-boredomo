package com.practice.boredomo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.ChipGroup;
import com.practice.boredomo.R;
import com.practice.boredomo.model.FetcherTaskParameter;
import com.practice.boredomo.model.RequestResult;
import com.practice.boredomo.model.Task;
import com.practice.boredomo.utils.NetworkUtils;
import com.practice.boredomo.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragment for controlling the Searching of a Random Activity
 * @author Aaron Alba
 */
public class SearchFragment extends Fragment {
    private ProgressBar mProgressBar;
    private Button mSearchBtn;
    private ChipGroup mTypeGroup;
    private ChipGroup mParticipantGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // get references to the widgets
        mProgressBar = view.findViewById(R.id.search_progressBar);
        mSearchBtn = view.findViewById(R.id.search_button);
        mTypeGroup = view.findViewById(R.id.search_type_chipGroup);
        mParticipantGroup = view.findViewById(R.id.search_participant_chipGroup);


        // set onClick to the search button
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });

        return view;
    }


    /*
     * Searches the API with the given parameters
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

        // get the number of participants
        String participants = Utils.participantIdConverter(mParticipantGroup.getCheckedChipId());

        // send the http request to the api
        FetcherTaskParameter params = new FetcherTaskParameter(activityTypes, participants);
        new FetcherTask().execute(params);
    }



    /*
        Class for executing a network request to an API
     */
    private class FetcherTask extends AsyncTask<FetcherTaskParameter, Void, RequestResult> {
        @Override
        protected RequestResult doInBackground(FetcherTaskParameter... fetcherTaskParameters) {
            // send request to the API
            return NetworkUtils.getTask(fetcherTaskParameters[0]);
        }

        @Override
        protected void onPostExecute(RequestResult res) {
            // remove the progress bar
            mProgressBar.setVisibility(View.GONE);

            // process the result
            if (res.getStatus() == RequestResult.ERROR) {
                Toast.makeText(getContext(), getString(R.string.result_error), Toast.LENGTH_LONG).show();
            } else if (res.getStatus() == RequestResult.EMPTY) {
                Toast.makeText(getContext(), getString(R.string.result_empty), Toast.LENGTH_LONG).show();
            } else if (res.getStatus() == RequestResult.NO_ACTIVITY_FOUND) {
                Toast.makeText(getContext(), getString(R.string.result_no_activity), Toast.LENGTH_LONG).show();
            } else if (res.getStatus() == RequestResult.SUCCESS) {
                // parse json here
                Task task = parseTask(res.getJSON());

                if (task==null) {
                    Toast.makeText(getContext(), getString(R.string.json_parse_failed), Toast.LENGTH_LONG).show();
                }

                // launch the Result Activity

            } else {
                Toast.makeText(getContext(), getString(R.string.result_unknown_error), Toast.LENGTH_LONG).show();
            }
        }
    }


    /*
        Converts the given JSON string to a Task object
     */
    private Task parseTask(String json) {
        try {
            JSONObject taskJSON = new JSONObject(json);

            String title = taskJSON.getString("activity");
            String url = taskJSON.getString("link");
            int key = Integer.parseInt(taskJSON.getString("key"));

            // return the Task object
            return new Task(title, url, key);

        } catch (JSONException e) {
            return null;
        }
    }
}
