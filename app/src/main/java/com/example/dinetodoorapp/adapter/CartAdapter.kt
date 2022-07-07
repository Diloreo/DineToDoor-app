package com.example.dinetodoorapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dinetodoorapp.R
import com.example.dinetodoorapp.managers.CartManager
import com.example.dinetodoorapp.model.ProductModel

class CartAdapter(private val list: List<ProductModel>) :
    RecyclerView.Adapter<CartAdapter.AdapterViewHolder>() {
    var onCartItemRemovedListener: OnCartItemRemovedListener? = null

    interface OnCartItemRemovedListener{
        fun onRemoved()
    }

    inner class AdapterViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_cart, parent, false)
    ) {
        fun bind(item: ProductModel) = with(itemView) {
            findViewById<TextView>(R.id.textProductName).text = item.name
            findViewById<TextView>(R.id.textPrice).text = "Rs. ${item.price}0"
            Glide.with(context).load(item.image ?: "").into(findViewById(R.id.imageProduct))
            findViewById<TextView>(R.id.button).setOnClickListener {
                CartManager.cartList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                onCartItemRemovedListener?.onRemoved()
                Toast.makeText(context, "Item removed from the cart", Toast.LENGTH_SHORT).show()
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