package com.stevenscheffelaar.simple_security;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        final Light currentLight = getItem(position);

        TextView lightNumberTextView = (TextView) listItemView.findViewById(R.id.light_number_text_view);
        lightNumberTextView.setText(String.valueOf(currentLight.getLightName()));

        Button offBtn = (Button)listItemView.findViewById(R.id.light_off_text_view);
        Button onBtn = (Button)listItemView.findViewById(R.id.light_on_text_view);

        onBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new HTTPRequest().execute(currentLight.getOnCode());

                Context context = (getContext().getApplicationContext());
                CharSequence text = "Light Turned On";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        offBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new HTTPRequest().execute(currentLight.getOffCode());
                Context context = (getContext().getApplicationContext());
                CharSequence text = "Light Turned Off";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        return listItemView;
    }
}
