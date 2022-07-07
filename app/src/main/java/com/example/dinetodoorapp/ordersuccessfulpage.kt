package com.example.dinetodoorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ordersuccessfulpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordersuccessfulpage)

        val shoppagebtn: Button = findViewById(R.id.shoppagebtn)

        val statusbtn : Button = findViewById(R.id.orderstatuspage)

        statusbtn.setOnClickListener {
            val intent = Intent(this, OrderStatusTrack::class.java) //navigate to Order Tracking Page
            startActivity(intent)
        }

        shoppagebtn.setOnClickListener {
            val intent = Intent(
                this,
                Homepage::class.java
            )
            startActivity(intent)
        }
        /*
        orderstatuspage.setOnClickListener {
            val intent = Intent(
                this,
               displaystatus::class.java
            )
            startActivity(intent)
        }
        
         */


    }
}