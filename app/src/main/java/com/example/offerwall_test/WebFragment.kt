package com.example.offerwall_test

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

class WebFragment(val webNote: WebNote) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_web, container, false)
        var webView = view.findViewById<WebView>(R.id.webNoteView)
        webView.loadUrl(Uri.parse(webNote.getData()).toString())
        return view
    }

//    companion object {
//        @JvmStatic
//        fun newInstance() =
//            WebFragment()
//    }
}