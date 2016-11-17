package com.stevenscheffelaar.simple_security;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Steven on 11/16/2016.
 */

public class LightSwitchFragment extends Fragment {
    public LightSwitchFragment() {

    }

    public static LightSwitchFragment newInstance() {
        LightSwitchFragment fragment = new LightSwitchFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.light_list, container, false);

        final ArrayList<Light> lights = new ArrayList<>();
        lights.add(new Light(1, "123", "456"));
        LightAdapter adapter = new LightAdapter(getActivity(), lights);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }
}
