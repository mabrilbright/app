package com.example.ririahub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Signup : AppCompatActivity() {

    private lateinit var uname: EditText
    private lateinit var upassword: EditText
    private lateinit var cpassword: EditText
    private lateinit var signupbtn: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        uname= findViewById(R.id.editusername)
        upassword= findViewById(R.id.editTextTextPassword)
        cpassword= findViewById(R.id.editTextTextPassword2)
        signupbtn= findViewById(R.id.button3)
        db= DBHelper(this)

        signupbtn.setOnClickListener{
            val unametext = uname.text.toString()
            val upasswordtext = upassword.text.toString()
            val cpasswordtext = cpassword.text.toString()
            val savedata = db.addUser(unametext, upasswordtext)

            if(TextUtils.isEmpty(unametext) || TextUtils.isEmpty(upasswordtext) || TextUtils.isEmpty(cpasswordtext)){
                Toast.makeText(this,"Enter username password & confirm password",Toast.LENGTH_SHORT).show()
            }
            else{
                if(upasswordtext==(cpasswordtext)){
                    if (savedata.equals(true)){
                        Toast.makeText(this,"Signup Successful",Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Login::class.java)
                    }else{
                        Toast.makeText(this,"User exists",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Passwords doesn't match",Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}