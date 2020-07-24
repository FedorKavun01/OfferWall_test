package com.example.offerwall_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TextFragment(val textNote: TextNote) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_text, container, false)
        var tv = view.findViewById<TextView>(R.id.textNoteView)
        tv.setText(textNote.getData())
        return view
    }
//
//    companion object {
//        @JvmStatic
//        fun newInstance() = TextFragment()
//    }
}