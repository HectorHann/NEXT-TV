package com.nexttrucking.com.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Method;

public class WebViewHelper {

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void setCustomWebView(WebView webView, Context context) {

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


        WebSettings ws = webView.getSettings();
        try {
            Method m1 = WebSettings.class.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
            m1.invoke(ws, Boolean.TRUE);

            Method m2 = WebSettings.class.getMethod("setDatabaseEnabled", new Class[]{Boolean.TYPE});
            m2.invoke(ws, Boolean.TRUE);

            Method m3 = WebSettings.class.getMethod("setDatabasePath", new Class[]{String.class});
            m3.invoke(ws, "/data/data/" + context.getPackageName() + "/databases/");

            Method m4 = WebSettings.class.getMethod("setAppCacheMaxSize", new Class[]{Long.TYPE});
            m4.invoke(ws, 1024 * 1024 * 8);

            Method m5 = WebSettings.class.getMethod("setAppCachePath", new Class[]{String.class});
            m5.invoke(ws, "/data/data/" + context.getPackageName() + "/cache/");

            Method m6 = WebSettings.class.getMethod("setAppCacheEnabled", new Class[]{Boolean.TYPE});
            m6.invoke(ws, Boolean.TRUE);

            WebSettings.class.getMethod("setLightTouchEnabled", new Class[]{Boolean.TYPE});
        } catch (Exception e) {
            Log.e(WebViewHelper.class.getSimpleName(), "Reflection fail", e);
        }

    }
}