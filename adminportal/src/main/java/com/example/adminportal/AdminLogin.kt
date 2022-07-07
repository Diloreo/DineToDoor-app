package com.example.adminportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)



        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val username = findViewById<EditText>(R.id.edUsername)
        val password = findViewById<EditText>(R.id.edPassword)

        btnLogin.setOnClickListener {
            if(username.text.toString().equals("Admin")
                &&password.text.toString().equals("root")){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)}
            else{

                Toast.makeText( this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            }


        }


    }
}