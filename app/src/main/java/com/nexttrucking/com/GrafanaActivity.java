package com.nexttrucking.com;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class GrafanaActivity extends AppCompatActivity {

    private WebView webView;
    private TextView textView;
    private String title;
    private String url;

    private Map<String, String> serviceMap = new HashMap<>();
    private int mapIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafana);
        initServiceMap();
        textView = findViewById(R.id.title);
        textView.setVisibility(View.GONE);
        webView = findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        new Thread(new GrafanaActivity.MyThread()).start();
    }

    private void initServiceMap() {
        serviceMap.put("Mobile BFF Service", "https://svcs.us-west-2.prod.aws.nexttrucking.com/grafana/d/lL2b6U9ik/next-services?orgId=1&var-job=next-mobile&from=now-12h&to=now&kiosk=tv");
        serviceMap.put("Backend Service", "https://svcs.us-west-2.prod.aws.nexttrucking.com/grafana/d/lL2b6U9ik/next-services?orgId=1&from=now-12h&to=now&var-job=backend&kiosk=tv");
        serviceMap.put("Trips Service", "https://svcs.us-west-2.prod.aws.nexttrucking.com/grafana/d/lL2b6U9ik/next-services?orgId=1&from=now-12h&to=now&var-job=trips&kiosk=tv");
    }


    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(1000 * 10);
                    Message message = new Message();
                    handler.sendMessage(message);
                    return;
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
            mapIdx = mapIdx % serviceMap.size();
            title = (String) serviceMap.keySet().toArray()[mapIdx];
            url = serviceMap.get(title);
            mapIdx++;
            textView.setText(title);
            webView.loadUrl(url);
            Log.d("NEXT", title + " | " + url);
            super.handleMessage(msg);
        }
    };
}
