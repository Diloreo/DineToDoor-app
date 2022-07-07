package com.example.dinetodoorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EnterOrderStatus : AppCompatActivity() {


    lateinit var orderid : EditText
    lateinit var btnNext : Button
    lateinit var ediOrderstatus : EditText
    lateinit var dbref: DatabaseReference

    lateinit var btnnext : Button  //remove



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_order_status)



        dbref = FirebaseDatabase.getInstance().getReference( "OrderID")

        orderid = findViewById(R.id.EdiStatus)
        ediOrderstatus = findViewById(R.id.ediOrderstatus)
        btnNext =  findViewById(R.id.btnDone)

        btnNext.setOnClickListener {
            saveData()
        }

        btnnext = findViewById(R.id.btnjust)
        btnnext.setOnClickListener {
            val intent = Intent( this, EnterOrderStatus::class.java)
            startActivity(intent)
        }


    }

    private fun saveData()
    {
        val orderID = orderid.text.toString()
        val statusOrder = ediOrderstatus.text.toString()




        val orderStat = Orderstatus(orderID,statusOrder)
        dbref.child(orderID).setValue(orderStat)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error${err.message}", Toast.LENGTH_LONG).show()
            }
    }


    }
