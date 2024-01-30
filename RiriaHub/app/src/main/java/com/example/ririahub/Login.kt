package com.example.ririahub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var uname: EditText
    private lateinit var upassword: EditText
    private lateinit var loginbtn: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        uname= findViewById(R.id.editTextText)
        upassword= findViewById(R.id.editTextTextPassword3)
        loginbtn= findViewById(R.id.button4)
        db= DBHelper(this)

        loginbtn.setOnClickListener{
            val unametext= uname.text.toString()
            val upasswordtext= upassword.text.toString()

            if (TextUtils.isEmpty(unametext) || TextUtils.isEmpty(upasswordtext)){
                Toast.makeText(this,"Enter username & Password",Toast.LENGTH_SHORT).show()
            }else{
                val credentialsValid = db.checkUserCredentials(unametext,upasswordtext)
                if (credentialsValid) {
                    // User authentication successful
                    Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show()
                    // Perform the necessary actions (e.g., navigate to the main screen)
                    val intent = Intent(applicationContext,Home::class.java)
                } else {
                    // User authentication failed
                    // Display an error message or take appropriate action
                }

            }
        }

    }
}