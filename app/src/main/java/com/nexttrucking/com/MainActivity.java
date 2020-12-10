package com.nexttrucking.com;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button pullBt, burndownBt, grafanaBt, sonarBt, kibanaBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullBt = findViewById(R.id.pull_reminder);
        pullBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PullReminderActivity.class);
                intent.putExtra("url1", "https://pullreminders.com/installs/15793071/leaderboard?d=14d&s=prs&t%5B%5D=182191978&v=reviews");
                intent.putExtra("url2", "https://pullreminders.com/installs/15793071/leaderboard?d=14d&s=comments&t%5B%5D=182191978&v=reviews");
                startActivity(intent);

            }
        });

        burndownBt = findViewById(R.id.burndown);
        burndownBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BurndownChartActivity.class);
                startActivity(intent);

            }
        });

        grafanaBt = findViewById(R.id.grafana);
        grafanaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GrafanaActivity.class);
                startActivity(intent);

            }
        });

        sonarBt = findViewById(R.id.sonar);
        sonarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SonarActivity.class);
                startActivity(intent);

            }
        });

        kibanaBt = findViewById(R.id.kibana);
        kibanaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KibanaActivity.class);
                startActivity(intent);
            }
        });
        kibanaBt.callOnClick();

    }

}
