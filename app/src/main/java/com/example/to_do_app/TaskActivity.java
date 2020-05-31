package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskActivity extends AppCompatActivity {

    Button add_task, show_task, github;

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

        github = (Button) findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://github.com/anuraj07");
            }
        });
    }
    public void openUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent launchGit = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(launchGit);
    }
}
