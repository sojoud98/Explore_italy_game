package com.example.guessplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity {
    CardView language, cuisine, places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setIcon(R.mipmap.title);
        actionBar.setTitle("  Explore Italy");
        language = findViewById(R.id.language);
        cuisine = findViewById(R.id.cuisine);
        places = findViewById(R.id.places);
//        ImageView imageView = findViewById(R.id.cities);
//        Bitmap b = ImageHelper.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.milan), 300, "Cities");
//        imageView.setImageBitmap(b);
//        imageView.bringToFront();
//
//
//        ImageView  imageView2 = findViewById(R.id.food);
//        Bitmap b2 = ImageHelper2.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pizza), 300, "Italian Cuisine");
//        imageView2.setImageBitmap(b2);
//        imageView2.setTranslationZ(-10);
//
//
//
//        ImageView  imageView3 = findViewById(R.id.lng);
//        Bitmap b3 = ImageHelper3.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ciao), 300, "Language");
//        imageView3.setImageBitmap(b3);
//        imageView3.setTranslationZ(-20);
        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),place.class);
                startActivity(i);
            }
        });
        cuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),cuisineActivity.class);
                startActivity(i);
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Language.class);
                startActivity(i);
            }
        });


    }


}
