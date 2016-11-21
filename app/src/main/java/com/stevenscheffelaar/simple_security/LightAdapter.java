package com.stevenscheffelaar.simple_security;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Steven on 11/16/2016.
 */

public class LightAdapter extends ArrayAdapter<Light> {
    public  LightAdapter(Context context, ArrayList<Light> lights) { super(context, 0, lights); }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Light currentLight = getItem(position);

        TextView lightNumberTextView = (TextView) listItemView.findViewById(R.id.light_number_text_view);
        lightNumberTextView.setText(String.valueOf(currentLight.getLightNum()));

        TextView lightOnTextView = (TextView) listItemView.findViewById(R.id.light_on_text_view);

        lightOnTextView.setText(currentLight.getOnCode());

        TextView lightOffTextView = (TextView) listItemView.findViewById(R.id.light_off_text_view);

        lightOffTextView.setText(currentLight.getOffCode());

        return listItemView;
    }
}
