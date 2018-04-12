package br.com.sticup.mvvm.repository.api

import io.reactivex.Observable
import br.com.sticup.mvvm.repository.data.User
import retrofit2.http.GET

interface UserApi {

    @GET("https://sticup-firebase.firebaseio.com/0/user/.json")
    fun getUser(): Observable<List<User>>
}
