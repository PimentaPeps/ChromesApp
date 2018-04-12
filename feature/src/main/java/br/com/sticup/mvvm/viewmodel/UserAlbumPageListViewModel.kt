package br.com.sticup.mvvm.viewmodel

import br.com.sticup.mvvm.repository.UserAlbumPageRepository
import br.com.sticup.mvvm.viewmodel.data.AlbumPageList
import br.com.sticup.mvvm.viewmodel.data.UserAlbumPageList
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class UserAlbumPageListViewModel(val userAlbumPageRepository: UserAlbumPageRepository) {

    fun getUserAlbumPages(): Observable<UserAlbumPageList> {
        //Create the data for your UI, the albumPages lists and maybe some additional data needed as well
        return userAlbumPageRepository.getUserAlbumPages()
                //Drop DB data if we can fetch item fast enough from the API
                //to avoid UI flickers
                .debounce(400, TimeUnit.MILLISECONDS)
                .map {
                    Timber.d("Mapping albumPages to UIData...")
                    UserAlbumPageList(it.take(10), "Top 10 AlbumPages")
                }
                .onErrorReturn {
                    UserAlbumPageList(emptyList(), "An error occurred", it)
                }
    }
}