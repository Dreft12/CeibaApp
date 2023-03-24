package com.jorgealdana.ceibaapp.features.posts.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgealdana.ceibaapp.features.posts.repository.PostRepository
import com.jorgealdana.ceibaapp.models.Post
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> = _posts

    fun insert(post: Post) = viewModelScope.launch {
        repository.insert(post)
    }

    fun loadPostsByUser(id: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getPosts(id)
                _posts.value = result
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}