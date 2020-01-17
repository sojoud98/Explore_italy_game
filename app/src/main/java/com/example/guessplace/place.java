package com.example.guessplace;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class place extends AppCompatActivity {
    TextView timing, score;
    CardView card1, card2;
    Button guessbtn;
    ImageView next, imageView;
    SharedPreferences.Editor editor;
    int score_int, color = Color.RED, ans;
    DBConnect db;
    SharedPreferences pref;
    private static final String TAG = "test";
    GoogleMap googleMap;
    MapTAsk t;
    ArrayList<Integer> index;
    ArrayList names;
    ArrayList<Double> lngs, lats;
    HashMap<Integer, Double> lat_map = new HashMap<>();
    HashMap<Integer, Double> lng_map = new HashMap<>();
    HashMap<Integer, String> name_map = new HashMap<>();
    ArrayList<Marker> markers;
    SupportMapFragment mapFragment;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        markers = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mgoogleMap) {
                googleMap = mgoogleMap;
//                LatLng place1 = new LatLng(41.890190, 12.492150);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(44.4, 10.4), 2));
//                googleMap.addMarker(new MarkerOptions().position(place1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.nightmode));
                init();
            }
        });

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        timing = findViewById(R.id.timing);
        color = timing.getCurrentTextColor();
        t = new MapTAsk(getApplicationContext(), timing,googleMap);
        next = findViewById(R.id.nextbtn);
        score = findViewById(R.id.score);
        card1 = findViewById(R.id.choices);
        card2 = findViewById(R.id.blur);
        card1.setVisibility(View.INVISIBLE);
        card2.setVisibility(View.INVISIBLE);
        guessbtn = findViewById(R.id.guess);
        guessbtn.setVisibility(View.VISIBLE);
        imageView = findViewById(R.id.img_place);
       
        score_int = Integer.parseInt(pref.getString("score", "0"));
        score.setText("Score: " + score_int);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });


        guessbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessbtn.setVisibility(View.GONE);
                card1.setVisibility(View.VISIBLE);
                card2.setVisibility(View.VISIBLE);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessbtn.setVisibility(View.GONE);
                card1.setVisibility(View.VISIBLE);
                card2.setVisibility(View.VISIBLE);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessbtn.setVisibility(View.VISIBLE);
                card1.setVisibility(View.GONE);
                card2.setVisibility(View.GONE);
            }
        });

    }




    public void init() {
        for (Marker m : markers) {
            m.remove();

        }
        timing.setTextColor(color);
        names = new ArrayList<String>();
        lngs = new ArrayList<>();
        lats = new ArrayList<>();
        index = new ArrayList<>();
        SQLiteDatabase db = new DBConnect(getApplicationContext()).getReadableDatabase();
        Cursor cursor = db.rawQuery("select id,lat,lng,name from places ", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            name_map.put(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))), cursor.getString(cursor.getColumnIndex("name")));
            lat_map.put(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))), Double.parseDouble(cursor.getString(cursor.getColumnIndex("lat"))));
            lng_map.put(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))), Double.parseDouble(cursor.getString(cursor.getColumnIndex("lng"))));
            index.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            cursor.moveToNext();

        }
        Collections.shuffle(index);
        Random r = new Random();
        ans = r.nextInt(4);
        getid(index.get(ans));
        final String ans_name = name_map.get(index.get(ans));
        latlngs = new ArrayList<>();
        latlngs.add(new LatLng(lng_map.get(index.get(0)), lat_map.get(index.get(0)))); //some latitude and logitude value
        latlngs.add(new LatLng(lng_map.get(index.get(1)), lat_map.get(index.get(1)))); //some latitude and logitude value
        latlngs.add(new LatLng(lng_map.get(index.get(2)), lat_map.get(index.get(2)))); //some latitude and logitude value
        latlngs.add(new LatLng(lng_map.get(index.get(3)), lat_map.get(index.get(3)))); //some latitude and logitude value
        for (int i = 0; i < latlngs.size(); i++) {
            LatLng point = latlngs.get(i);
            options.position(point);
            options.title(name_map.get(index.get(i)));
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            markers.add(googleMap.addMarker(options));

        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                for (Marker m : markers) {
                    m.remove();

                }

                String position = (marker.getTitle());
                if (ans_name.equals(position)) {
                    score_int += 10;

                    editor.putString("score", "" + score_int);
                    editor.commit();
                    score.setText("Score: " + pref.getString("score", "0"));
                    score.setTextColor(Color.GREEN);
                } else {
                    score.setTextColor(Color.RED);
                }

                LatLng latLng = new LatLng(lng_map.get(index.get(ans)), lat_map.get(index.get(ans)));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(ans_name);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                markerOptions.getPosition();
               Marker r= googleMap.addMarker(markerOptions);
               markers.add(r);
                return false;


            }
        });

        t.cancel(true);
        t = new MapTAsk(getApplicationContext(), timing,googleMap);
        t.execute();

    }

    public void getid(Integer i) {

        Cursor res =
                new DBConnect(this).getReadableDatabase().rawQuery("select img from places where ID = ?", new String[]{"" + i});
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            byte[] image = res.getBlob(0);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bmp);
            res.moveToNext();
        }
    }

}
