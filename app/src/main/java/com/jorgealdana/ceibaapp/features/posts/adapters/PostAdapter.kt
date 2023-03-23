package com.jorgealdana.ceibaapp.features.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorgealdana.ceibaapp.databinding.ItemPostListBinding

class PostAdapter(private val provider: PostProvider) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(itemView: ItemPostListBinding): RecyclerView.ViewHolder(itemView.root){
        val title = itemView.titleTxt
        val body = itemView.bodyTxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPostListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = provider.getPosts()?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = provider.getPosts()?.get(position)
        holder.title.text = item?.title
        holder.body.text = item?.body
    }
}