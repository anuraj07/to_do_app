package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText title, date, description;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = (EditText) findViewById(R.id.title);
        date = (EditText) findViewById(R.id.date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month+1;
                                String date1 = day+"/"+month+"/"+year;
                                date.setText(date1);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });
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
