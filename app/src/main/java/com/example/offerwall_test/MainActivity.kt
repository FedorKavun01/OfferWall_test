package com.example.offerwall_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var button: Button
    lateinit var noteViewModel: NoteViewModel

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = NoteViewModel()
        noteViewModel.getStarted()
        button = findViewById(R.id.nextBtn)
        button.setOnClickListener(this)
        showFragment()

    }

    override fun onClick(v: View?) {
        showFragment()
    }

    fun showFragment(): Unit {
        var fragment: Fragment? = null
        runBlocking {
            fragment = noteViewModel.fetchFragment()
        }
        if (fragment == null) {
            Toast.makeText(this, "Fragment is null", Toast.LENGTH_SHORT).show()
        } else {
            var fTrans = supportFragmentManager.beginTransaction()
            fTrans.replace(R.id.frameLayout, fragment!!)
            fTrans.commit()
        }
    }
}