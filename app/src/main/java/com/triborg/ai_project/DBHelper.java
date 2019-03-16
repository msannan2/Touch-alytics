package com.triborg.ai_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AIProj.db";

    DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE input " + "(id INTEGER PRIMARY KEY, User_ID TEXT, Phone_ID TEXT, Pressure REAL, " +
                        "Touch_Size REAL, Distance REAL, Speed REAL, Time INTEGER, Touch_Major REAL, Touch_Minor REAL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    void insertRecord(String user, String phone, float pressure, float t_size, float distance,
        float speed, long time, float t_major, float t_minor)
    {   
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put("User_ID", user);
        values.put("Phone_ID", phone);
        values.put("Pressure", pressure);
        values.put("Touch_Size", t_size);
        values.put("Distance", distance);
        values.put("Speed", speed);
        values.put("Time", time);
        values.put("Touch_Major", t_major);
        values.put("Touch_Minor", t_minor);

        db.insert("input", null, values);
        db.close();
    }

    /*void deleteRecord(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!user.equals("")){
            db.delete("input", "User_ID = ?", new String[]{user});
        }
        db.close();
    }*/

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM input", null);
        return res;
    }
}
