package com.example.dinetodoorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dinetodoorapp.adapter.CartAdapter
import com.example.dinetodoorapp.managers.CartManager

class CartPage : AppCompatActivity(), CartAdapter.OnCartItemRemovedListener {
    private lateinit var adapter: CartAdapter
    private lateinit var recycleViewCart: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        recycleViewCart = findViewById(R.id.recycleViewCart)

        recycleViewCart.layoutManager = GridLayoutManager(this, 2)

        adapter = CartAdapter(CartManager.cartList)
        recycleViewCart.adapter = adapter

        calcTotal()
        adapter.onCartItemRemovedListener = this


        findViewById<Button>(R.id.button2).setOnClickListener {
            val intent = Intent(this,checkout::class.java)
            startActivity(intent)
        }

    }

    private fun calcTotal(): Double {
        var total = 0.0
        CartManager.cartList.forEach {
            total += (it.price ?: "0.0").toDouble()
        }

        findViewById<TextView>(R.id.textView10).text = "Total (${CartManager.cartList.size}) : LKR ${total}0"
       return total

    }

    override fun onRemoved() {
        calcTotal()
    }


}