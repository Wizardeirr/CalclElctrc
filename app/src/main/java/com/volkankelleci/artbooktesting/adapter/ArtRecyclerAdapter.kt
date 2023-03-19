package com.volkankelleci.artbooktesting.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.volkankelleci.artbooktesting.R
import com.volkankelleci.artbooktesting.roomdb.Art
import com.volkankelleci.artbooktesting.util.Util.calculateBill
import com.volkankelleci.artbooktesting.util.Util.callCalc
import javax.inject.Inject

class ArtRecyclerAdapter@Inject constructor(
    val glide:RequestManager
) : RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    class ArtViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    private val diffUtil=object :DiffUtil.ItemCallback<Art>(){
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerListDiffer=AsyncListDiffer(this,diffUtil)
    var arts:List<Art>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    //Recycler View'ı verimli hale getirmek ve Recycler'ı güncelleyip yeni elemanları daha hızlı koymak için



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.art_row,parent,false)
        return ArtViewHolder(view)

    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {

        val imageView=holder.itemView.findViewById<ImageView>(R.id.art_row_image_view)
        val nameText=holder.itemView.findViewById<TextView>(R.id.art_row_name)
        val yearText=holder.itemView.findViewById<TextView>(R.id.art_row_year)
        val artisNameText=holder.itemView.findViewById<TextView>(R.id.art_row_artist)
        val art=arts[position]
        holder.itemView.apply {
            nameText.text ="Amount Due: ${calculateBill(art.year)} $"
            yearText.text = "Electric Cost Amount: ${art.year} Unit"
            artisNameText.text="Number of: ${art.id}"
            glide.load(art.imageUrl).into(imageView)

        }

    }

    override fun getItemCount(): Int {
        return arts.size
        }
}
