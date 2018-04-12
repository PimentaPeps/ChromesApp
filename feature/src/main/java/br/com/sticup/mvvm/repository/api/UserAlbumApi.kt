package br.com.sticup.mvvm.repository.api

import br.com.sticup.mvvm.repository.data.Album
import br.com.sticup.mvvm.repository.data.UserAlbum
import io.reactivex.Observable
import retrofit2.http.GET

interface UserAlbumApi {

    @GET("https://sticup-firebase.firebaseio.com/0/user/album/.json")
    fun getUserAlbum(): Observable<List<UserAlbum>>
}