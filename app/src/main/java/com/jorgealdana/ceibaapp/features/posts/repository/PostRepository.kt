package com.jorgealdana.ceibaapp.features.posts.repository

import androidx.annotation.WorkerThread
import com.jorgealdana.ceibaapp.database.dao.PostDao
import com.jorgealdana.ceibaapp.models.Post
import com.jorgealdana.ceibaapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(private val postsDao: PostDao, private val apiService: ApiService) {

    val allPost = postsDao.getPosts()

    @WorkerThread
    suspend fun insert(post: Post) {
        postsDao.insert(post)
    }

    suspend fun getPosts(id: Int): List<Post> = withContext(Dispatchers.IO) {
        val response = apiService.getPosts(id)
        if (response.isSuccessful) response.body()!! else emptyList()
    }
}