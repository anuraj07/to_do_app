package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskActivity extends AppCompatActivity {

    Button add_task;
    Button show_task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        add_task = (Button) findViewById(R.id.add_task);
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaskActivity.this, AddActivity.class));
            }
        });

        show_task =(Button) findViewById(R.id.show_task);
        show_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaskActivity.this, ShowActivity.class));
            }
        });
    }
}
