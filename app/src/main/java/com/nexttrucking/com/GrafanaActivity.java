package com.nexttrucking.com;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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
        webView = findViewById(R.id.webview);

        initWebViewSettings(webView);

        new Thread(new GrafanaActivity.MyThread()).start();
    }


    private void initWebViewSettings(WebView webView) {
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed("next", "7u4#3KTVUejFd00t");
//                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }
        });
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
