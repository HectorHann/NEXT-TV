package com.nexttrucking.com;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.nexttrucking.com.helpers.WebViewHelper;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class BurndownChartActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burndown_chart);
        initViews();
        loadUrls();
    }

    private void loadUrls() {
        webView.loadUrl("https://nexttrucking.atlassian.net/plugins/servlet/Wallboard/?dashboardId=10137&cyclePeriod=2000&transitionFx=scrollLeft&random=false");
    }

    private void initViews() {
        webView = findViewById(R.id.web);
        WebViewHelper.setCustomWebView(webView, this);
    }

}
