package br.com.sticup.mvvm.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.sticup.mvvm.repository.data.UserAlbumPage
import io.reactivex.Single

@Dao
interface UserAlbumPageDao {

    @Query("SELECT * FROM user_album_page")
    fun getUserAlbumPage(): Single<List<UserAlbumPage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albumPage: UserAlbumPage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albumPage: List<UserAlbumPage>)
}