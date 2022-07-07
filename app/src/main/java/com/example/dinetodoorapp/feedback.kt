package com.example.dinetodoorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class feedback : AppCompatActivity() {

    lateinit var Edttxt : EditText
    lateinit var rbStars : RatingBar
    lateinit var btnSend : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        Edttxt = findViewById(R.id.Edttxt)
        rbStars = findViewById(R.id.rbStars)
        btnSend = findViewById(R.id.btnSend)



        btnSend.setOnClickListener {
            saveHero()
        }



    }

    private fun saveHero() {
        val name = Edttxt.text.toString().trim()

        if(name.isEmpty()){
            Edttxt.error = "Please fill to send the FeedBack"
            return
        }



        val ref = FirebaseDatabase.getInstance().getReference("FeedBack")
        val heroId = ref.push().key

        val hero = Hero(heroId,name, rbStars.numStars)

        ref.child(heroId.toString()).setValue(hero).addOnCompleteListener{
            Toast.makeText(applicationContext,"Hero Saved", Toast.LENGTH_LONG).show()

        }

    }
}