package com.stevenscheffelaar.simple_security;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Button submit = (Button) rootView.findViewById(R.id.submit_light);
        final EditText off_signal = (EditText) rootView.findViewById(R.id.off_signal);
        final EditText on_signal = (EditText) rootView.findViewById(R.id.on_signal);
        final EditText name = (EditText) rootView.findViewById(R.id.name);
        submit.setOnClickListener(
            new View.OnClickListener() {
                public void onClick (View view){
                    DatabaseHandler db = new  DatabaseHandler(getActivity());
                    int newNum = db.getLightCount() + 1;
                    db.addLight(new Light(newNum, on_signal.getText().toString(),
                            off_signal.getText().toString(),name.getText().toString() ));

                    Context context = (getContext().getApplicationContext());
                    CharSequence text = "New Light Added";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        );
        return rootView;
    }
}
