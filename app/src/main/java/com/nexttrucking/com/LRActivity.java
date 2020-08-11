package com.nexttrucking.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LRActivity extends AppCompatActivity {

    private String url1 , url2;
    private WebView webView_left, webView_right;
    private String css, script;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lr);
        css =
                        "'"+
                                "#header {\n" +
                                "    display: none;\n" +
                                "}\n" +
                                "\n" +
                                "body {\n" +
                                "    background: #333c46;\n" +
                                "}\n" +
                                "\n" +
                                "#main {\n" +
                                "    margin-bottom: 0em;\n" +
                                "}\n" +
                                "\n" +
                                ".box {\n" +
                                "    box-shadow: none;\n" +
                                "    background: transparent;\n" +
                                "}\n" +
                                "\n" +
                                ".box .boxHeader {\n" +
                                "    display: none;\n" +
                                "}\n" +
                                "\n" +
                                ".footerLinks {\n" +
                                "    display: none;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard {\n" +
                                "    margin: 0;\n" +
                                "    margin-left: -5px;\n" +
                                "}\n" +
                                ".section-leaderboard .leaderboard table {\n" +
                                "    table-layout: initial;\n" +
                                "    color: #f69d2e\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr th {\n" +
                                "    background-color: transparent;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr:nth-child(1) .rank {\n" +
                                "    visibility: hidden;\n" +
                                "    font-size: 15px\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr .rank {\n" +
                                "    font-size: 20px;\n" +
                                "}\n" +
                                "\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr:nth-child(1) .user {\n" +
                                "    margin-left: -15px;\n" +
                                "    margin-bottom: 5px;\n" +
                                "    font-size: 20px;\n" +
                                "    font-weight: bold;\n" +
                                "}\n" +
                                "\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr:nth-child(1) .user .avatar {\n" +
                                "    width: 30px;\n" +
                                "    height: 30px;\n" +
                                "    border-radius: 10px;\n" +
                                "    border: solid 1px #ffffff;\n" +
                                "    margin-right: 10px;\n" +
                                "    -webkit-animation: neon-organge-box 1s ease-in-out infinite alternate;\n" +
                                "    -moz-animation: neon-organge-box 1s ease-in-out infinite alternate;\n" +
                                "    animation: neon-organge-box 1s ease-in-out infinite alternate;\n" +
                                "}\n" +
                                "\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr:nth-child(1) td {\n" +
                                "  color:#ffffff;\n" +
                                "  -webkit-animation: neon-organge 1s ease-in-out infinite alternate;\n" +
                                "  -moz-animation: neon-organge 1s ease-in-out infinite alternate;\n" +
                                "  animation: neon-organge 1s ease-in-out infinite alternate;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr th a {\n" +
                                "    color: #f69d2e;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr th.metric {\n" +
                                "    padding: 1em 0 1em 15px;\n" +
                                "    font-size: 15px;\n" +
                                "}\n" +
                                ".section-leaderboard .leaderboard table tr th:nth-child(2) {\n" +
                                "    padding: 1em 0 1em 15px;\n" +
                                "    font-size: 15px;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr td.metric {\n" +
                                "    font-size: 25px;\n" +
                                "    font-family:Iceland;\n" +
                                "    text-align: center!important;\n" +
                                "    padding: 0.0em 0;\n" +
                                "    max-width: 40px;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr td {\n" +
                                "    padding: 0em 0;\n" +
                                "    font-size: 15px;\n" +
                                "    font-family:Iceland;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr td.user {\n" +
                                "    margin-left: 10px;\n" +
                                "    padding: 0.55em 0;\n" +
                                "    font-size: 26px;\n" +
                                "    white-space: nowrap;\n" +
                                "    max-width: 220px;\n" +
                                "    text-align: left;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr td.rank {\n" +
                                "    padding-left: 0px;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr td.user .avatar {\n" +
                                "    width: 20px;\n" +
                                "    height: 20px;\n" +
                                "    margin-right: 0.3em;\n" +
                                "    border-radius: 10px;\n" +
                                "    white-space: nowrap;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr {\n" +
                                "    display: none;\n" +
                                "    border-bottom: none;\n" +
                                "    background: transparent;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard thead tr {\n" +
                                "    background: transparent!important;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr:nth-child(-n+5){\n" +
                                "    display: table-row;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr:nth-last-child(-n+3){\n" +
                                "    display: table-row;\n" +
                                "    background: #484848;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr th.metric:nth-last-child(1) {\n" +
                                "    padding-right: 2.5em;\n" +
                                "    margin-left: -5px;\n" +
                                "}\n" +
                                "\n" +
                                ".section-leaderboard .leaderboard table tr td.metric:last-child {\n" +
                                "    padding-right: 0.2em;\n" +
                                "}\n" +
                                "\n" +
                                "\n" +
                                "@keyframes neon-organge {\n" +
                                "  from {\n" +
                                "    text-shadow: 0 0 10px #fff,\n" +
                                "               0 0 20px  #fff,\n" +
                                "               0 0 30px  #fff,\n" +
                                "               0 0 40px  #f69d2e,\n" +
                                "               0 0 70px  #f69d2e,\n" +
                                "               0 0 80px  #f69d2e,\n" +
                                "               0 0 100px #f69d2e,\n" +
                                "               0 0 150px #f69d2e;\n" +
                                "  }\n" +
                                "  to {\n" +
                                "    text-shadow: 0 0 5px #fff,\n" +
                                "               0 0 10px #fff,\n" +
                                "               0 0 15px #fff,\n" +
                                "               0 0 20px #f69d2e,\n" +
                                "               0 0 35px #f69d2e,\n" +
                                "               0 0 40px #f69d2e,\n" +
                                "               0 0 50px #f69d2e,\n" +
                                "               0 0 75px #f69d2e;\n" +
                                "  }\n" +
                                "}\n" +
                                "\n" +
                                "@keyframes neon-organge-box {\n" +
                                "  from {\n" +
                                "    box-shadow: 0 0 10px #fff,\n" +
                                "               0 0 20px  #fff,\n" +
                                "               0 0 30px  #fff,\n" +
                                "               0 0 40px  #f69d2e,\n" +
                                "               0 0 70px  #f69d2e,\n" +
                                "               0 0 80px  #f69d2e,\n" +
                                "               0 0 100px #f69d2e,\n" +
                                "               0 0 150px #f69d2e;\n" +
                                "  }\n" +
                                "  to {\n" +
                                "    box-shadow: 0 0 5px #fff,\n" +
                                "               0 0 10px #fff,\n" +
                                "               0 0 15px #fff,\n" +
                                "               0 0 20px #f69d2e,\n" +
                                "               0 0 35px #f69d2e,\n" +
                                "               0 0 40px #f69d2e,\n" +
                                "               0 0 50px #f69d2e,\n" +
                                "               0 0 75px #f69d2e;\n" +
                                "  }\n" +
                                "}"+

                        "'; ";


         script = "javascript:(function() { " +
                "var parent = document.getElementsByTagName('head').item(0); " +
                "var style = document.createElement('style'); " +
                "style.type = 'text/css'; " +
                "style.innerHTML = " +css+
                "var header = document.getElementById('header'); "+
                "document.getElementsByTagName('th')[2].innerText = 'PRs'; "+
                "document.getElementsByTagName('th')[3].innerText = 'CMs'; "+
                "document.getElementsByTagName('th')[4].innerText = 'Rate'; "+
                "header.remove(); "+
                "parent.appendChild(style); " +
                "})();";
        url1 = getIntent().getStringExtra("url1");
        url2 = getIntent().getStringExtra("url2");
        webView_left = findViewById(R.id.web_left);
        webView_right = findViewById(R.id.web_right);

        webView_left.getSettings().setJavaScriptEnabled(true);
        webView_left.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView_left.getSettings().setSupportMultipleWindows(true);
        webView_left.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(script);
            }
        });
        webView_left.loadUrl(url1);

        webView_right.getSettings().setJavaScriptEnabled(true);
        webView_right.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView_right.getSettings().setSupportMultipleWindows(true);
        webView_right.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(script);
            }
        });
        webView_right.loadUrl(url2);

        new Thread(new MyThread()).start();

    }


    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(1000*60*5);
                    Message message = new Message();
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            webView_left.loadUrl(url1);
            webView_right.loadUrl(url2);
            super.handleMessage(msg);
        }
    };

}
