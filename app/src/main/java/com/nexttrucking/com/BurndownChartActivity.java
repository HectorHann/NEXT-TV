package com.nexttrucking.com;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class BurndownChartActivity extends AppCompatActivity {
    private WebView webView1, webView2, webView3, webView4;
    private TextView textView1, textView2, textView3, textView4;
    private String title1, title2, title3, title4;
    private String url1, url2, url3, url4;
    private String script;
    private Map<String, String> borndownMap = new HashMap<>();
    private int mapIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burndown_chart);
        initBornDownMap();
        initScript();
        initViews();
        loadBornDownUrl();

        new Thread(new BurndownChartActivity.MyThread()).start();
    }


    private void loadBornDownUrl() {
        loadBornDownUrl(webView1, textView1);
        loadBornDownUrl(webView2, textView2);
        loadBornDownUrl(webView3, textView3);
        loadBornDownUrl(webView4, textView4);
    }


    private void loadBornDownUrl(WebView webView, TextView textView) {
        mapIdx = mapIdx % borndownMap.size();
        String title = (String) borndownMap.keySet().toArray()[mapIdx];
        String url = borndownMap.get(title);
        mapIdx++;

        textView.setText(title);
        webView.loadUrl(url);
        Log.d("NEXT", title + " | " + url);
    }


    private void initBornDownMap() {
        borndownMap.put("Hotpot Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=76&projectKey=APD&view=reporting&chart=burndownChart");
        borndownMap.put("Spaceport Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=113&projectKey=APD&view=reporting&chart=burndownChart");
        borndownMap.put("Omega Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=112&projectKey=APD&view=reporting&chart=burndownChart");
        borndownMap.put("Quality Eng Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=91&projectKey=PLAT&view=reporting&chart=burndownChart");
        borndownMap.put("Marvel Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=114&projectKey=APD&view=reporting&chart=burndownChart");
    }

    private void initScript() {
        script = "javascript:(function() { " +
                "document.getElementsByClassName('ak-navigation-resize-button')[0].click(); " +
                "document.getElementById('ghx-chart-content').scrollIntoView(); " +
                "document.getElementById('ghx-chart-intro').remove(); " +
                "document.getElementById('ghx-header').remove(); " +
                "document.getElementById('navigation-app').remove(); " +
                "document.getElementById('ghx-report').style.height='auto'; " +
                "document.getElementsByClassName('legend')[0].remove(); " +
                "document.getElementById('ghx-chart-header').remove(); " +
                "})();";
        Log.d("NEXT", script);
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
    }

    private void initViews() {
        webView1 = findViewById(R.id.web_left1);
        webView2 = findViewById(R.id.web_right1);
        webView3 = findViewById(R.id.web_left2);
        webView4 = findViewById(R.id.web_right2);

        textView1 = findViewById(R.id.title_left1);
        textView2 = findViewById(R.id.title_right1);
        textView3 = findViewById(R.id.title_left2);
        textView4 = findViewById(R.id.title_right2);

        initWebViewSettings(webView1);
        initWebViewSettings(webView2);
        initWebViewSettings(webView3);
        initWebViewSettings(webView4);

        webView1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("burndownChart")) {
                    view.loadUrl(script);
                }

            }
        });
        webView2.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("burndownChart")) {
                    view.loadUrl(script);
                }
            }
        });
        webView3.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("burndownChart")) {
                    view.loadUrl(script);
                }
            }
        });
        webView4.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("burndownChart")) {
                    view.loadUrl(script);
                }
            }
        });

    }


    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Message message = new Message();
                    handler.sendMessage(message);
                    Thread.sleep(1000 * 60 * 10);
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
            loadBornDownUrl();
            super.handleMessage(msg);
        }

    };
}
