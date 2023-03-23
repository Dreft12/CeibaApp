package com.jorgealdana.ceibaapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jorgealdana.ceibaapp.utils.Constants

@Entity(tableName = Constants.TABLE_POSTS)
data class Post(
    @ColumnInfo(name = "userId") val userId: Int? = 0,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int? = 0,
    @ColumnInfo(name = "title") val title: String? = "",
    @ColumnInfo(name = "body") val body: String? = "",
)