package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText title, date, description;
    Button add;
//    database myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

//        myDatabase = new database(this);
        //SQLiteDatabase database = myDatabase.getWritableDatabase();

        title = (EditText) findViewById(R.id.title);
        date = (EditText) findViewById(R.id.date);
        description = (EditText) findViewById(R.id.description);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database myDatabase = new database(AddActivity.this);
                boolean isInserted = myDatabase.insertData(date.getText().toString(),
                        title.getText().toString(),
                        description.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(AddActivity.this, "Task Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Error while inserting\ndata to database", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(AddActivity.this, TaskActivity.class));
                finish();
            }
        });
    }


}
