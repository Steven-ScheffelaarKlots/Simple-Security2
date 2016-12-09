package com.stevenscheffelaar.simple_security;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTTPRequest extends AsyncTask <String, Void, String> {
    private  final String TAG = HTTPRequest.class.getName();
    String response = "";


    protected String doInBackground(String ... strings){

        HttpURLConnection urlConnection;
        String serverURL = "http://StevenScheffelaar.com:81/api/sendSignal/" +strings[0];
        InputStream in = null;
        try {

            URL url = new URL(serverURL);
            Log.e(TAG, url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            try {
                urlConnection.setDoOutput(true);
                in = new BufferedInputStream(urlConnection.getInputStream());
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException exceptionName)
        {} catch (IOException exception) {}
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("Response", "" + response);
    }
}
