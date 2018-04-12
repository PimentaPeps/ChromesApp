package br.com.sticup.mvvm.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.sticup.mvvm.repository.data.UserAlbum
import io.reactivex.Single

@Dao
interface UserAlbumDao {

    @Query("SELECT * FROM user_album")
    fun getUserAlbum(): Single<List<UserAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: UserAlbum)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(album: List<UserAlbum>)
}