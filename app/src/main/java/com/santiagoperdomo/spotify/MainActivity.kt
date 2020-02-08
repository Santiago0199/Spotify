package com.santiagoperdomo.spotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.santiagoperdomo.spotify.root.App

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component.inject(this)
    }
}
