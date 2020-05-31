package com.example.to_do_app;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
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




        customAdapter = new CustomAdapter(ShowActivity.this,ShowActivity.this, id, date, title, description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowActivity.this));

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }*/

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
    }


}
