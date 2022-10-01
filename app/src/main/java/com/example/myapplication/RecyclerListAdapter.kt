package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemImageListBinding

class RecyclerListAdapter(private val context: Context, private val mList: List<ListModel>?) :
    RecyclerView.Adapter<RecyclerListAdapter.ImageListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val itemImageListBinding: ItemImageListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_image_list, parent, false
        )
        return ImageListViewHolder(itemImageListBinding)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.itemImageListBinding.tvTitle.text = mList?.get(position)?.title ?: "-"
        holder.itemImageListBinding.tvDescription.text = mList?.get(position)?.description ?: "-"
        Glide.with(context).load(mList?.get(position)?.image).placeholder(R.drawable.ic_placeholder)
            .into(holder.itemImageListBinding.ivImage)
    }


    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    inner class ImageListViewHolder(var itemImageListBinding: ItemImageListBinding) :
        RecyclerView.ViewHolder(itemImageListBinding.root)

}