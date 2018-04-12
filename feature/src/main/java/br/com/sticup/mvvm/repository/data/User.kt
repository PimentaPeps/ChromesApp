package br.com.sticup.mvvm.repository.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class User(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "email")
        val email: String,
        @ColumnInfo(name = "firstName")
        val first: String,
        @ColumnInfo(name = "lastName")
        val last: String,
        @ColumnInfo(name = "albumList")
        val userAlbum: ArrayList<UserAlbum>)