package br.com.sticup.mvvm.repository.api

import io.reactivex.Observable
import br.com.sticup.mvvm.repository.data.User
import retrofit2.http.GET

interface UserApi {

    @GET("https://invisionapp-f2994.firebaseio.com/.json")
    fun getUsers(): Observable<List<User>>
}
