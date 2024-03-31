package com.devatrii.bookify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import soup.neumorphism.NeumorphButton

class sign_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
        val user=findViewById<NeumorphButton>(R.id.signup)
        val login=findViewById<NeumorphButton>(R.id.login);
        user.setOnClickListener(){
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }
        login.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}