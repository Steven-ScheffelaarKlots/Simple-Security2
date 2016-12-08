package com.stevenscheffelaar.simple_security;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Steven on 11/27/2016.
 */

public class LightSchedulerFragment extends Fragment{
    public LightSchedulerFragment() {}


    public static  LightSchedulerFragment newInstance() {
        LightSchedulerFragment fragment = new LightSchedulerFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scheduler, container, false);

        Button clickButton = (Button) rootView.findViewById(R.id.scheduleButton);
        clickButton.setOnClickListener( new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                scheduleLight();
            }
        });
        return rootView;
    }

    public void scheduleLight() {
        new DaylightApiRequest(getContext(), new DaylightApiRequest.AsyncResponse() {
            @Override
            public void processFinish(Integer output) {

                Log.e("Job", output.toString());
                JobScheduler mJobScheduler = (JobScheduler)
                        getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName( getActivity().getPackageName(),
                        SchedulerJobService.class.getName()))
                        .setRequiresCharging(true)
                        .setPeriodic(output)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                        .build();

                int result = mJobScheduler.schedule(jobInfo);
                if (result == JobScheduler.RESULT_SUCCESS) Log.d("MAIN", "Job scheduled successfully!");
            }
        }).execute();
    }
}
