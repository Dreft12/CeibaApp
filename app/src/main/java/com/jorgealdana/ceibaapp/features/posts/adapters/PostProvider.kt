package com.jorgealdana.ceibaapp.features.posts.adapters

import com.jorgealdana.ceibaapp.models.Post

interface PostProvider {

    fun getPosts(): ArrayList<Post>? {
        return null
    }
}