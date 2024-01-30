package com.example.ririahub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var signupbtn : Button
    private lateinit var loginbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signupbtn = findViewById(R.id.button)
        loginbtn = findViewById(R.id.button2)

        signupbtn.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        loginbtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}