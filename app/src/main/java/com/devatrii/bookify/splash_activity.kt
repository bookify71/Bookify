package com.devatrii.bookify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splash_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed(
            {
                val intent = Intent(this, sign_in::class.java)
                startActivity(intent)
                finish()
            },
            3000
        )
    }
}