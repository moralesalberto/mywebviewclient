package com.example.amorales.mywebviewapp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by amorales on 9/9/17.
 */

public class MyWebViewClient extends WebViewClient {

    @Override
    public void onPageFinished(WebView view, String url) {

        super.onPageFinished(view, url);

        // run javascript
        view.evaluateJavascript("console.log(document.location.href);", null);
    }
}
