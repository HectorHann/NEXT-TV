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
public class SonarActivity extends AppCompatActivity {
    private WebView webView;
    private TextView textView;
    private String script;
    private Map<String, String> dataMap = new HashMap<>();
    private int mapIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonar);
        initViews();
        initDataMap();
        initScript();

        loadUrls();

        new Thread(new SonarActivity.MyThread()).start();
    }


    private void initDataMap() {
        dataMap.put("Hotpot Team", "https://sonar.tools.aws.nexttrucking.com/projects?sort=-analysis_date&tags=hotpot");
        dataMap.put("Spaceport Team", "https://sonar.tools.aws.nexttrucking.com/projects?sort=-analysis_date&tags=spaceport");
        dataMap.put("Omega Team", "https://sonar.tools.aws.nexttrucking.com/projects?sort=-analysis_date&tags=omega");
        dataMap.put("Marvel Team", "https://sonar.tools.aws.nexttrucking.com/projects?sort=-analysis_date&tags=marvel");
    }


    private void loadUrls() {
        loadTitleAndUrl(webView, textView);
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
        String css = "'" +
                ".boxed-group-inner{\n" +
                "    padding: 0 15px 10px 0;\n" +
                "}\n" +
                ".project-card{\n" +
                "    height:auto!important;\n" +
                "    min-height: auto;\n" +
                "}\n" +
                ".boxed-group-header{\n" +
                "    padding-top: 8px;\n" +
                "}\n" +
                ".projects-list .ReactVirtualized__Grid__innerScrollContainer{\n" +
                "    width: 94% !important;\n" +
                "}\n" +
                ".projects-list .ReactVirtualized__Grid__innerScrollContainer div:nth-child(2) .project-card{\n" +
                "    margin-top: -50px;\n" +
                "}\n" +
                ".projects-list .ReactVirtualized__Grid__innerScrollContainer div:nth-child(3) .project-card{\n" +
                "    margin-top: -100px;\n" +
                "}\n" +
                ".projects-list .ReactVirtualized__Grid__innerScrollContainer div:nth-child(4) .project-card{\n" +
                "    margin-top: -150px;\n" +
                "}\n" +
                ".project-card-measure:nth-child(1){\n" +
                "    padding-left: 30px;\n" +
                "}\n" +
                ".project-card-measure:nth-child(4){\n" +
                "    padding-left: 7px\n" +
                "}\n" +
                ".project-card-measure-number{\n" +
                "    font-size:30px;\n" +
                "}\n" +
                ".rating{\n" +
                "    position: relative;\n" +
                "    top: -5px;\n" +
                "}\n" +
                ".project-card-dates{\n" +
                "    margin-top: -2px;\n" +
                "    margin-bottom: -5px;\n" +
                "}" +
                "'; ";

        script = "javascript:(function() { " +
                "var parent = document.getElementsByTagName('head').item(0); " +
                "var style = document.createElement('style'); " +
                "style.type = 'text/css'; " +
                "style.innerHTML = " + css +
                "document.getElementsByClassName('layout-page-side-outer')[0].remove(); " +
                "document.getElementById('global-navigation').remove(); " +
                "parent.appendChild(style); " +
                "})();";

        Log.d("NEXT", script);
    }


    private void initViews() {
        textView = findViewById(R.id.title);
        webView = findViewById(R.id.webview);

        WebViewHelper.setCustomWebView(webView, this);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("analysis_date")) {
                    webView.loadUrl(script);
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
                    Thread.sleep(1000 * 60 * 5);
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
