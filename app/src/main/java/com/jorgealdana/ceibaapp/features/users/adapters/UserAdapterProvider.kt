package com.jorgealdana.ceibaapp.features.users.adapters

import com.jorgealdana.ceibaapp.models.User

interface UserAdapterProvider {

    fun getListUser(): List<User>? {
        return null
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}