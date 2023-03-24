package com.jorgealdana.ceibaapp.features.users.adapters

import com.jorgealdana.ceibaapp.models.User

interface UserAdapterProvider {
    fun onItemClick(user: User)
}