package com.example.rusalfood.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rusalfood.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, SignInFragment.newInstance())
                .commitNow()
        }*/
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}