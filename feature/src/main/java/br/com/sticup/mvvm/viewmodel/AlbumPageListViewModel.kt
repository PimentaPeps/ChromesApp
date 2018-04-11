package br.com.sticup.mvvm.viewmodel

import br.com.sticup.mvvm.repository.AlbumPageRepository
import br.com.sticup.mvvm.viewmodel.data.AlbumPageList
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class AlbumPageListViewModel(val albumPageRepository: AlbumPageRepository) {

    fun getAlbumPages(): Observable<AlbumPageList> {
        //Create the data for your UI, the albumPages lists and maybe some additional data needed as well
        return albumPageRepository.getAlbumPages()
                //Drop DB data if we can fetch item fast enough from the API
                //to avoid UI flickers
                .debounce(400, TimeUnit.MILLISECONDS)
                .map {
                    Timber.d("Mapping albumPages to UIData...")
                    AlbumPageList(it.take(10), "Top 10 AlbumPages")
                }
                .onErrorReturn {
                    AlbumPageList(emptyList(), "An error occurred", it)
                }
    }
}