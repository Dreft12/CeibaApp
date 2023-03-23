package com.jorgealdana.ceibaapp

import android.app.Application
import android.os.Build
import com.jorgealdana.ceibaapp.database.AppDB
import com.jorgealdana.ceibaapp.features.posts.repository.PostRepository
import com.jorgealdana.ceibaapp.features.users.repository.UsersRepository
import com.jorgealdana.ceibaapp.network.RestClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDB.getDatabase(this, applicationScope) }
    val restClient by lazy { RestClient(BuildConfig.BASE_URL) }
    val userRepository by lazy { UsersRepository(database.userDao(), restClient.apiService)}
    val postRepository by lazy { PostRepository(database.postDao(), restClient.apiService)}
}