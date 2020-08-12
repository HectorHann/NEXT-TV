package com.nexttrucking.com;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class BurnDownActivity extends AppCompatActivity {

    private WebView webView;
    private TextView textView;
    private String title;
    private String url;

    private Map<String, String> borndownMap = new HashMap<>();
    private int mapIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn_down);
        initBornDownMap();
        textView = findViewById(R.id.title);
        webView = findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        new Thread(new BurnDownActivity.MyThread()).start();
    }

    private void initBornDownMap() {
        borndownMap.put("Hotpot Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=76&projectKey=APD&view=reporting&chart=burndownChart");
        borndownMap.put("Spaceport Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=113&projectKey=APD&view=reporting&chart=burndownChart");
        borndownMap.put("Omega Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=112&projectKey=APD&view=reporting&chart=burndownChart");
        borndownMap.put("Quality Eng Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=91&projectKey=PLAT&view=reporting&chart=burndownChart");
        borndownMap.put("Marvel Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=114&projectKey=APD&view=reporting&chart=burndownChart");
    }


    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(1000 * 60 * 10);
                    Message message = new Message();
                    handler.sendMessage(message);
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
