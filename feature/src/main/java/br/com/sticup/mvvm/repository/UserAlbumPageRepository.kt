package br.com.sticup.mvvm.repository

import br.com.sticup.mvvm.repository.api.UserAlbumPageApi
import br.com.sticup.mvvm.repository.data.UserAlbumPage
import br.com.sticup.mvvm.repository.db.UserAlbumPageDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserAlbumPageRepository(val userAlbumPageApi: UserAlbumPageApi, val userAlbumPageDao: UserAlbumPageDao) {

    fun getUserAlbumPages(): Observable<List<UserAlbumPage>> {
        return Observable.concatArray(
                getUserAlbumPagesFromDb(),
                getUserAlbumPagesFromApi())
    }


    fun getUserAlbumPagesFromDb(): Observable<List<UserAlbumPage>> {
        return userAlbumPageDao.getUserAlbumPage().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} albumPages from DB...")
                }
    }

    fun getUserAlbumPagesFromApi(): Observable<List<UserAlbumPage>> {
        return userAlbumPageApi.getUserAlbumPage()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} albumPages from API...")
                    storeUserAlbumPagesInDb(it)
                }
    }

    fun storeUserAlbumPagesInDb(userAlbumPages: List<UserAlbumPage>) {
        Observable.fromCallable { userAlbumPageDao.insertAll(userAlbumPages) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${userAlbumPages.size} albumPages from API in DB...")
                }
    }

}