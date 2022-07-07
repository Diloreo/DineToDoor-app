package com.example.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cart.Listener.ICartLoadListener
//import com.example.cart.adapter.MyCartAdapter
import com.example.dinetodoorapp.model.CartModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartActivity : AppCompatActivity(), ICartLoadListener {

    var cartLoadListener:ICartLoadListener?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_cart)
        init()
        loadCartFromFirebase()
    }

    private fun init()
    {
        cartLoadListener = this
        val layoutManager = LinearLayoutManager(this)
       // recycler_cart!!.layoutManager=layoutManager
       //btnBack!!.setOnClickListener{finish()}

       // btnCart.setOnClickListener{ startActivity(Intent(this,CartActivity::class.java))}
    }

    override fun onLoadCartSuccess(cartModelList: List<CartModel>) {
        var sum =0.0
        for (cartModel in cartModelList!!){
            sum+= cartModel!!.totalPrice
        }
       // txtTotal.text=StringBuilder("$").append(sum)
       // recycler_cart!!.adapter=adapter
    }
    override fun onLoadCartFailed(message: String?) {
      //  Snackbar.make(mainLayout,message!!,Snackbar.LENGTH_LONG).show()
    }

    private fun loadCartFromFirebase()
    {
        val cartModels : MutableList<CartModel> = ArrayList()
        FirebaseDatabase.getInstance()
            .getReference("Cart")
            .child("UNIQUE_USER_ID")
            .addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    for (cartSnapshot in snapshot.children)
                    {
                        val cartModel = cartSnapshot.getValue(CartModel::class.java)
                        cartModel!!.key = cartSnapshot.key
                        cartModels.add(cartModel)
                    }
                    cartLoadListener!!.onLoadCartSuccess(cartModels)
                }

                override fun onCancelled(error: DatabaseError)
                {
                    cartLoadListener!!.onLoadCartFailed(error.message)
                }
            })






    }

}



