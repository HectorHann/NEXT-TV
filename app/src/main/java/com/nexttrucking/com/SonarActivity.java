package com.nexttrucking.com;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class SonarActivity extends AppCompatActivity {

    private WebView webView;
    private TextView textView;
    private String title;
    private String url;
    private String script;

    private Map<String, String> borndownMap = new HashMap<>();
    private int mapIdx;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonar);
        initBornDownMap();
        textView = findViewById(R.id.title);
        webView = findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);


        script = "javascript:(function() { " +
                "document.getElementsByClassName('layout-page-side-outer')[0].remove(); " +
                "document.getElementById('global-navigation').remove(); " +
                "})();";

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("analysis_date")) {
                    handler.sendEmptyMessageDelayed(2, 3000);
                }


            }
        });

        title = (String) borndownMap.keySet().toArray()[mapIdx];
        url = borndownMap.get(title);
        textView.setText(title);
        webView.loadUrl(url);

        new Thread(new SonarActivity.MyThread()).start();
    }

    private void initBornDownMap() {
        borndownMap.put("Hotpot Team", "https://sonar.tools.aws.nexttrucking.com/projects?sort=-analysis_date&tags=hotpot");
        borndownMap.put("Spaceport Team", "https://sonar.tools.aws.nexttrucking.com/projects?sort=-analysis_date&tags=spaceport");
        borndownMap.put("Omega Team", "https://sonar.tools.aws.nexttrucking.com/projects?sort=-analysis_date&tags=omega");
        borndownMap.put("Marvel Team", "https://sonar.tools.aws.nexttrucking.com/projects?sort=-analysis_date&tags=marvel");
    }


    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Message message = new Message();
                    handler.sendMessage(message);
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                webView.loadUrl(script);
                Log.d("NEXT", script);
                return;
            }
            mapIdx = mapIdx % borndownMap.size();
            title = (String) borndownMap.keySet().toArray()[mapIdx];
            url = borndownMap.get(title);
            mapIdx++;
            textView.setText(title);
            webView.loadUrl(url);
            Log.d("NEXT", title + " | " + url);
            super.handleMessage(msg);
        }

    };

}
