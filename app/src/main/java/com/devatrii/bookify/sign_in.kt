package com.devatrii.bookify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import soup.neumorphism.NeumorphButton

class sign_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
        val user=findViewById<NeumorphButton>(R.id.signup)
        val login=findViewById<NeumorphButton>(R.id.login);
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        user.setOnClickListener(){
            val intent = Intent(applicationContext, signup::class.java)
            startActivity(intent)
        }
        login.setOnClickListener(){
            val helper = DbHelper(applicationContext)
            if (email.text.toString().isEmpty()) {
                email.error = "Email is compulsory"
                return@setOnClickListener
            }
            if (password.text.toString().isEmpty()) {
                password.error = "Password is compulsory"
                return@setOnClickListener
            }
            //loading.startLoading()
            val model = helper.fetchUserInfo()
            for (userInfo in model) {
                Log.d("user_info", "${userInfo.email}  ${userInfo.password}")
                Log.d("user_info", "${email.text.toString()}  ${password.text.toString()}")
                if (email.text.toString().trim() == userInfo.email && password.text.toString().trim() == userInfo.password) {
                    val pref = getSharedPreferences("login", MODE_PRIVATE)
                    val edit = pref.edit()
                    edit.putBoolean("flag", false) // update false to true
                    edit.apply()
                    //loading.endLoading()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_LONG).show()
                    finish()
                    return@setOnClickListener // Exit loop early once login is successful
                }
            }
            Toast.makeText(applicationContext, "Invalid User!!!!!! ", Toast.LENGTH_LONG).show()
        }
    }
}