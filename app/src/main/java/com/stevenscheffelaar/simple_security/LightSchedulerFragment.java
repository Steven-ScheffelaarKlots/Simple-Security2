package com.stevenscheffelaar.simple_security;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        return rootView;
    }
}
