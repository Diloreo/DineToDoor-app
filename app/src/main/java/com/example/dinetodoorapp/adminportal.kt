package com.example.dinetodoorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class adminportal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminportal)


        val edUsername = findViewById<EditText>(R.id.edUsername)
        val edPassword = findViewById<EditText>(R.id.edPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        btnLogin.setOnClickListener{

            if (edUsername.text.trim().isNotEmpty() || edPassword.text.trim().isNotEmpty()){
                Toast.makeText(this ,"Input provided", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this ,"Input required to proceed", Toast.LENGTH_LONG).show()
            }
        }
        tvRegister.setOnClickListener {
            val intent = Intent(this,Registeration::class.java)
            startActivity(intent)

        }

    }
}