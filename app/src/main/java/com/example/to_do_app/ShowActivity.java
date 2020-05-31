package com.example.to_do_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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




        customAdapter = new CustomAdapter(ShowActivity.this,ShowActivity.this, id, date, title, description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowActivity.this));

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                confirmDialog();
//            }
//        });

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

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DELETE "+ title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                myDatabase.deleteOneRow(id);
                Toast.makeText(ShowActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ShowActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
