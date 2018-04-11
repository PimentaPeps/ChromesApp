package br.com.sticup.mvvm.repository

import br.com.sticup.mvvm.repository.api.AlbumPageApi
import br.com.sticup.mvvm.repository.data.AlbumPage
import br.com.sticup.mvvm.repository.db.AlbumPageDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AlbumPageRepository(val albumPageApi: AlbumPageApi, val albumPageDao: AlbumPageDao) {

    fun getAlbumPages(): Observable<List<AlbumPage>> {
        return Observable.concatArray(
                getAlbumPagesFromDb(),
                getAlbumPagesFromApi())
    }


    fun getAlbumPagesFromDb(): Observable<List<AlbumPage>> {
        return albumPageDao.getAlbumPage().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} albumPages from DB...")
                }
    }

    fun getAlbumPagesFromApi(): Observable<List<AlbumPage>> {
        return albumPageApi.getAlbumPage()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} albumPages from API...")
                    storeAlbumPagesInDb(it)
                }
    }

    fun storeAlbumPagesInDb(albumPages: List<AlbumPage>) {
        Observable.fromCallable { albumPageDao.insertAll(albumPages) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${albumPages.size} albumPages from API in DB...")
                }
    }

}