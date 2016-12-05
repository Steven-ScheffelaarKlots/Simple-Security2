package com.stevenscheffelaar.simple_security;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        SharedPreferences settings = getContext().getSharedPreferences("prefFile", MODE_PRIVATE);
        Boolean daylightLightSchedule = settings.getBoolean("daylightSchedule", false);
        final CheckBox sundownCheckbox=(CheckBox) rootView.findViewById(R.id.checkbox_daylight);
        sundownCheckbox.setChecked(daylightLightSchedule);
        sundownCheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(sundownCheckbox.isChecked()){
                    System.out.println("Checked");
                }else{
                    System.out.println("Un-Checked");
                }
            }
        });;
        return rootView;
    }
}
