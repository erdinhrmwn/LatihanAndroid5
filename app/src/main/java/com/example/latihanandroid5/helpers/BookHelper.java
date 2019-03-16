package com.example.latihanandroid5.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookHelper extends SQLiteOpenHelper {
    final static String DBNAME = "books.db";
    final static int DBVERSION = 1;

    public BookHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreate = "CREATE TABLE samplebooks(_id integer primary key autoincrement," +
                "title text," +
                "author text);";
        db.execSQL(queryCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryDrop = "DROP TABLE IF EXISTS samplebooks";
        db.execSQL(queryDrop);
        onCreate(db);
    }
}
