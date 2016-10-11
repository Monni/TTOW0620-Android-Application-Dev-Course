package com.example.h4211.exercise06;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<String[]> kenttaData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        FetchDataTask stationDataTask = new FetchDataTask();
        stationDataTask.execute("http://ptm.fi/jamk/android/golf_courses");
    }


    class FetchDataTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... urls) {
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
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
            }
            return json;
        }

        protected void onPostExecute(JSONObject json) {
            try {
                JSONArray array = json.getJSONArray("kentat");
                for (int i=0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    kenttaData.add(new String[] {object.getString("lat"), object.getString("lng"), object.getString("Kentta"), object.getString("Osoite"),
                            object.getString("Puhelin"), object.getString("Sahkoposti"), object.getString("Webbi"), object.getString("Tyyppi")});
                }
            } catch (JSONException e) {
                Log.e("JSON", "Error parsing data.");
            }
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(MapsActivity.this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }
            @Override
            public View getInfoContents(Marker marker) {

                Context context = getApplicationContext();

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        for (int i = 0; i < kenttaData.size(); i++) {
            LatLng location = new LatLng(Double.parseDouble(kenttaData.get(i)[0]), Double.parseDouble(kenttaData.get(i)[1]));
            String tyyppi = kenttaData.get(i)[7];
            switch (tyyppi) {
                case "Kulta":
                    mMap.addMarker(new MarkerOptions().position(location).title(kenttaData.get(i)[2]).snippet(kenttaData.get(i)[3]
                            + "\n" + kenttaData.get(i)[4] + "\n" + kenttaData.get(i)[5] + "\n" + kenttaData.get(i)[6])
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                    break;
                case "Kulta/Etu":
                    mMap.addMarker(new MarkerOptions().position(location).title(kenttaData.get(i)[2]).snippet(kenttaData.get(i)[3]
                            + "\n" + kenttaData.get(i)[4] + "\n" + kenttaData.get(i)[5] + "\n" + kenttaData.get(i)[6])
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    break;
                case "?":
                    mMap.addMarker(new MarkerOptions().position(location).title(kenttaData.get(i)[2]).snippet(kenttaData.get(i)[3]
                            + "\n" + kenttaData.get(i)[4] + "\n" + kenttaData.get(i)[5] + "\n" + kenttaData.get(i)[6])
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    break;
            }
        }
    }
}
