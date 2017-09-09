package com.example.amorales.mywebviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.content.Context;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    public String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine); // process line
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this.getApplicationContext();

        // hold the javascript code here
        String jsCode = "";

        try {
            jsCode = readFromAssets(context, "main.js");
        } catch (IOException ie) {
            System.exit(1);
        }

        // adding the webview
        mWebView = (WebView) findViewById(R.id.activity_main_webview);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Log.d("READING_FROM_FILE", jsCode);

        mWebView.setWebViewClient(new MyWebViewClient());

        // load page
        mWebView.loadUrl("http://www.bublup.com/");

    }

}
