package com.example.dinetodoorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dinetodoorapp.adapter.ProductAdapter
import com.example.dinetodoorapp.model.ProductModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class ProductPage : AppCompatActivity(), ValueEventListener {
    private lateinit var recycleViewProducts: RecyclerView
    private lateinit var dbRef : DatabaseReference
    private val dataList = ArrayList<ProductModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)
        dbRef = FirebaseDatabase.getInstance().getReference("Drink")
        recycleViewProducts = findViewById(R.id.recycleViewProducts)
        recycleViewProducts.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        dbRef.addListenerForSingleValueEvent(this)
    }

    override fun onPause() {
        super.onPause()
        dbRef.removeEventListener(this)
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        if(snapshot.exists()) {
            snapshot.children.forEach {
                it.getValue<ProductModel>()?.let { it1 -> dataList.add(it1) }
            }
            val adapter = ProductAdapter(dataList)
            recycleViewProducts.adapter = adapter
        }
    }

    override fun onCancelled(error: DatabaseError) {

    }
}