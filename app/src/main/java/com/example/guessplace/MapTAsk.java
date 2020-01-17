package com.example.guessplace;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MapTAsk extends AsyncTask<String, String, Long> {
    TextView tv;
    int color = Color.RED;
    Context c;
    GoogleMap googleMap;

    public MapTAsk(Context c, TextView tv, GoogleMap googleMap) {
        this.tv = tv;
        this.googleMap = googleMap;
        this.c = c;
        tv.setText("00:05");
        color = tv.getCurrentTextColor();
        tv.setTextColor(color);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });


    }

    @Override
    protected Long doInBackground(String... strings) {
        for (int i = 5; i > -1; i--) {
            try {
                Thread.sleep(500);
                publishProgress("" + i);
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        if (values[0].equals("2") || values[0].equals("1") || values[0].equals("0")) {
            tv.setTextColor(Color.RED);

        } else {
            tv.setTextColor(color);

        }
        tv.setText("00:0" + values[0]);


    }
}
