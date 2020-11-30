package com.fortuneloan.tenmileslotus.view.activitys

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.blankj.utilcode.util.SPUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_web
    }

    override fun initView(savedInstanceState: Bundle?) {
        SPUtils.getInstance().put("isWeb", true)
        iv_back.visibility = View.VISIBLE
        iv_back.setOnClickListener { finish() }
        val noTitle = intent.getBooleanExtra("notitle", false)
        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")

        //设置禁止浏览器打开
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        if (!noTitle) {
            tv_titles.text = title
        }
        webview.loadUrl(url!!)
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                // 顶部加载进度条
                progressBar!!.progress = progress
                if (progress == 100) {
                    progressBar!!.visibility = View.INVISIBLE
                }
                super.onProgressChanged(view, progress)
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                if (noTitle) {
                    tv_titles!!.text = title
                }
            }
        }
        val webSettings = webview.settings
        webSettings.javaScriptEnabled = true
        //支持缩放
        webSettings.setSupportZoom(true)
        //支持播放视频
        webSettings.domStorageEnabled = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.defaultTextEncodingName = "utf-8"
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        // 设置允许JS弹窗
        webSettings.javaScriptCanOpenWindowsAutomatically = true
    }

    override fun initListener() {}
    override fun initData() {}
}