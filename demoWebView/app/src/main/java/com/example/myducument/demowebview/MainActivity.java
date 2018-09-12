package com.example.myducument.demowebview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonGoBack;
    private Button buttonGo;
    private Button buttonReLoad;
    private EditText editURL;
    private WebView webView;
    private ProgressBar progressBar;
    private TextView textLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inIt();

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                textLoading.setVisibility(View.VISIBLE);
                textLoading.setText("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                textLoading.setVisibility(View.GONE);

            }

        });
        webView.loadUrl("http://oicsoft.com");
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                    editURL.setText(webView.getUrl());
                } else {
                    Toast.makeText(MainActivity.this, "Khong quay lai duoc !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonReLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = editURL.getText().toString();
                url=url.replaceAll("http://", "");
                webView.loadUrl("http://"+url);
                editURL.setText(webView.getUrl());

            }
        });



    }

    private void inIt() {
        buttonGo = findViewById(R.id.button_go);
        buttonGoBack = findViewById(R.id.button_goback);
        buttonReLoad = findViewById(R.id.button_reload);
        editURL = findViewById(R.id.edit_url);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressBar);
        textLoading = findViewById(R.id.text_loading);
    }

}

