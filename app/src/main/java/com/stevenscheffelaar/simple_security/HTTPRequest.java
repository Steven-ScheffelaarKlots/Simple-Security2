package com.stevenscheffelaar.simple_security;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTTPRequest extends AsyncTask <String, Void, String> {
    private  final String TAG = HTTPRequest.class.getName();
    String response = "";


    protected String doInBackground(String ... strings){

        HttpURLConnection urlConnection = null;
        try {
            String serverURL = "http://www.StevenScheffelaar.com:81/api/sendSignal/" +strings[0];
            URL url = new URL(serverURL.toString());
            Log.e(TAG, url.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException exceptionName)
        {

        } catch (IOException exception) {}
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("Response", "" + response);
    }
}
