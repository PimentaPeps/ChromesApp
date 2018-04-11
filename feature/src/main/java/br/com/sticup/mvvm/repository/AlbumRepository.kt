package br.com.sticup.mvvm.repository

import br.com.sticup.mvvm.repository.api.AlbumApi
import br.com.sticup.mvvm.repository.data.Album
import br.com.sticup.mvvm.repository.db.AlbumDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AlbumRepository(val albumApi: AlbumApi, val albumDao: AlbumDao) {

    fun getAlbums(): Observable<List<Album>> {
        return Observable.concatArray(
                getAlbumsFromDb(),
                getAlbumsFromApi())
    }


    fun getAlbumsFromDb(): Observable<List<Album>> {
        return albumDao.getAlbum().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} albums from DB...")
                }
    }

    fun getAlbumsFromApi(): Observable<List<Album>> {
        return albumApi.getAlbum()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} albums from API...")
                    storeAlbumsInDb(it)
                }
    }

    fun storeAlbumsInDb(albums: List<Album>) {
        Observable.fromCallable { albumDao.insertAll(albums) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${albums.size} albums from API in DB...")
                }
    }

}