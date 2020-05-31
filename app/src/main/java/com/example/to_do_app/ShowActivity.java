package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    database myDatabase;
    ArrayList<String> id, date, title, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        myDatabase = new database(ShowActivity.this);
        id = new ArrayList<>();
        date = new ArrayList<>();
        title = new ArrayList<>();
        description = new ArrayList<>();
        viewAll();

    }

    private void viewAll() {
        Cursor cursor = myDatabase.getAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Task...", Toast.LENGTH_SHORT).show();
            return;
        } else {

        }
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append(cursor.getString(0)+"\n");
            buffer.append("DATE : "+cursor.getString(1)+"\n");
            buffer.append("TITLE : "+cursor.getString(2)+"\n");
            buffer.append("DESCRIPTION : "+cursor.getString(3)+"\n\n");
            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText(buffer.toString());
        }
    }
}
