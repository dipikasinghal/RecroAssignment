package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class RecyclerListAdapter(private val context: Context, private val mList: List<ListModel>?) :
    RecyclerView.Adapter<RecyclerListAdapter.ImageListViewHolder>() {

    inner class ImageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_image)
        val titleText: TextView = itemView.findViewById(R.id.tv_title)
        val descriptionText: TextView = itemView.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_list, parent, false)

        return ImageListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.titleText.text = mList?.get(position)?.title ?: "-"
        holder.descriptionText.text = mList?.get(position)?.description ?: "-"
        Glide.with(context).load(mList?.get(position)?.image).placeholder(R.drawable.ic_placeholder)
            .into(holder.imageView)
    }


    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }


}