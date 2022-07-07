package com.example.dinetodoorapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dinetodoorapp.databinding.ActivityDisplaystatusBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class displaystatus : AppCompatActivity() {

    private lateinit var binding: ActivityDisplaystatusBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplaystatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val orderID : String = binding.ediTxt.text.toString()
            if(orderID.isNotEmpty()){
                readData(orderID)

            }else{
                Toast.makeText(this,"Please enter the order ID", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(orderID: String) {

        database = FirebaseDatabase.getInstance().getReference("OrderID")
        database.child(orderID).get().addOnSuccessListener {
            if (it.exists()) {
                val orderstatus =  it.child("statusOrder").value
                binding.ediTxt.text.clear()
                binding.displayStatus.text= orderstatus.toString()

            } else {

                Toast.makeText(this,"Please Wait",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }




    }
}