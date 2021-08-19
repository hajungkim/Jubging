package com.example.jubging.webapp

import android.app.ActivityManager
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MyWebChromeClient(val activity: AppCompatActivity): WebChromeClient() {

    interface GotoUrl {
        fun goToUrl()
    }

    var mFilePathCallback: ValueCallback<Array<Uri>>? = null

    interface IImageHandler {
        fun showDialog(callback: ValueCallback<Array<Uri>>?)

        fun uploadImageOnPage(resultCode: Int, intent: Intent?)
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {

        if (newProgress == 100) {
            val manager = activity.baseContext.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager

            for (service in manager.getRunningServices(Int.MAX_VALUE)) {
                if ("com.example.jubging.MyService" == service.service.className) {
                    Log.d("여기다여기", service.started.toString())
                    if (service.started) {
                        if (activity is GotoUrl) {
                            activity.goToUrl()
                        }
                    }
                }
            }
        }

        super.onProgressChanged(view, newProgress)
    }

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {

        mFilePathCallback?.onReceiveValue(null)
        mFilePathCallback = null

        mFilePathCallback = filePathCallback

        if (activity is IImageHandler) {
            activity.showDialog(mFilePathCallback)
        }

        mFilePathCallback = null
        return true
    }
}