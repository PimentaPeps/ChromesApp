package br.com.sticup.mvvm.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import br.com.sticup.mvvm.repository.data.Album
import br.com.sticup.mvvm.repository.data.AlbumPage
import br.com.sticup.mvvm.repository.data.Sticker
import br.com.sticup.mvvm.repository.data.User
import br.com.sticup.mvvm.repository.data.converter.StickerConverter
import br.com.sticup.mvvm.repository.data.converter.AlbumPageConverter


@Database(entities = arrayOf(User::class, Sticker::class, Album::class, AlbumPage::class), version = 1)
@TypeConverters(StickerConverter::class, AlbumPageConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stickerDao(): StickerDao
    abstract fun albumDao(): AlbumDao
    abstract fun albumPageDao(): AlbumPageDao
}