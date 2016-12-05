package com.stevenscheffelaar.simple_security;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by Steven on 12/4/2016.
 */

public class DaylightApiRequest extends AsyncTask <String, Void, String>{
    private  final String TAG = DaylightApiRequest.class.getName();
    String response = "";


    protected String doInBackground(String ... strings){

        HttpURLConnection urlConnection = null;
        try {
            String serverURL = "http://api.sunrise-sunset.org/json?lat=42.2775&lng=-71.3468&formatted=0";
            URL url = new URL(serverURL.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                response = readStream(urlConnection.getInputStream());
//                Log.v("CatalogClient", response);
                try {
                    JSONObject requestResponse = new JSONObject(response);
                    String sundownTime = requestResponse.getJSONObject("results").getString("sunset");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                    Date convertedDate = new Date();
                    try {
                        convertedDate = dateFormat.parse(sundownTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Period period = new Period(DateTime.now(), new DateTime(convertedDate));
                    Log.e(TAG, "This is teh thign " + period.getMillis());
                    JobScheduler mJobScheduler = (JobScheduler)
                            Context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

                    JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName( getPackageName(),
                            SchedulerJobService.class.getName()))
                            .setRequiresCharging(true)
                            .setPeriodic(period.getMillis())
                            .build();

                    mJobScheduler.schedule(jobInfo);
                }
                catch (JSONException exception){

                }

            }
        } catch (MalformedURLException exceptionName)
        {

        } catch (IOException exception) {}
        return null;
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("Response", "" + response);
    }
}

