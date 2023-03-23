package com.jorgealdana.ceibaapp.features.users.repository

import androidx.annotation.WorkerThread
import com.jorgealdana.ceibaapp.database.dao.UserDao
import com.jorgealdana.ceibaapp.models.User

class UsersRepository(private val userDao: UserDao) {

    val allUsers = userDao.getUsers()

    @WorkerThread
    suspend fun insert(user: User){
        userDao.insert(user)
    }
}