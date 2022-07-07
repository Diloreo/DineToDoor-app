package com.example.dinetodoorapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.dinetodoorapp.managers.CartManager

class ordersummary : AppCompatActivity() {

    lateinit var cus_name: TextView
    lateinit var cus_address: TextView
    lateinit var cus_phoneno: TextView
    lateinit var cus_email  :TextView
    lateinit var cus_paymethod : TextView
    lateinit var cus_subtotal :TextView
    lateinit var cus_total : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordersummary)

        cus_name = findViewById(R.id.cus_name)
        cus_address = findViewById(R.id.cus_address)
        cus_phoneno = findViewById(R.id.cus_phoneno)
        cus_email = findViewById(R.id.cus_email)
        cus_paymethod= findViewById(R.id.cus_paymethod)
        cus_subtotal = findViewById(R.id.subtotaltxtbox)
        cus_total = findViewById(R.id.totaltxtbox)


        val bundle = intent.extras
        if (bundle != null) {
            cus_name.text = " ${bundle.getString("name")}"
            cus_address.text = " ${bundle.getString("address")}"
            cus_phoneno.text = " ${bundle.getString("phoneno")}"
            cus_email.text = " ${bundle.getString("email")}"
            cus_paymethod.text = " ${bundle.getString("paymethod")} on delivery"

        }


        val subtotal = intent.getStringExtra("subtotal")
         val deliveryfee = 200.00
        findViewById<TextView>(R.id.subtotaltxtbox).text = "LKR 6.00"
        findViewById<TextView>(R.id.totaltxtbox).text = "LKR 206.00"

        val cancelorderbtn: Button = findViewById(R.id.cancelorderbtn)
        val checkoutbtn: Button = findViewById(R.id.checkoutbtn)
        val backbtn: ImageButton = findViewById(R.id.backbtn5)
        val viewcart : Button = findViewById(R.id.viewcartbtn)


        cancelorderbtn.setOnClickListener {
            val intent = Intent(this, orderunsuccessfulpage::class.java)     //navigate to shop page
            startActivity(intent)
        }
        checkoutbtn.setOnClickListener {
            val intent = Intent(this, ordersuccessfulpage::class.java)     //navigate to shop page
            startActivity(intent)
        }
        backbtn.setOnClickListener {
            val intent = Intent(this, checkout::class.java)     //navigate to shop page
            startActivity(intent)
        }
        viewcart.setOnClickListener {
            val intent = Intent(this, CartPage::class.java)     //navigate to shop page
            startActivity(intent)
        }

    }
}