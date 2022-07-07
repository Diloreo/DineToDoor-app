package com.example.dinetodoorapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dinetodoorapp.databinding.ActivityOrderStatusTrackBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class OrderStatusTrack: AppCompatActivity() {

    private lateinit var binding: ActivityOrderStatusTrackBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderStatusTrackBinding.inflate(layoutInflater)
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
        database = FirebaseDatabase.getInstance().getReference("Order Status")
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