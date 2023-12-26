package com.example.kotlin_chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.io.FileWriter

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton: Button = findViewById(R.id.button3)
        registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val usernameEditText: EditText = findViewById(R.id.editTextText2)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)
        val confirmPasswordEditText: EditText = findViewById(R.id.editTextTextPassword3)

        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (password == confirmPassword) {
            saveUserInfoToFile(username, password)
        } else {
            showToast("Passwords do not match.")
        }
    }

    private fun buttonClickLogin(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun saveUserInfoToFile(username: String, password: String) {
        try {
            val userInfoFile = File(filesDir, "user_info.txt")
            val fileWriter = FileWriter(userInfoFile, true)

            fileWriter.append("Username: $username, Password: $password\n")
            fileWriter.close()

            showToast("Registration successful.")
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Error during registration.")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun buttonLogin(view: View){
        val intent = Intent(this, LoginActivity::class.java);
        startActivity(intent)
    }
}