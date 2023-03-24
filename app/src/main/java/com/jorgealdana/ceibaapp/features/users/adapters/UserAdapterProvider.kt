package com.jorgealdana.ceibaapp.features.users.adapters

import com.jorgealdana.ceibaapp.models.User

interface UserAdapterProvider {

    fun getListUser(): ArrayList<User>? {
        return ArrayList()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}