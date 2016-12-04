package com.spiritual.android.navigationdrawerexample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class OpenWebView extends Activity {
    private WebView webView;
  //  public Button back = (Button) findViewById(R.id.back_button);
  //  public Button forward = (Button) findViewById(R.id.forward_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_web_view);
        Button back = (Button) findViewById(R.id.back_button);
        Button forward = (Button) findViewById(R.id.forward_button);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String url=bundle.getString("url");

        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        back.setVisibility(View.VISIBLE);
        forward.setVisibility(View.VISIBLE);
    }

    public void onBack(View v) {
        if (webView.canGoBack()) {
            webView.goBack();
            //App crashed when I tried to make the buttons public variables here
            Button back = (Button) findViewById(R.id.back_button);
            Button forward = (Button) findViewById(R.id.forward_button);
            /*if(!webView.canGoBack()){
                back.setVisibility(View.INVISIBLE);
            }
            if(forward.getVisibility() == View.INVISIBLE){
                forward.setVisibility(View.VISIBLE);
            }
*/
        } else {
    //something

        }
    }
    public void onForward(View v) {
        if (webView.canGoForward()) {
            webView.goForward();
            Button back = (Button) findViewById(R.id.back_button);
            Button forward = (Button) findViewById(R.id.forward_button);
            /*if(!webView.canGoForward()){
                forward.setVisibility(View.INVISIBLE);
            }
            if(back.getVisibility() == View.INVISIBLE){
                back.setVisibility(View.VISIBLE);
            }
            */
        } else {
            //Button forwardbutton = (Button) findViewById(R.id.forward_button);
        }
    }


    //navigate back to the previous page instead of jump to the first app page
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Button back = (Button) findViewById(R.id.back_button);
        Button forward = (Button) findViewById(R.id.forward_button);
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //this.webView.goBack();
            back.setVisibility(View.INVISIBLE);
            forward.setVisibility(View.INVISIBLE);
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
