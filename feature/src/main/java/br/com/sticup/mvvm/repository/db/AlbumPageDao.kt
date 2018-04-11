package br.com.sticup.mvvm.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.sticup.mvvm.repository.data.AlbumPage
import io.reactivex.Single

@Dao
interface AlbumPageDao {

    @Query("SELECT * FROM album_page")
    fun getAlbumPage(): Single<List<AlbumPage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albumPage: AlbumPage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albumPage: List<AlbumPage>)
}