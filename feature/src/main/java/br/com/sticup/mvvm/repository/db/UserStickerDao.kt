package br.com.sticup.mvvm.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.sticup.mvvm.repository.data.UserSticker
import io.reactivex.Single

@Dao
interface UserStickerDao {

    @Query("SELECT * FROM user_sticker")
    fun getUserSticker(): Single<List<UserSticker>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sticker: UserSticker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sticker: List<UserSticker>)
}