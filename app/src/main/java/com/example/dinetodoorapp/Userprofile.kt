package com.example.dinetodoorapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Userprofile : AppCompatActivity() {


    private lateinit var progressDialog: ProgressDialog
    private lateinit var txtfname: EditText
    private lateinit var txtemail: EditText
    private lateinit var txtuname: EditText
    private lateinit var txtcno: EditText
    private lateinit var txtaddress: EditText
    private lateinit var textView4: TextView
    private lateinit var Regbtn: Button
    private lateinit var auth: FirebaseAuth
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("USERS")

        val Button1 = findViewById<Button>(R.id.homebtn)
        Button1.setOnClickListener {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }
        val Button2 = findViewById<Button>(R.id.productbtn)
        Button2.setOnClickListener {
            val intent = Intent(this, ProductPage::class.java)
            startActivity(intent)
        }
        val Button3 = findViewById<Button>(R.id.cartbtn)
        Button3.setOnClickListener {
            val intent = Intent(this, CartPage::class.java)
            startActivity(intent)
        }




        txtfname = findViewById(R.id.txtfname)
        txtemail = findViewById(R.id.txtemail)
        txtuname = findViewById(R.id.txtuname)
        txtcno = findViewById(R.id.txtcno)
        txtaddress = findViewById(R.id.txtaddress)
        textView4 = findViewById(R.id.textView4)

        Regbtn = findViewById(R.id.Regbtn)

        if (auth.currentUser != null) {
            loadData()
        }



        Regbtn.setOnClickListener {
            saveUserData()
        }

    }

    private fun loadData() {
        showProgress()
        val userRef = ref.child(auth.currentUser!!.uid)
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                hideProgress()
                if (snapshot.exists()) {
                    val user = snapshot.getValue(UserModel::class.java)
                    if (user != null) {
                        textView4.setText(user.name)
                        txtfname.setText(user.name)
                        txtemail.setText(user.email)
                        txtuname.setText(user.uname)
                        txtcno.setText(user.contactNo)
                        txtaddress.setText(user.address)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgress()
            }

        })
    }

    private fun saveUserData() {
        showProgress()
        val fname = txtfname.text.toString()
        val email = txtemail.text.toString()
        val uname = txtuname.text.toString()
        val cno = txtcno.text.toString()
        val address = txtaddress.text.toString()

        if (fname.isEmpty()) {
            txtfname.error = "Please enter name"
        }
        if (email.isEmpty()) {
            txtemail.error = "Please enter email"
        }
        if (uname.isEmpty()) {
            txtuname.error = "please enter username"
        }
        if (cno.isEmpty()) {
            txtcno.error = "please enter contact no"
        }
        if (address.isEmpty()) {
            txtaddress.error = "please enter address"
        }

        val userId = auth.currentUser!!.uid

        val user = UserModel(userId, fname, email, uname, cno, address)

        ref.child(userId).setValue(user)
            .addOnCompleteListener {
                hideProgress()
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                hideProgress()
                Toast.makeText(this, "Error $err.get", Toast.LENGTH_LONG).show()

            }


    }

    private fun hideProgress() {
        progressDialog.dismiss()
    }

    private fun showProgress() {
        progressDialog = ProgressDialog(this@Userprofile)
        progressDialog.setTitle("Profile")
        progressDialog.setMessage("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
    }
}