package br.com.sticup.mvvm.repository.api

import br.com.sticup.mvvm.repository.data.Sticker
import io.reactivex.Observable
import retrofit2.http.GET

interface StickerApi {

    @GET("https://sticup.firebaseio.com/.json")
    fun getSticker(): Observable<List<Sticker>>
}