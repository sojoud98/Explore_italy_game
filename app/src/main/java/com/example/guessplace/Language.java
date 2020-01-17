package com.example.guessplace;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Language extends AppCompatActivity {
    TextView timing, score;
    ImageView next;
    SharedPreferences.Editor editor;
    int score_int;
    Button ch1, ch2, ch3, ch4;
    SharedPreferences pref;
    private static final String TAG = "test";
    ArrayList<String> englishWords;
    ArrayList<String> italianWords;
    ArrayList<Integer> index;
    int i, ans, color = Color.RED;
    Task t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

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


    public void init() {
        timing.setTextColor(color);
        englishWords = new ArrayList<>();
        italianWords = new ArrayList<>();
        index = new ArrayList<>();
        i = 0;
        SQLiteDatabase db = new DBConnect(getApplicationContext()).getReadableDatabase();
        Cursor cursor = db.query("words", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Log.d(TAG, cursor.getString(cursor.getColumnIndex("english")) + " : " + cursor.getString(cursor.getColumnIndex("italian")));
            englishWords.add(cursor.getString(cursor.getColumnIndex("english")));
            italianWords.add(cursor.getString(cursor.getColumnIndex("italian")));
            index.add(i);
            i++;
            cursor.moveToNext();

        }
        Collections.shuffle(index);
        Random r = new Random();
        ans = r.nextInt(4);
        TextView italianWord = findViewById(R.id.italianword);
        italianWord.setText(italianWords.get(index.get(ans)) + " means :");
        ch1.setText(englishWords.get(index.get(0)));
        ch2.setText(englishWords.get(index.get(1)));
        ch3.setText(englishWords.get(index.get(2)));
        ch4.setText(englishWords.get(index.get(3)));
        t.cancel(true);
        t = new Task(getApplicationContext(), timing, ch1, ch2, ch3, ch4);
        t.execute();

    }
}
