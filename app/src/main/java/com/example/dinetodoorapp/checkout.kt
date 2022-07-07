package com.example.dinetodoorapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dinetodoorapp.databinding.ActivityCheckoutBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class checkout : AppCompatActivity() {


   lateinit var fname : EditText
   lateinit var phoneno : EditText
   lateinit var email: EditText
   lateinit var address : EditText
   lateinit var notes : EditText
   lateinit var cash : EditText
   lateinit var home : EditText
   lateinit var confirmbtn : Button
   lateinit var backbtn : ImageButton

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var database : DatabaseReference


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fname = findViewById(R.id.fullnametxtbox)
        phoneno = findViewById(R.id.phonenotxtbox)
        address = findViewById(R.id.addresstxtbox)
        email = findViewById(R.id.emailtxtbox)
        cash = findViewById(R.id.txtcash)


        confirmbtn = findViewById(R.id.confirmbtn1)
        backbtn = findViewById(R.id.backbtn101)

        confirmbtn.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("name",fname.text.toString())
            bundle.putString("address",address.text.toString())
            bundle.putString("phoneno",phoneno.text.toString())
            bundle.putString("email" ,email.text.toString())
            bundle.putString("paymethod" ,cash.text.toString())

            binding.confirmbtn1.setOnClickListener {

                val fullname = binding.fullnametxtbox.text.toString()
                val phone = binding.phonenotxtbox.text.toString()
                val email = binding.emailtxtbox.text.toString()
                val address = binding.addresstxtbox.text.toString()
                val additionalnotes = binding.notestxtbox.toString()
                val cash = binding.txtcash.toString()
                val home = binding.txthome.toString()
                database = FirebaseDatabase.getInstance().getReference("Checkout")
                val checkoutuser = UserCheckout(fullname,phone,email,address,additionalnotes,cash,home)

                database.child(fullname).setValue(checkoutuser).addOnSuccessListener{
                    Toast.makeText(this,"Checkout Successful", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {
                    Toast.makeText(this,"Checkout Fail", Toast.LENGTH_SHORT).show()

                }

            }

            val intent = Intent(this,ordersummary::class.java)
            intent.putExtras(bundle)
            startActivity(intent)


        }

       backbtn.setOnClickListener {
            val intent = Intent(this,CartPage ::class.java)
            startActivity(intent)
        }

    }


}