package com.example.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.web), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress", "WrongViewCast"}) WebView base = findViewById(R.id.web);
        setupWebView(base);
        loadbase(base);
    }
    private void setupWebView(WebView base) {
        WebSettings webSettings = base.getSettings();

        // Включаем JavaScript
        webSettings.setJavaScriptEnabled(true);

        webSettings.setDomStorageEnabled(true);


        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        // Кэширование
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        base.setWebViewClient(new WebViewClient());
    }
    private boolean isNetworkAvailable() {//тест инета
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
    private void loadbase(WebView base){
        if(isNetworkAvailable() == true){
            base.loadUrl("https://developer.android.com/studio"); //айпи
        }
        else {
            Intent intent = new Intent(this, Nointer.class);
            startActivity(intent);
        }
    }
}