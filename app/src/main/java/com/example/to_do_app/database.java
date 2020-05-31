package com.example.to_do_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

    private static final String dbname = "mydb";
    private static final int version = 1;
    //public Object insertData;

    public  database(Context context) {

        super(context, dbname, null, version);
//        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE TODO (_id INTEGER PRIMARY KEY AUTOINCREMENT, TASK_DATE DATE, TITLE TEXT, DESCRIPTION REAL)";
        sqLiteDatabase.execSQL(sql);
        //insert

        //insertData("03/04/2020", "nahana hai", "pakka se", sqLiteDatabase);

    }
    boolean insertData(String date, String title, String description) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TASK_DATE", date);
        values.put("TITLE", title);
        values.put("DESCRIPTION", description);
        long result = sqLiteDatabase.insert("TODO", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Cursor getAllData () {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM TODO", null);
        return res;
    }


}
