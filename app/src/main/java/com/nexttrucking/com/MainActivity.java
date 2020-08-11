package com.nexttrucking.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button pullBt;
    private Button burnDownBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullBt = findViewById(R.id.pull_reminder);
        pullBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LRActivity.class );
                intent.putExtra("url1","https://pullreminders.com/installs/15793071/leaderboard?d=14d&s=prs&t%5B%5D=182191978&v=reviews");
                intent.putExtra("url2","https://pullreminders.com/installs/15793071/leaderboard?d=14d&s=comments&t%5B%5D=182191978&v=reviews");
                startActivity(intent);

            }
        });
        burnDownBt = findViewById(R.id.burn_down);
        burnDownBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pullBt.callOnClick();

    }

}
