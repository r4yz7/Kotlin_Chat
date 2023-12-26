package com.example.kotlin_chat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.button)
        loginButton.setOnClickListener {
            loginUser()
        }

        val registerButton: Button = findViewById(R.id.button2)
        registerButton.setOnClickListener {
            buttonClickRegister()
        }
    }

    private fun loginUser() {
        val usernameEditText: EditText = findViewById(R.id.editTextText)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword2)
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        val sharedPref: SharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val isRegistered = sharedPref.getBoolean("isRegistered", false)

        if (isRegistered) {
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
        } else {
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putString("username", username)
            editor.putBoolean("isRegistered", true)
            editor.apply()
        }
    }

    private fun buttonClickRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}