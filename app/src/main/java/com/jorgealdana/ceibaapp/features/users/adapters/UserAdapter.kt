package com.jorgealdana.ceibaapp.features.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorgealdana.ceibaapp.databinding.ItemUserListBinding

class UserAdapter(private val userAdapterProvider: UserAdapterProvider) :
    RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: ItemUserListBinding) : RecyclerView.ViewHolder(itemView.root) {
        val emailText = itemView.emailTxt
        val phoneText = itemView.phoneNumberTxt
        val name = itemView.nameUserTxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = userAdapterProvider.getListUser()?.size!!

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = userAdapterProvider.getListUser()?.get(position)
        holder.emailText.text = item?.email
        holder.name.text = item?.name
        holder.phoneText.text = item?.phone
    }
}