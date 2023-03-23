package com.jorgealdana.ceibaapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jorgealdana.ceibaapp.utils.Constants.TABLE_USERS

@Entity(tableName = TABLE_USERS)
data class User(
    @PrimaryKey @ColumnInfo(name = "id") var id: Int? = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "username") var username: String = "",
    @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "phone") var phone: String = "",
)