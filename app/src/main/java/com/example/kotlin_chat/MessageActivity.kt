package com.example.kotlin_chat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MessageActivity : AppCompatActivity() {
    private lateinit var messageLayout: LinearLayout
    private lateinit var messageEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        messageLayout = findViewById(R.id.messageLayout)
        messageEditText = findViewById(R.id.editTextText3)

        val sendButton: Button = findViewById(R.id.button5)
        sendButton.setOnClickListener {
            sendMessage()
        }

        val exitButton: Button = findViewById(R.id.button6)
        exitButton.setOnClickListener {
            exitAndRedirectToLogin()
        }

        displayMessages()
    }

    private fun sendMessage() {
        val sharedPref: SharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "")

        val message = messageEditText.text.toString()
        if (message.isNotEmpty()) {
            val messageTextView = TextView(this)
            messageTextView.text = "$username: $message"
            messageLayout.addView(messageTextView)

            val messagesPref: SharedPreferences = getSharedPreferences("messages", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = messagesPref.edit()
            val existingMessages = messagesPref.getStringSet("message_list", mutableSetOf()) ?: mutableSetOf()
            existingMessages.add("$username: $message")
            editor.putStringSet("message_list", existingMessages)
            editor.apply()

            messageEditText.text.clear()
        }
    }

    private fun displayMessages() {
        val messagesPref: SharedPreferences = getSharedPreferences("messages", Context.MODE_PRIVATE)
        val existingMessages = messagesPref.getStringSet("message_list", mutableSetOf()) ?: mutableSetOf()

        for (message in existingMessages) {
            val messageTextView = TextView(this)
            messageTextView.text = message
            messageLayout.addView(messageTextView)
        }
    }

    private fun exitAndRedirectToLogin() {
        val sharedPref: SharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}