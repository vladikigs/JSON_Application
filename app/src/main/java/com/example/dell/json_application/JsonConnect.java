package com.example.dell.json_application;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonConnect extends AsyncTask<Void, Void, String > {

    private final EmployeesModel.LoadJsonCallback callback;

    JsonConnect(EmployeesModel.LoadJsonCallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        try {
            URL url = new URL("http://www.mocky.io/v2/56fa31e0110000f920a72134");

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            resultJson = buffer.toString();
            urlConnection.disconnect();
        }
        catch (IOException e) {
            Log.d("asas", "errConnection");
            return resultJson;
        }
        return resultJson;
    }


    @Override
    protected void onPostExecute(String strJson) {
        if (callback != null) {
            callback.onLoad(strJson);
        }
    }
}
