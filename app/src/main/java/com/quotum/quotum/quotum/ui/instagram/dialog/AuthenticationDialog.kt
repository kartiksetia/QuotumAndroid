package com.quotum.quotum.quotum.ui.instagram.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.NonNull
import com.quotum.quotum.quotum.R
import com.quotum.quotum.quotum.ui.instagram.AuthenticationListener

class AuthenticationDialog(@NonNull context: Context, private val listener: AuthenticationListener) : Dialog(context) {

    private val request_url: String
    private val redirect_url: String = context.resources.getString(R.string.redirect_url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.auth_dialog)
        initializeWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView() {
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(request_url)
        webView.webViewClient = webViewClient
    }

    var webViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            if (url.startsWith(redirect_url)) {
                dismiss()
                return true
            }
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (url.contains("access_token=")) {
                val uri = Uri.parse(url)
                var access_token = uri.encodedFragment
                access_token = access_token!!.substring(access_token.lastIndexOf("=") + 1)
                Log.e("access_token", access_token)
                listener.onTokenReceived(access_token)
                dismiss()
            } else if (url.contains("?error")) {
                Log.e("access_token", "getting error fetching access token")
                dismiss()
            }
        }
    }

    init {
        request_url = context.resources.getString(R.string.base_url) +
                "oauth/authorize/?client_id=" +
                context.resources.getString(R.string.client_id) +
                "&redirect_uri=" + redirect_url +
                "&response_type=token&display=touch&scope=public_content"
    }
}