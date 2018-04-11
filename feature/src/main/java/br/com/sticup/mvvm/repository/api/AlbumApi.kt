package br.com.sticup.mvvm.repository.api

import br.com.sticup.mvvm.repository.data.Album
import io.reactivex.Observable
import retrofit2.http.GET

interface AlbumApi {

    @GET("https://sticup-firebase.firebaseio.com/.json")
    fun getAlbum(): Observable<List<Album>>
}