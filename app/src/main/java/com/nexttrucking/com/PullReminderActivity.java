package com.nexttrucking.com;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.nexttrucking.com.helpers.WebViewHelper;

import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class PullReminderActivity extends AppCompatActivity {
    private WebView webView_left, webView_right;
    private String script;
    private Map<String, String> dataMap = new HashMap<>();
    private int mapIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_reminder);
        initViews();
        initDataMap();
        initScript();

        loadUrls();

        new Thread(new PullReminderActivity.MyThread()).start();
    }


    private void initDataMap() {

        dataMap.put("URL1", "https://pullreminders.com/installs/15793071/leaderboard?d=14d&s=prs&t%5B%5D=182191978&v=reviews");
        dataMap.put("URL2", "https://pullreminders.com/installs/15793071/leaderboard?d=14d&s=comments&t%5B%5D=182191978&v=reviews");
    }


    private void loadUrls() {
        loadTitleAndUrl(webView_left, dataMap.get("URL1"));
        loadTitleAndUrl(webView_right, dataMap.get("URL2"));
    }


    private void loadTitleAndUrl(WebView webView, String url) {
        webView.loadUrl(url);
        Log.d("NEXT", url);
    }


    private void initScript() {
        String css =
                "'" +
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
                        "    font-size: 25px;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ".section-leaderboard .leaderboard table tr:nth-child(1) .user {\n" +
                        "    margin-left: -25px;\n" +
                        "    font-size: 40px;\n" +
                        "    font-weight: bold;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ".section-leaderboard .leaderboard table tr:nth-child(1) .user .avatar {\n" +
                        "    width: 46px;\n" +
                        "    height: 46px;\n" +
                        "    border-radius: 23px;\n" +
                        "    border: solid 1px #ffffff;\n" +
                        "    margin-right: 15px;\n" +
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
                        "    padding: 0.285em 0 0.1em 25px;\n" +
                        "    font-size: 13px;\n" +
                        "}\n" +
                        ".section-leaderboard .leaderboard table tr th:nth-child(2) {\n" +
                        "    padding: 0.1em 0 0.1em 25px;\n" +
                        "    font-size: 15px;\n" +
                        "}\n" +
                        "\n" +
                        ".section-leaderboard .leaderboard table tr td.metric {\n" +
                        "    font-size: 35px;\n" +
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
                        "    padding: 0.45em 0;\n" +
                        "    font-size: 30px;\n" +
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
                        "    width: 32px;\n" +
                        "    height: 32px;\n" +
                        "    margin-right: 0.3em;\n" +
                        "    border-radius: 16px;\n" +
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
                        "    margin-left: -40px;\n" +
                        "    padding-right: 1.5em;\n" +
                        "    margin-left: 0px;\n" +
                        "}\n" +
                        "\n" +
                        ".section-leaderboard .leaderboard table tr td.metric:last-child {\n" +
                        "    padding-right: 0.2em;\n" +
                        "    margin-left: -40px;\n" +
                        "    padding-right: 0em;\n" +
                        "    margin-left: 0px;\n" +
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
                        "}" +

                        "'; ";


        script = "javascript:(function() { " +
                "var parent = document.getElementsByTagName('head').item(0); " +
                "var style = document.createElement('style'); " +
                "style.type = 'text/css'; " +
                "style.innerHTML = " + css +
                "document.getElementsByTagName('th')[2].innerText = 'PRs'; " +
                "document.getElementsByTagName('th')[3].innerText = 'CMs'; " +
                "document.getElementsByTagName('th')[4].innerText = 'Rate'; " +


                "const users = document.getElementById('main').getElementsByClassName('user');\n" +
                "const nameMap = {\n" +
                "  'HectorHann':'Hector',\n" +
                "  'helloworld-Andrew':'Andrew',\n" +
                "  'phuud':'Luke',\n" +
                "  'richard-li-next':'Richard',\n" +
                "  'kaylazl':'Kayla',\n" +
                "  'MikeNextTrucking':'Mike',\n" +
                "  'BarryLiii':'Barry',\n" +
                "  'Michael-crazyman':'Michael',\n" +
                "  'cj768025549':'Tiny',\n" +
                "  'WinterGou':'Winter',\n" +
                "  'visonalhal':'Halvin',\n" +
                "  'Gordon-Xu':'Gordon',\n" +
                "  'Rey-Wang':'Rey',\n" +
                "  'huqiaohua':'Alice',\n" +
                "  'Norman-else':'Norman',\n" +
                "  'samsuse':'Sam',\n" +
                "  'amyyangyang':'Amy',\n" +
                "  'Alvin-MXK':'Max',\n" +
                "  'billyhanyulong':'Billy',\n" +
                "  'RyanZheng1':'Ryan',\n" +
                "  'vincent-du-dev':'Vincent',\n" +
                "  'IlluYing':'Illu',\n" +
                "  'JasonNoob':'Jason',\n" +
                "  'lipper-li':'Lipper',\n" +
                "  'Noodles-Wang':'Noodles'\n" +
                "};\n" +
                "for(let i=0;i<users.length;i++){\n" +
                "  const name = users[i].innerText;\n" +
                "  if(nameMap[name]) {\n" +
                "    users[i].innerHTML = users[i].innerHTML.replace(name, nameMap[name]);\n" +
                "  }\n" +
                "}" +

                "\n" +

                "parent.appendChild(style); " +


                "})();";
        Log.d("NEXT", script);
    }


    private void initViews() {
        webView_left = findViewById(R.id.web_left);
        webView_right = findViewById(R.id.web_right);

        WebViewHelper.setCustomWebView(webView_left, this);
        WebViewHelper.setCustomWebView(webView_right, this);


        webView_left.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(script);
            }
        });
        webView_right.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(script);
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
