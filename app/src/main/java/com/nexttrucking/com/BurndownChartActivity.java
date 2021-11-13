package com.nexttrucking.com;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.nexttrucking.com.helpers.WebViewHelper;

import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class BurndownChartActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burndown_chart);
        initViews();
        webView.loadUrl("https://nexttrucking.okta.com/login/login.htm?fromURI=%2Fapp%2Fgoogle%2Fexk5l99gyc5qMU9Ni357%2Fsso%2Fsaml%3FSAMLRequest%3DfVLLbsIwELxX6j9EvpMQSlVkkSAKQkWibQShh96M2QSDH9TrAP37mgAqPRTJp%252FHszszudnsHJYMdWBRGJyQOmyQAzc1S6DIh83zU6JBeen%252FXRabklvYrt9JT%252BKoAXeArNdL6IyGV1dQwFEg1U4DUcTrrv05oK2zSrTXOcCNJMB4mRGgo1xuA9YKZ9coIUAstmNwoWULBC6OYUCtxZH9cbLWOtsaIFYw1Oqadh5qtuBH795A327TdoY9PnyTIzkrPQp8S3LK1OJGQvuR51sjeZ3ndYCeWYN88OyGlMaWEkBt1lM8Yoth5uGASgQR9RLDOGxwYjZUCOwO7Exzm00lCVs5tkUbRfr8Pf9tELNJwcM5WfOOlTxBHktbzpXVEezXY2wHYxQBJb0p0o6vu6XmVx4TjYWak4N9BX0qzH1hgzsfzpT7dyFjF3P8G4jCuEbFsFDWVVhq3wEUhYEmCKD2p%252Fr0Zf0k%252F%26RelayState%3Dhttps%253A%252F%252Faccounts.google.com%252FCheckCookie%253Fcontinue%253Dhttps%25253A%25252F%25252Faccounts.google.com%25252Fo%25252Foauth2%25252Fauth%25253Flogin_hint%25253Dhector%25252540nexttrucking.com%252526scope%25253Dprofile%25252Bemail%252526response_type%25253Dcode%252526state%25253DeyJzb3VyY2UiOiJsb2dpblNjcmVlbiIsInVzZXJGbG93IjpudWxsLCJpc1NsYWNrQXBwU291cmNlIjpudWxsLCJxdWVyeSI6Ij9hcHBsaWNhdGlvbj1qaXJhJmNvbnRpbnVlPWh0dHBzOi8vbmV4dHRydWNraW5nLmF0bGFzc2lhbi5uZXQvbG9naW4%2525252FcmVkaXJlY3RDb3VudCUzRDElMjZhcHBsaWNhdGlvbiUzRGppcmEmZW1haWw9aGVjdG9yQG5leHR0cnVja2luZy5jb20mbG9naW5UeXBlPWdvb2dsZUJ1dHRvbiZsb2dpbl9oaW50PWhlY3RvckBuZXh0dHJ1Y2tpbmcuY29tJnByb21wdD1zZWxlY3RfYWNjb3VudCZzb3VyY2U9bG9naW5TY3JlZW4iLCJjc3JmVG9rZW4iOiIyODRhM2Q2NTM0MGQ2MGNlOTNmZjcxOTE1NmZiYjE5M2FlM2QxMDZiMGFiOTMwNjgwNWYyM2RhNjE3NWY0NzE0IiwibG9naW5UeXBlIjoiZ29vZ2xlQnV0dG9uIiwiYW5vbnltb3VzSWQiOiJiMTVkN2RjZC0zY2E3LTRiMjUtOWUwZi05YTMwM2QyZjE5MGQiLCJtYXJrZXRpbmdDb25zZW50IjpudWxsLCJleHBlcmltZW50IjpudWxsfQ%2525253D%2525253D%252526redirect_uri%25253Dhttps%2525253A%2525252F%2525252Fid.atlassian.com%2525252Flogin%2525252Fgoogle%252526code_challenge_method%25253DS256%252526prompt%25253Dselect_account%252526client_id%25253D596149463257-9oquqfivs9on8t8erq23c8qso6vk3cp1.apps.googleusercontent.com%252526code_challenge%25253D4NiAT-jkTPh0xcgOUGinaTSjgi3UdzLxXSUCcrmBnoI%252526hd%25253Dnexttrucking.com%252526as%25253DS436851531%2525253A1636778937487479%252526authuser%25253Dunknown%2526client_id%253D596149463257-9oquqfivs9on8t8erq23c8qso6vk3cp1.apps.googleusercontent.com");

        new Thread(new BurndownChartActivity.MyThread()).start();
    }

    private void initViews() {
        webView = findViewById(R.id.web);
        WebViewHelper.setCustomWebView(webView, this);
    }

    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
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

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            webView.loadUrl("https://nexttrucking.atlassian.net/plugins/servlet/Wallboard/?dashboardId=10137&cyclePeriod=2000&transitionFx=scrollLeft&random=false");
            super.handleMessage(msg);
        }

    };

}
