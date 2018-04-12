package br.com.sticup.mvvm.repository.api

import br.com.sticup.mvvm.repository.data.UserSticker
import io.reactivex.Observable
import retrofit2.http.GET

interface UserStickerApi {

    @GET("https://sticup-firebase.firebaseio.com/0/user/album/0/pageList/0/stickerList/.json")
    fun getUserSticker(): Observable<List<UserSticker>>
}