package com.jorgealdana.ceibaapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jorgealdana.ceibaapp.database.dao.PostDao
import com.jorgealdana.ceibaapp.database.dao.UserDao
import com.jorgealdana.ceibaapp.models.Post
import com.jorgealdana.ceibaapp.models.User
import com.jorgealdana.ceibaapp.utils.Constants

@Database(entities = [User::class, Post::class], version = Constants.DATABASE_VERSION, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    Constants.DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}