package br.com.sticup.mvvm.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import br.com.sticup.mvvm.repository.data.*
import br.com.sticup.mvvm.repository.data.converter.*


@Database(entities = arrayOf(User::class, Sticker::class, UserSticker::class, Album::class, UserAlbum::class, AlbumPage::class, UserAlbumPage::class), version = 1)
@TypeConverters(UserConverter::class, StickerConverter::class, UserStickerConverter::class, AlbumConverter::class, UserAlbumConverter::class, AlbumPageConverter::class, UserAlbumPageConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stickerDao(): StickerDao
    abstract fun userStickerDao(): UserStickerDao
    abstract fun albumDao(): AlbumDao
    abstract fun userAlbumDao(): UserAlbumDao
    abstract fun albumPageDao(): AlbumPageDao
    abstract fun userAlbumPageDao(): UserAlbumPageDao
}