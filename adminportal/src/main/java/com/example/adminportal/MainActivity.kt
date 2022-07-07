package com.example.adminportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var orderid : EditText
    lateinit var btnNext : Button
    lateinit var ediOrderstatus : EditText
    lateinit var dbref:DatabaseReference
    lateinit var btnBack :Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbref = FirebaseDatabase.getInstance().getReference( "Order Status")

        orderid = findViewById(R.id.EdiStatus)
        ediOrderstatus = findViewById(R.id.ediOrderstatus)
        btnNext =  findViewById(R.id.btnDone)
        btnBack = findViewById(R.id.btnBack)


        btnNext.setOnClickListener {
            saveData()
        }

        btnBack.setOnClickListener {
            startActivity(Intent(this, AdminLogin::class.java))
        }



    }

    private fun saveData(){
        val orderID = orderid.text.toString()
        val statusOrder = ediOrderstatus.text.toString()




        val orderStat = Orderstatus(orderID,statusOrder)
        dbref.child(orderID).setValue(orderStat)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error${err.message}", Toast.LENGTH_LONG).show()
            }
    } }
