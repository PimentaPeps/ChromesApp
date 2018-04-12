package br.com.sticup.mvvm.repository

import br.com.sticup.mvvm.repository.api.UserStickerApi
import br.com.sticup.mvvm.repository.data.UserSticker
import br.com.sticup.mvvm.repository.db.UserStickerDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserStickerRepository(val userStickerApi: UserStickerApi, val userStickerDao: UserStickerDao) {

    fun getUserStickers(): Observable<List<UserSticker>> {
        return Observable.concatArray(
                getUserStickersFromDb(),
                getUserStickersFromApi())
    }


    fun getUserStickersFromDb(): Observable<List<UserSticker>> {
        return userStickerDao.getUserSticker().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} stickers from DB...")
                }
    }

    fun getUserStickersFromApi(): Observable<List<UserSticker>> {
        return userStickerApi.getUserSticker()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} stickers from API...")
                    storeUserStickersInDb(it)
                }
    }

    fun storeUserStickersInDb(stickers: List<UserSticker>) {
        Observable.fromCallable { userStickerDao.insertAll(stickers) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${stickers.size} stickers from API in DB...")
                }
    }

}