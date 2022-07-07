package com.example.dinetodoorapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dinetodoorapp.R
import com.example.dinetodoorapp.managers.CartManager
import com.example.dinetodoorapp.model.ProductModel

class ProductAdapter(private val list: List<ProductModel>) : RecyclerView.Adapter<ProductAdapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(parent: ViewGroup):RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_product, parent, false)){

        fun bind(item:ProductModel) = with(itemView){
            findViewById<TextView>(R.id.textProductName).text = item.name
            findViewById<TextView>(R.id.textPrice).text = "Rs. ${item.price}0"
            Glide.with(context).load(item.image?:"").into(findViewById(R.id.imageProduct))
            findViewById<Button>(R.id.buttonAddToCart).setOnClickListener {
                CartManager.cartList.add(item)
                Toast.makeText(context, "Item added to the cart", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(parent)
    }


    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

}