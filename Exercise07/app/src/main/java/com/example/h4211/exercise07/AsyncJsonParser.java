package com.example.h4211.exercise07;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by h4211 on 10/25/2016.
 */

public class AsyncJsonParser extends AsyncTask<String, Void, JSONObject> {
    private List<Kentta> kenttaList;
    public asyncresponse delegate = null;
    public AsyncJsonParser(){}
    public AsyncJsonParser(asyncresponse Delegate){
        delegate = Delegate;
    }

    @Override
    protected JSONObject doInBackground(String... urls) {
        Log.d("AsyncJsonParser", "doInBackground");
        HttpURLConnection urlConnection = null;
        JSONObject json = null;
        try {
            URL url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            json = new JSONObject(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }
        return json;
    }

    protected void onPostExecute(JSONObject json) {
        kenttaList = new ArrayList<>();
        try {
            JSONArray array = json.getJSONArray("kentat");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                kenttaList.add(new Kentta(object.getString("Tyyppi"),object.getString("lat"),object.getString("lng"),object.getString("Kentta"),
                        object.getString("Osoite"),object.getString("Puhelin"),object.getString("Sahkoposti"),object.getString("Webbi"),
                        object.getString("Kuva"),object.getString("Kuvaus")));
            }
        } catch (JSONException e) {
            Log.e("asyncJsonParser", "Error parsing data.");
        }
        delegate.onJSONparserComplete(kenttaList);
    }
}

