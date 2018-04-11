package br.com.sticup.mvvm.viewmodel

import br.com.sticup.mvvm.repository.AlbumRepository
import br.com.sticup.mvvm.viewmodel.data.AlbumList
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class AlbumListViewModel(val albumRepository: AlbumRepository) {

    fun getAlbums(): Observable<AlbumList> {
        //Create the data for your UI, the albums lists and maybe some additional data needed as well
        return albumRepository.getAlbums()
                //Drop DB data if we can fetch item fast enough from the API
                //to avoid UI flickers
                .debounce(400, TimeUnit.MILLISECONDS)
                .map {
                    Timber.d("Mapping albums to UIData...")
                    AlbumList(it.take(10), "Top 10 Albums")
                }
                .onErrorReturn {
                    AlbumList(emptyList(), "An error occurred", it)
                }
    }
}