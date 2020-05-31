package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        myDatabase = new database(ShowActivity.this);
        id = new ArrayList<>();
        date = new ArrayList<>();
        title = new ArrayList<>();
        description = new ArrayList<>();
        viewAll();

        customAdapter = new CustomAdapter(ShowActivity.this, id, date, title, description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowActivity.this));

    }

    private void viewAll() {
        Cursor cursor = myDatabase.getAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Task...", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                date.add(cursor.getString(1));
                title.add(cursor.getString(2));
                description.add(cursor.getString(3));
            }
        }
//        StringBuffer buffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//            buffer.append(cursor.getString(0)+"\n");
//            buffer.append("DATE : "+cursor.getString(1)+"\n");
//            buffer.append("TITLE : "+cursor.getString(2)+"\n");
//            buffer.append("DESCRIPTION : "+cursor.getString(3)+"\n\n");
//            TextView textView = (TextView) findViewById(R.id.text);
//            textView.setText(buffer.toString());
//        }
    }
}
