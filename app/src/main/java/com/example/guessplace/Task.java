package com.example.guessplace;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class Task extends AsyncTask<String, String, Long> {
    TextView tv;
    int color = Color.RED;
    Context c;
    Button ch1, ch2, ch3, ch4;

    public Task(Context c,TextView tv) {
        this.tv = tv;

        this.c = c;
        tv.setText("00:05");
        color = tv.getCurrentTextColor();
        tv.setTextColor(color);
        }

    public Task(Context c, TextView tv, Button ch1, Button ch2, Button ch3, Button ch4) {
        this.tv = tv;
        this.ch1 = ch1;
        this.ch2 = ch2;
        this.ch3 = ch3;
        this.ch4 = ch4;
        this.c = c;
        ch1.setEnabled(true);
        tv.setText("00:05");
        color = tv.getCurrentTextColor();
        tv.setTextColor(color);
        ch1.setEnabled(true);
        ch2.setEnabled(true);
        ch3.setEnabled(true);
        ch4.setEnabled(true);
        ch1.setBackgroundTintList(c.getResources().getColorStateList(R.color.mainColor));
        ch2.setBackgroundTintList(c.getResources().getColorStateList(R.color.mainColor));
        ch3.setBackgroundTintList(c.getResources().getColorStateList(R.color.mainColor));
        ch4.setBackgroundTintList(c.getResources().getColorStateList(R.color.mainColor));
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);


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
            if (values[0].equals("0")) {
                ch1.setEnabled(false);
                ch2.setEnabled(false);
                ch3.setEnabled(false);
                ch4.setEnabled(false);
            }
        } else {
            tv.setTextColor(color);

        }
        tv.setText("00:0" + values[0]);


    }
}
