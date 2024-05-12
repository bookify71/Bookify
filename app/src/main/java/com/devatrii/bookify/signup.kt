package com.devatrii.bookify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import soup.neumorphism.NeumorphButton

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val signup: NeumorphButton = findViewById(R.id.sign_up)
        val editTextEmail: EditText = findViewById(R.id.email)
        val editTextPassword: EditText = findViewById(R.id.password)
        val editTextPhone: EditText = findViewById(R.id.mobile_number)
        val helper = DbHelper(applicationContext)
        signup.setOnClickListener(){
            val phoneNumber: String = editTextPhone.text.toString().trim()
            val email: String = editTextEmail.text.toString().trim()
            val password: String = editTextPassword.text.toString().trim()

            if (email.isEmpty()) {
                editTextEmail.error = "Email is compulsory"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Password is compulsory"
                return@setOnClickListener
            }

            if (phoneNumber.isEmpty() || phoneNumber.length != 10) {
                editTextPhone.error = "Enter a valid 10 digit phone number"
                editTextPhone.requestFocus()
                return@setOnClickListener
            }
            val helper = DbHelper(applicationContext)
            helper.addUserInfo(email,password,phoneNumber)
            val intent = Intent(applicationContext, sign_in::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"user Inserted",Toast.LENGTH_LONG).show()
            finish()
        }
    }
}