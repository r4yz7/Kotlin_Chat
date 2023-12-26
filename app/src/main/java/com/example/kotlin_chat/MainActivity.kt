package com.example.kotlin_chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.FragmentActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            if (!isLogin()){
                buttonClickLogin(null);
            }
            else{
                val intent = Intent(this, LoginActivity::class.java);
                startActivity(intent);
            }
        },3000);
    }

    fun isLogin():Boolean{
        return false;
    }

    fun buttonClickLogin(view: View?){
        val intent = Intent(this, LoginActivity::class.java);
        startActivity(intent)
    }
}