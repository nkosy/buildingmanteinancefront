package com.nkosi.buildingmanteinancefront.repository.myrestimpl;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nkosi on 2015/10/01.
 */
public class CallAPI extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        String urlString=params[0]; ///URL to call

        String resultToDisplay = "";

        InputStream in = null;

        // HTTP Get
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
        }catch(Exception ex){
            return ex.getMessage();
        }
        return resultToDisplay;
    }
}
