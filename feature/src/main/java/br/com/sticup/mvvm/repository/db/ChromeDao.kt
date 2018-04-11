package br.com.sticup.mvvm.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.sticup.mvvm.repository.data.Sticker
import io.reactivex.Single

@Dao
interface StickerDao {

    @Query("SELECT * FROM sticker")
    fun getSticker(): Single<List<Sticker>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sticker: Sticker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sticker: List<Sticker>)
}