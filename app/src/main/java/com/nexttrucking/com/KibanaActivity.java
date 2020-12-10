package com.nexttrucking.com;

import android.annotation.SuppressLint;
import android.content.Intent;
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
public class KibanaActivity extends AppCompatActivity {

    private WebView webView;
    private TextView textView;
    private String title;
    private String url;
    private String script, css;
    private Map<String, String> serviceMap = new HashMap<>();
    private Object[] serviceArray;
    private int mapIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kibana);
        initServiceMap();
        textView = findViewById(R.id.title);
        webView = findViewById(R.id.webview);


        css = "'" +
                ".app-wrapper{\n" +
                "    left:0!important;\n" +
                "}\n" +
                "kbn-top-nav{\n" +
                "    display:none;\n" +
                "}\n" +
                "filter-bar{\n" +
                "    display:none;\n" +
                "}\n" +
                ".sidebar-container{\n" +
                "    display:none;\n" +
                "}\n" +
                ".dscWrapper{\n" +
                "    width:100%;\n" +
                "}" +
                "'; ";

        script = "javascript:(function() { " +
                "var parent = document.getElementsByTagName('head').item(0); " +
                "var style = document.createElement('style'); " +
                "style.type = 'text/css'; " +
                "style.innerHTML = " + css +
                "parent.appendChild(style); " +
                "})();";
        Log.d("NEXT", script);


        initWebViewSettings(webView);

        new Thread(new MyThread()).start();
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

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(script);
            }
        });
    }

    private void initServiceMap() {
        serviceMap.put("Hotpot Team", "https://svcs.us-west-2.prod.aws.nexttrucking.com/_plugin/kibana/app/kibana#/discover/609d0ae0-2a5a-11eb-8e46-594f90f59027?_g=(refreshInterval:(pause:!t,value:0),time:(from:now-24h,mode:quick,to:now))&_a=(columns:!(message,user-id),filters:!(('$state':(store:appState),meta:(alias:!n,disabled:!f,index:c4ffd690-ad50-11e8-9bbd-fb4ac8098167,key:kubernetes.labels.app,negate:!f,params:!(next-mobile-service),type:phrases,value:next-mobile-service),query:(bool:(minimum_should_match:1,should:!((match_phrase:(kubernetes.labels.app:next-mobile-service)))))),('$state':(store:appState),meta:(alias:!n,disabled:!f,index:c4ffd690-ad50-11e8-9bbd-fb4ac8098167,key:level,negate:!f,params:!(ERROR),type:phrases,value:ERROR),query:(bool:(minimum_should_match:1,should:!((match_phrase:(level:ERROR))))))),index:'41d4a010-ad51-11e8-88e0-e9e04547326b',interval:auto,query:(language:kuery,query:''),sort:!('@timestamp',desc))");
        serviceMap.put("Spaceport Team", "https://svcs.us-west-2.prod.aws.nexttrucking.com/_plugin/kibana/app/kibana#/discover/68199670-2a5b-11eb-9680-331e4589543b?_g=(refreshInterval:(pause:!f,value:300000),time:(from:now-24h,mode:quick,to:now))&_a=(columns:!(message,kubernetes.labels.app),filters:!(('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'41d4a010-ad51-11e8-88e0-e9e04547326b',key:level,negate:!f,params:!(ERROR),type:phrases,value:ERROR),query:(bool:(minimum_should_match:1,should:!((match_phrase:(level:ERROR)))))),('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'41d4a010-ad51-11e8-88e0-e9e04547326b',key:kubernetes.labels.app,negate:!f,params:!(accounting-service,yard-service,location-service,dashboard-service,accounting-bff-service,rmis-integration-service),type:phrases,value:'accounting-service,%20yard-service,%20location-service,%20dashboard-service,%20accounting-bff-service,%20rmis-integration-service'),query:(bool:(minimum_should_match:1,should:!((match_phrase:(kubernetes.labels.app:accounting-service)),(match_phrase:(kubernetes.labels.app:yard-service)),(match_phrase:(kubernetes.labels.app:location-service)),(match_phrase:(kubernetes.labels.app:dashboard-service)),(match_phrase:(kubernetes.labels.app:accounting-bff-service)),(match_phrase:(kubernetes.labels.app:rmis-integration-service))))))),index:'41d4a010-ad51-11e8-88e0-e9e04547326b',interval:auto,query:(language:lucene,query:''),sort:!('@timestamp',desc))");
        serviceMap.put("Omega Team", "https://svcs.us-west-2.prod.aws.nexttrucking.com/_plugin/kibana/app/kibana#/discover/f7886e80-2b00-11eb-9680-331e4589543b?_g=(refreshInterval:(pause:!f,value:300000),time:(from:now-24h,mode:quick,to:now))&_a=(columns:!(message,kubernetes.labels.app),filters:!(('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'41d4a010-ad51-11e8-88e0-e9e04547326b',key:level,negate:!f,params:(query:ERROR,type:phrase),type:phrase,value:ERROR),query:(match:(level:(query:ERROR,type:phrase)))),('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'41d4a010-ad51-11e8-88e0-e9e04547326b',key:kubernetes.container.name,negate:!f,params:!(backend-service,ap-service,comms-service2,comms-service,eros-service,mail-service,text-message-service,crawler-service),type:phrases,value:'backend-service,%20ap-service,%20comms-service2,%20comms-service,%20eros-service,%20mail-service,%20text-message-service,%20crawler-service'),query:(bool:(minimum_should_match:1,should:!((match_phrase:(kubernetes.container.name:backend-service)),(match_phrase:(kubernetes.container.name:ap-service)),(match_phrase:(kubernetes.container.name:comms-service2)),(match_phrase:(kubernetes.container.name:comms-service)),(match_phrase:(kubernetes.container.name:eros-service)),(match_phrase:(kubernetes.container.name:mail-service)),(match_phrase:(kubernetes.container.name:text-message-service)),(match_phrase:(kubernetes.container.name:crawler-service))))))),index:'41d4a010-ad51-11e8-88e0-e9e04547326b',interval:auto,query:(language:lucene,query:''),sort:!('@timestamp',desc))");
        serviceMap.put("Marvel Team", "https://svcs.us-west-2.prod.aws.nexttrucking.com/_plugin/kibana/app/kibana#/discover/384d3450-2eee-11eb-9680-331e4589543b?_g=(refreshInterval:(pause:!f,value:300000),time:(from:now-24h,mode:quick,to:now))&_a=(columns:!(message,kubernetes.container.name),filters:!(('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'41d4a010-ad51-11e8-88e0-e9e04547326b',key:kubernetes.container.name,negate:!f,params:!(trips-service,rule-engine-service,equipment-service,accessorial-event-service),type:phrases,value:'trips-service,%20rule-engine-service,%20equipment-service,%20accessorial-event-service'),query:(bool:(minimum_should_match:1,should:!((match_phrase:(kubernetes.container.name:trips-service)),(match_phrase:(kubernetes.container.name:rule-engine-service)),(match_phrase:(kubernetes.container.name:equipment-service)),(match_phrase:(kubernetes.container.name:accessorial-event-service)))))),('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'41d4a010-ad51-11e8-88e0-e9e04547326b',key:level,negate:!f,params:(query:ERROR,type:phrase),type:phrase,value:ERROR),query:(match:(level:(query:ERROR,type:phrase))))),index:'41d4a010-ad51-11e8-88e0-e9e04547326b',interval:auto,query:(language:lucene,query:''),sort:!('@timestamp',desc))");

        serviceArray = serviceMap.keySet().toArray();
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

            if (mapIdx >= serviceMap.size()){
                Intent intent = new Intent(KibanaActivity.this, PullReminderActivity.class);
                startActivity(intent);
                KibanaActivity.this.finish();
            }
            mapIdx = mapIdx % serviceMap.size();
            title = (String) serviceArray[mapIdx];
            url = serviceMap.get(title);
            mapIdx++;
            textView.setText(title);
            webView.loadUrl(url);
            Log.d("NEXT", title + " | " + url);
            super.handleMessage(msg);
        }
    };
}
