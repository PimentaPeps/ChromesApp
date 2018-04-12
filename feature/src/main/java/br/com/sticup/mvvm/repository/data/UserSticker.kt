package br.com.sticup.mvvm.repository.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user_sticker")
data class UserSticker(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "number")
        val number: String,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "count")
        val count: String)