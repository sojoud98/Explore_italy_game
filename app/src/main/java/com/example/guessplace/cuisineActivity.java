package com.example.guessplace;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class cuisineActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ImageView imageView;
    TextView timing, score;
    ImageView next;
    SharedPreferences.Editor editor;
    int score_int;
    CardView card;
    Button ch1, ch2, ch3, ch4, guess;
    SharedPreferences pref;
    private static final String TAG = "test";
    ArrayList<String> names;
    ArrayList<Integer> index;
    int ans, color = Color.RED;
    Task t;
    HashMap<Integer, String> map = new HashMap<>();

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
        card = findViewById(R.id.card);
        guess=findViewById(R.id.guess);
        guess.setVisibility(View.VISIBLE);
        card.setVisibility(View.GONE);
        db = new DBConnect(getApplicationContext()).getReadableDatabase();
        imageView = findViewById(R.id.img);
        ch1 = findViewById(R.id.ch1);
        ch2 = findViewById(R.id.ch2);
        ch3 = findViewById(R.id.ch3);
        ch4 = findViewById(R.id.ch4);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        timing = findViewById(R.id.timing);
        color = timing.getCurrentTextColor();

        score = findViewById(R.id.score);
        next = findViewById(R.id.nextbtn);
        score_int = Integer.parseInt(pref.getString("score", "0"));
        score.setText("Score: " + score_int);
        t = new Task(getApplicationContext(), timing, ch1, ch2, ch3, ch4);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
        init();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setVisibility(View.INVISIBLE);

            }
        });
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setVisibility(View.VISIBLE);
            }
        });
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch1.setEnabled(false);
                ch2.setEnabled(false);
                ch3.setEnabled(false);
                ch4.setEnabled(false);
                ch1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.incorrect));

                if (ans == 0) {
                    score_int += 10;
                    editor.putString("score", "" + score_int);
                    editor.commit();
                    score.setText("Score: " + pref.getString("score", "0"));

                    score.setTextColor(Color.GREEN);
                } else {
                    score.setTextColor(Color.RED);
                }
                switch (ans) {
                    case 0:
                        ch1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));
                        break;
                    case 1:
                        ch2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;

                    case 2:
                        ch3.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;
                    case 3:
                        ch4.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;
                    default:
                        break;
                }
            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch1.setEnabled(false);
                ch2.setEnabled(false);
                ch3.setEnabled(false);
                ch4.setEnabled(false);
                ch2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.incorrect));

                if (ans == 1) {

                    score_int += 10;
                    editor.putString("score", "" + score_int);
                    editor.commit();
                    score.setText("Score: " + pref.getString("score", "0"));
                    score.setTextColor(Color.GREEN);
                } else {
                    score.setTextColor(Color.RED);
                }
                switch (ans) {
                    case 0:
                        ch1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));
                        break;
                    case 1:
                        ch2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;

                    case 2:
                        ch3.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;
                    case 3:
                        ch4.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;
                    default:
                        break;
                }
            }
        });
        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch1.setEnabled(false);
                ch2.setEnabled(false);
                ch3.setEnabled(false);
                ch4.setEnabled(false);
                ch3.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.incorrect));

                if (ans == 2) {

                    score_int += 10;
                    editor.putString("score", "" + score_int);
                    editor.commit();
                    score.setText("Score: " + pref.getString("score", "0"));
                    score.setTextColor(Color.GREEN);
                } else {
                    score.setTextColor(Color.RED);
                }
                switch (ans) {
                    case 0:
                        ch1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));
                        break;
                    case 1:
                        ch2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;

                    case 2:
                        ch3.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;
                    case 3:
                        ch4.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;
                    default:
                        break;
                }
            }
        });
        ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch1.setEnabled(false);
                ch2.setEnabled(false);
                ch3.setEnabled(false);
                ch4.setEnabled(false);
                ch4.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.incorrect));

                if (ans == 3) {
                    score_int += 10;
                    editor.putString("score", "" + score_int);
                    editor.commit();
                    score.setText("Score: " + pref.getString("score", "0"));
                    score.setTextColor(Color.GREEN);
                } else {
                    score.setTextColor(Color.RED);
                }
                switch (ans) {
                    case 0:
                        ch1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));
                        break;
                    case 1:
                        ch2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;

                    case 2:
                        ch3.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;
                    case 3:
                        ch4.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));

                        break;
                    default:
                        break;
                }
            }
        });

    }

    public void insertFood(String name) throws SQLiteException {
        Bitmap img = BitmapFactory.decodeResource(getResources(),
                R.drawable.ravioli);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();

        SQLiteDatabase database = new DBConnect(getApplicationContext()).getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("img", imageInByte);
        Long i = database.insert("food", null, cv);
    }

    public void getid(int i) {

        Cursor res =
                db.rawQuery("select * from food where ID = ?", new String[]{"" + i});
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            Log.d("test", res.getString(res.getColumnIndex("name")));
            byte[] image = res.getBlob(2);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bmp);
            res.moveToNext();
        }
    }

    public void get(View view) {
        Cursor c = db.rawQuery("select * from food", null);
        c.moveToFirst();
        if (!c.isAfterLast()) {
            byte[] image = c.getBlob(2);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bmp);
            c.moveToNext();
        }
    }

    public void init() {
        timing.setTextColor(color);
        names = new ArrayList<>();
        index = new ArrayList<>();
        SQLiteDatabase db = new DBConnect(getApplicationContext()).getReadableDatabase();
        Cursor cursor = db.rawQuery("select id,name from food ", null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            map.put(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))), cursor.getString(cursor.getColumnIndex("name")));
            index.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            cursor.moveToNext();

        }
        Collections.shuffle(index);
        Random r = new Random();
        ans = r.nextInt(4);
        getid(index.get(ans));
        Log.d(TAG, "init: " + map.get(index.get(ans)));
        ch1.setText(map.get(index.get(0)));
        ch2.setText(map.get(index.get(1)));
        ch3.setText(map.get(index.get(2)));
        ch4.setText(map.get(index.get(3)));
        t.cancel(true);
        t = new Task(getApplicationContext(), timing, ch1, ch2, ch3, ch4);
        t.execute();

    }
}
