package br.com.sticup.mvvm.viewmodel

import br.com.sticup.mvvm.repository.UserAlbumRepository
import br.com.sticup.mvvm.viewmodel.data.UserAlbumList
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class UserAlbumListViewModel(val userAlbumRepository: UserAlbumRepository) {

    fun getUserUserAlbums(): Observable<UserAlbumList> {
        //Create the data for your UI, the userAlbums lists and maybe some additional data needed as well
        return userAlbumRepository.getUserAlbums()
                //Drop DB data if we can fetch item fast enough from the API
                //to avoid UI flickers
                .debounce(400, TimeUnit.MILLISECONDS)
                .map {
                    Timber.d("Mapping userAlbums to UIData...")
                    UserAlbumList(it.take(10), "Top 10 UserAlbums")
                }
                .onErrorReturn {
                    UserAlbumList(emptyList(), "An error occurred", it)
                }
    }
}