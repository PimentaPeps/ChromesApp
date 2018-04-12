package br.com.sticup.mvvm.repository

import br.com.sticup.mvvm.repository.api.UserAlbumApi
import br.com.sticup.mvvm.repository.data.UserAlbum
import br.com.sticup.mvvm.repository.db.UserAlbumDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserAlbumRepository(val userUserAlbumApi: UserAlbumApi, val userUserAlbumDao: UserAlbumDao) {

    fun getUserAlbums(): Observable<List<UserAlbum>> {
        return Observable.concatArray(
                getUserAlbumsFromDb(),
                getUserAlbumsFromApi())
    }


    fun getUserAlbumsFromDb(): Observable<List<UserAlbum>> {
        return userUserAlbumDao.getUserAlbum().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} userUserAlbums from DB...")
                }
    }

    fun getUserAlbumsFromApi(): Observable<List<UserAlbum>> {
        return userUserAlbumApi.getUserAlbum()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} userUserAlbums from API...")
                    storeUserAlbumsInDb(it)
                }
    }

    fun storeUserAlbumsInDb(userUserAlbums: List<UserAlbum>) {
        Observable.fromCallable { userUserAlbumDao.insertAll(userUserAlbums) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${userUserAlbums.size} userUserAlbums from API in DB...")
                }
    }

}