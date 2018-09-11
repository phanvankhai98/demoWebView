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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

        webView.setWebViewClient(new WebViewClient());
        progressBar.setVisibility(View.GONE);
        buttonGo.setOnClickListener(this);
        buttonGoBack.setOnClickListener(this);
        buttonReLoad.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        AppWebViewClients appWebViewClients = new AppWebViewClients(progressBar);
        if (v.getId() == R.id.button_go) {
            if (editURL.getText().toString() != "") {
                String url = editURL.getText().toString();
                url.replaceAll("http://", "");

                appWebViewClients.shouldOverrideUrlLoading(webView,"http://"+url);
                editURL.setText(webView.getUrl());
                //appWebViewClients.onPageFinished(webView,"http://"+url);


            }
            if (v.getId() == R.id.button_goback) {
                if (webView.canGoBack()) {
                    webView.goBack();
                    editURL.setText(webView.getUrl());
                } else {
                    Toast.makeText(MainActivity.this, "Khong quay lai duoc !!", Toast.LENGTH_SHORT).show();
                }
            }
            if (v.getId() == R.id.button_reload) {
                webView.reload();
            }

        }
    }

    class AppWebViewClients extends WebViewClient {
        private  ProgressBar progressBar;
        public AppWebViewClients(ProgressBar progressBar) {
            this.progressBar = progressBar;
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}

