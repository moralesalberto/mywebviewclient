package com.example.amorales.mywebviewapp;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by amorales on 9/9/17.
 */

public class MyWebViewClient extends WebViewClient {

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
    public void onPageFinished(WebView view, String url) {

        super.onPageFinished(view, url);

        Context context = view.getContext();

        // hold the javascript code here
        String jsCode = "";

        try {
            jsCode = readFromAssets(context, "main.js");
        } catch (IOException ie) {
            System.exit(1);
        }

        // run javascript
        view.evaluateJavascript(jsCode, null);
    }
}
