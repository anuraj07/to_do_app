package com.example.to_do_app;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class database extends SQLiteOpenHelper {

    private static final String dbname = "mydb";
    private static final int version = 1;
    private Context context;
    //public Object insertData;

    public database(Context context) {

        super(context, dbname, null, version);
        this.context = context;
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

    public void deleteOneRow(String row_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete("TODO", "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(String row_id, String date, String title, String description) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TASK_DATE", date);
        cv.put("TITLE", title);
        cv.put("DESCRIPTION", description);

        long result = sqLiteDatabase.update("TODO", cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed To Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Sucessfully", Toast.LENGTH_SHORT).show();
        }

    }


}
