package com.stevenscheffelaar.simple_security;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Steven on 11/16/2016.
 */

public class AddLightFragment extends Fragment{
    public AddLightFragment() {

    }

    public static AddLightFragment newInstance() {
        AddLightFragment fragment = new AddLightFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_light, container, false);


        return rootView;
    }
}
