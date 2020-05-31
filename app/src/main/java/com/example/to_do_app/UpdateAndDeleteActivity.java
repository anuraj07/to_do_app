package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAndDeleteActivity extends AppCompatActivity {

    EditText edit_title, edit_date, edit_description;
    Button update;
    String id, date, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_date = (EditText) findViewById(R.id.edit_date);
        edit_description = (EditText) findViewById(R.id.edit_description);
        update = (Button) findViewById(R.id.update_data);

        getAndSetData();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database myDB = new database(UpdateAndDeleteActivity.this);
                date = edit_date.getText().toString();
                title = edit_title.getText().toString();
                description = edit_description.getText().toString();
                myDB.updateData(id, date, title, description);

                startActivity(new Intent(UpdateAndDeleteActivity.this, ShowActivity.class));
                finish();

            }
        });
    }

    void getAndSetData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("date") &&
        getIntent().hasExtra("title") && getIntent().hasExtra("description")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");

            //setting Intent Data
            edit_title.setText(title);
            edit_date.setText(date);
            edit_description.setText(description);



        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
