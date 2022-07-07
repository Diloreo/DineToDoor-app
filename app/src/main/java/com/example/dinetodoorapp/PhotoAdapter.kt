package com.example.dinetodoorapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter(val imageList:List<Int>)
    :RecyclerView.Adapter<PhotoAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.image);

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoAdapter.ViewPagerViewHolder {

        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false);
        return ViewPagerViewHolder(layoutView);

    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewPagerViewHolder, position: Int) {

        val list = imageList[position]
        holder.image.setImageResource(list)
    }

    override fun getItemCount(): Int {

        return imageList.size



    }


}