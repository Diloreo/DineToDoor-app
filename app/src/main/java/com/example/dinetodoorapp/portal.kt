package com.example.dinetodoorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class portal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portal)


        val btnstatus = findViewById<Button>(R.id.status)


        btnstatus.setOnClickListener {
            val intent = Intent(this,adminportalorderstatusmark::class.java)
            startActivity(intent)

        }



    }
}