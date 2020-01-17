package com.example.guessplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnect extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Places.db";

    String TAG = "sqlite debug";

    public DBConnect(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    //    public void insertPlace(String name, double lng, double lat) throws SQLiteException {
//        db = new DBConnect(getApplicationContext());
//
//        Bitmap img = BitmapFactory.decodeResource(getResources(),
//                R.drawable.);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        img.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte imageInByte[] = stream.toByteArray();
//        SQLiteDatabase database = new DBConnect(this).getReadableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("name", name);
//        cv.put("img", imageInByte);
//        cv.put("lng", lng);
//        cv.put("lat", lat);
//        Long i = database.insert("places", null, cv);
//        Log.d(TAG, "insertPlace: "+i+" "+name);
//    }
    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("CREATE TABLE places ("
//                + "id  INTEGER PRIMARY KEY, lat DOUBLE, lng DOUBLE, name  TEXT, img BLOB)");

//        db.execSQL("CREATE TABLE " + "words" + "("
//                + COLUMN_ID + " INTEGER PRIMARY KEY," +
//                " english TEXT," +
//                "italian TEXT" + ")");
//        db.execSQL("CREATE TABLE " + "food" + "("
//                + COLUMN_ID + " INTEGER PRIMARY KEY, name TEXT, img blob)");

    }
    public void insertWord( String eng,String italian ){
        SQLiteDatabase  db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("english",eng);
        values.put("italian",italian);
        db.insert("words",null,values);
    }
    public void insertFood(String name, byte[] image) throws SQLiteException {
        SQLiteDatabase database =this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("img", image);
        database.insert("food", null, cv);
    }
    public void insertPlace(String name, byte[] image, float lng, float lat) throws SQLiteException {
        SQLiteDatabase database =this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("img", image);
        cv.put("lng", lng);
        cv.put("lat", lat);
        database.insert("places", null, cv);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
