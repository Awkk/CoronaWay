package com.awisme.coronaway;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

//import android.support.annotation.NonNull;

public class YoutubeViewHolder extends RecyclerView.ViewHolder {
    WebView webView;
    Button button;

    @SuppressLint("SetJavaScriptEnabled")
    public YoutubeViewHolder(View itemView) {
        super(itemView);
        webView = itemView.findViewById(R.id.video_view);
        button = itemView.findViewById(R.id.fullscreen);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);


    }


}
