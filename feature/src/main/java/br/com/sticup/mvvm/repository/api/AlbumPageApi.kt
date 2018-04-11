package br.com.sticup.mvvm.repository.api

import br.com.sticup.mvvm.repository.data.AlbumPage
import io.reactivex.Observable
import retrofit2.http.GET

interface AlbumPageApi {

    @GET("https://sticup-firebase.firebaseio.com/.json")
    fun getAlbumPage(): Observable<List<AlbumPage>>
}