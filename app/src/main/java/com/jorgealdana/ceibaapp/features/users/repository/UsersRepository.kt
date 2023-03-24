package com.jorgealdana.ceibaapp.features.users.repository

import androidx.annotation.WorkerThread
import com.jorgealdana.ceibaapp.database.dao.UserDao
import com.jorgealdana.ceibaapp.models.User
import com.jorgealdana.ceibaapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(private val userDao: UserDao, private val apiService: ApiService) {
    suspend fun getAllUsersFromDB(): List<User> = withContext(Dispatchers.IO) {
        userDao.getUsers()
    }

    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getUsers(): ArrayList<User> = withContext(Dispatchers.IO) {
        val response = apiService.getUsers()
        (if (response.isSuccessful) response.body()!! else emptyList()) as ArrayList<User>
    }
}