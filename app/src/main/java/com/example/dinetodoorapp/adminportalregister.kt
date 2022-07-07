package com.example.dinetodoorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class adminportalregister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminportalregister)

        val tvLogin: TextView = findViewById(R.id.tvLogin)
        val btnRegister: Button = findViewById(R.id.btnRegister)
        val editUsername : EditText = findViewById(R.id.editUsername)
        val editEmail : EditText = findViewById(R.id.editEmail)
        val editPassword : EditText = findViewById(R.id.editPassword)
        val editCPassword : EditText = findViewById(R.id.editCPassword)


        btnRegister.setOnClickListener {
            if (editUsername.text.trim()
                    .isNotEmpty() || editEmail.text.isNotEmpty() || editPassword.text.isNotEmpty() || editCPassword.text.isNotEmpty()
            ) {
                Toast.makeText(this ,"Input provided ", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this ,"Input required to proceed", Toast.LENGTH_LONG).show()

            }
        }
        tvLogin.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}