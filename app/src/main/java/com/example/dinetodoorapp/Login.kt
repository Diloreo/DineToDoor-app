package com.example.dinetodoorapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dinetodoorapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.register.setOnClickListener {
            val intent = Intent(this@Login,Registeration::class.java)
            startActivity(intent)
        }


       val textView = findViewById<TextView>(R.id.register)

        textView.setOnClickListener {
            val intent = Intent(this, Registeration::class.java)
            startActivity(intent)

        }


    }
    /*override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            val intent = Intent(this,Homepage::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }*/
}