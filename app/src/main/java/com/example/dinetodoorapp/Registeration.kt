package com.example.dinetodoorapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dinetodoorapp.databinding.ActivityRegisterationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registeration : AppCompatActivity() {


    lateinit var binding: ActivityRegisterationBinding
    private lateinit var auth: FirebaseAuth
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("USERS")


        binding.registerButton.setOnClickListener {
            val name = binding.regname.text.toString().trim()
            val cno = binding.regPhone.text.toString().trim()
            val email = binding.regemail.text.toString().trim()
            val password = binding.regPassword.text.toString().trim()
            val address = binding.regAddress.text.toString().trim()

            if (email.isEmpty()) {
                binding.regemail.error = "Email required"
                binding.regemail.requestFocus()
                return@setOnClickListener

            }
            if (password.isEmpty() || password.length < 6) {
                binding.regPassword.error = "Password required"
                binding.regPassword.requestFocus()
                return@setOnClickListener

            }

            if (name.isEmpty()) {
                binding.regname.error = "Enter name"
                binding.regname.requestFocus()
                return@setOnClickListener

            }

            if (cno.isEmpty()) {
                binding.regPhone.error = "Enter contact no"
                binding.regPhone.requestFocus()
                return@setOnClickListener

            }
            if (address.isEmpty()) {
                binding.regAddress.error = "Enter Address"
                binding.regAddress.requestFocus()
                return@setOnClickListener

            }

            registrationUser(email, password, name, cno, address)

        }

    }

    private fun registrationUser(
        email: String,
        password: String,
        name: String,
        cno: String,
        address: String
    ) {
        val progressDialog = ProgressDialog(this@Registeration)
        progressDialog.setTitle("Registration User")
        progressDialog.setMessage("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                saveUser(name, email, cno, address, progressDialog)
            } else {
                val message = it.exception!!.toString()
                Toast.makeText(this, "Error : $message", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun saveUser(
        name: String,
        email: String,
        cno: String,
        address: String,
        progressDialog: ProgressDialog
    ) {
        val currentUserId = auth.currentUser!!.uid
        ref = FirebaseDatabase.getInstance().reference.child("USERS")
        val userMap = HashMap<String, Any>()
        userMap["id"] = currentUserId
        userMap["name"] = name
        userMap["email"] = email
        userMap["contactNo"] = cno
        userMap["address"] = address
        userMap["uname"] = ""

        ref.child(currentUserId).setValue(userMap).addOnCompleteListener {
            if (it.isSuccessful) {
                progressDialog.dismiss()
                Toast.makeText(this, "Register is Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Registeration, Homepage::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                val message = it.exception!!.toString()
                Toast.makeText(this, "Error : $message", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
        }


    }


}