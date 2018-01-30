package com.example.prajwal.myexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "WebViewActivity";

    private Context mContext;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mContext = WebViewActivity.this;

        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to 'MainActivity'");
                Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String URLString = getIntent().getStringExtra("URL");
        Log.i("URL: ", URLString);

        webView = (WebView) findViewById(R.id.postWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URLString);
    }


}
