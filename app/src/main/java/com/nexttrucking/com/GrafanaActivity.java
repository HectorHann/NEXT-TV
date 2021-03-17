package com.nexttrucking.com;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class GrafanaActivity extends AppCompatActivity {
    private WebView webView;
    private String url = "https://svcs.us-west-2.prod.aws.nexttrucking.com/grafana/d/YHDY1YaMk/duration-and-error?orgId=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafana);
        initViews();

        loadUrls();
    }


    private void loadUrls() {
        webView.loadUrl(url);
    }


    private void initViews() {
        webView = findViewById(R.id.webview);

//        WebViewHelper.setCustomWebView(webView, this);


//        webView.setWebViewClient(new WebViewClient() {
//
//            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
//                handler.proceed(Accounts.USERNAME, Accounts.PASSWORD);
//            }
//        });


        initWebViewSettings(webView);


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
            }

        });
    }

}
