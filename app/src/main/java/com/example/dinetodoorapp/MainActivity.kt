package com.example.dinetodoorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Nextpage= findViewById<Button>(R.id.nextButton)
          Nextpage.setOnClickListener {
              val intent = Intent(this,Homepage::class.java)
              startActivity(intent)
          }
    }
}