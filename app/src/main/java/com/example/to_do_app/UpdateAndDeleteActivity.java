package com.example.to_do_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateAndDeleteActivity extends AppCompatActivity {

    EditText edit_title, edit_date, edit_description;
    Button update, delete;
    String id, date, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_date = (EditText) findViewById(R.id.edit_date);
        edit_description = (EditText) findViewById(R.id.edit_description);
        update = (Button) findViewById(R.id.update_data);
        delete = (Button) findViewById(R.id.delete_data);

        getAndSetData();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateAndDeleteActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month+1;
                                String date1 = day+"/"+month+"/"+year;
                                edit_date.setText(date1);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });


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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DELETE "+ title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database myDB = new database(UpdateAndDeleteActivity.this);
                myDB.deleteOneRow(id);
                startActivity(new Intent(UpdateAndDeleteActivity.this, ShowActivity.class));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UpdateAndDeleteActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
