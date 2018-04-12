package br.com.sticup.mvvm.repository.api

import br.com.sticup.mvvm.repository.data.AlbumPage
import br.com.sticup.mvvm.repository.data.UserAlbumPage
import io.reactivex.Observable
import retrofit2.http.GET

interface UserAlbumPageApi {

    @GET("https://sticup-firebase.firebaseio.com/0/user/album/0/pageList/.json")
    fun getUserAlbumPage(): Observable<List<UserAlbumPage>>
}