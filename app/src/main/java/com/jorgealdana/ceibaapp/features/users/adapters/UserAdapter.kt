package com.jorgealdana.ceibaapp.features.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorgealdana.ceibaapp.databinding.ItemUserListBinding
import com.jorgealdana.ceibaapp.models.User

class UserAdapter(private var userList: List<User>, private var onItemClickListener: UserAdapterProvider) :
    RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: ItemUserListBinding) : RecyclerView.ViewHolder(itemView.root) {
        val emailText = itemView.emailTxt
        val phoneText = itemView.phoneNumberTxt
        val name = itemView.nameUserTxt
        val verPosts = itemView.seePostsButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = userList[position]
        holder.emailText.text = item.email
        holder.name.text = item.name
        holder.phoneText.text = item.phone

        holder.verPosts.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }

    fun setFilterItems(filterItems: List<User>) {
        userList = filterItems
        notifyDataSetChanged()
    }

    fun setItems(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}