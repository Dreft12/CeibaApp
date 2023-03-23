package com.jorgealdana.ceibaapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jorgealdana.ceibaapp.models.Post
import com.jorgealdana.ceibaapp.models.User
import com.jorgealdana.ceibaapp.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: Post)

    @Query("SELECT * FROM ${Constants.TABLE_USERS} ORDER BY id ASC")
    fun getPosts(): Flow<List<Post>>
}