package com.jorgealdana.ceibaapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jorgealdana.ceibaapp.models.User
import com.jorgealdana.ceibaapp.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM ${Constants.TABLE_USERS} ORDER BY id ASC")
    fun getUsers(): Flow<List<User>>
}