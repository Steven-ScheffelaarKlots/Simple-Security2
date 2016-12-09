package com.stevenscheffelaar.simple_security;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.R.attr.duration;
import static java.security.AccessController.getContext;

/**
 * Created by Steven on 12/4/2016.
 */

public class DaylightApiRequest extends AsyncTask <String, Void, Integer>{
    private  final String TAG = DaylightApiRequest.class.getName();
    String response = "";
    Date convertedDate = new Date();
    Context mContext;

    public interface AsyncResponse {
        void processFinish(Integer output);
    }


    public AsyncResponse delegate = null;

    public DaylightApiRequest(Context context, AsyncResponse delegate){
        this.delegate = delegate;
        mContext = context;
    }


    protected Integer doInBackground(String ... strings){

        int sundown = 0;

        HttpURLConnection urlConnection = null;
        try {
            String serverURL = "http://api.sunrise-sunset.org/json?lat=42.2775&lng=-71.3468&formatted=0";
            URL url = new URL(serverURL.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                response = readStream(urlConnection.getInputStream());
                try {
                    JSONObject requestResponse = new JSONObject(response);
                    String sundownTime = requestResponse.getJSONObject("results").getString("sunset");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

                    try {
                        convertedDate = dateFormat.parse(sundownTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    int period = new Period(DateTime.now(), new DateTime(convertedDate)).getMillis();
                    if (period > 0) {
                        sundown = period;
                    } else {
                        sundown = 10;
                    }

                }
                catch (JSONException exception){

                }

            }
        } catch (MalformedURLException exceptionName)
        {

        } catch (IOException exception) {}
        return sundown;
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    @Override
    protected void onPostExecute(Integer s) {
        super.onPostExecute(s);
        Toast.makeText(mContext, "Sundown is at " + convertedDate.toString() , Toast.LENGTH_LONG).show();
        delegate.processFinish(s);
    }
}

