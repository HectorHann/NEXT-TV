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

import com.nexttrucking.com.helpers.WebViewHelper;

import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class BurndownChartActivity extends AppCompatActivity {
    private WebView webView1, webView2, webView3, webView4;
    private TextView textView1, textView2, textView3, textView4;
    private String script;
    private Map<String, String> dataMap = new HashMap<>();
    private int mapIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burndown_chart);
        initViews();
        initDataMap();
        initScript();

        loadUrls();

        new Thread(new BurndownChartActivity.MyThread()).start();
    }


    private void initDataMap() {
        dataMap.put("Hotpot Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=76&projectKey=APD&view=reporting&chart=burndownChart");
        dataMap.put("Spaceport Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=113&projectKey=APD&view=reporting&chart=burndownChart");
        dataMap.put("Omega Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=112&projectKey=APD&view=reporting&chart=burndownChart");
        dataMap.put("Quality Eng Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=91&projectKey=PLAT&view=reporting&chart=burndownChart");
        dataMap.put("Marvel Team", "https://nexttrucking.atlassian.net/secure/RapidBoard.jspa?rapidView=114&projectKey=APD&view=reporting&chart=burndownChart");
    }


    private void loadUrls() {
        loadTitleAndUrl(webView1, textView1);
        loadTitleAndUrl(webView2, textView2);
        loadTitleAndUrl(webView3, textView3);
        loadTitleAndUrl(webView4, textView4);
    }


    private void loadTitleAndUrl(WebView webView, TextView textView) {
        mapIdx = mapIdx % dataMap.size();
        String title = (String) dataMap.keySet().toArray()[mapIdx];
        String url = dataMap.get(title);
        mapIdx++;

        textView.setText(title);
        webView.loadUrl(url);
        Log.d("NEXT", title + " | " + url);
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


    private void initViews() {
        webView1 = findViewById(R.id.web_left1);
        webView2 = findViewById(R.id.web_right1);
        webView3 = findViewById(R.id.web_left2);
        webView4 = findViewById(R.id.web_right2);
//        webView2.setVisibility(View.GONE);
//        webView3.setVisibility(View.GONE);
//        webView4.setVisibility(View.GONE);

        textView1 = findViewById(R.id.title_left1);
        textView2 = findViewById(R.id.title_right1);
        textView3 = findViewById(R.id.title_left2);
        textView4 = findViewById(R.id.title_right2);

        WebViewHelper.setCustomWebView(webView1, this);
        WebViewHelper.setCustomWebView(webView2, this);
        WebViewHelper.setCustomWebView(webView3, this);
        WebViewHelper.setCustomWebView(webView4, this);


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
            loadUrls();
            super.handleMessage(msg);
        }

    };
}
