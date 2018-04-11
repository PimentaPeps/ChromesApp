package br.com.sticup.mvvm.repository

import br.com.sticup.mvvm.repository.api.StickerApi
import br.com.sticup.mvvm.repository.data.Sticker
import br.com.sticup.mvvm.repository.db.StickerDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class StickerRepository(val stickerApi: StickerApi, val stickerDao: StickerDao) {

    fun getStickers(): Observable<List<Sticker>> {
        return Observable.concatArray(
                getStickersFromDb(),
                getStickersFromApi())
    }


    fun getStickersFromDb(): Observable<List<Sticker>> {
        return stickerDao.getSticker().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} stickers from DB...")
                }
    }

    fun getStickersFromApi(): Observable<List<Sticker>> {
        return stickerApi.getSticker()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} stickers from API...")
                    storeStickersInDb(it)
                }
    }

    fun storeStickersInDb(stickers: List<Sticker>) {
        Observable.fromCallable { stickerDao.insertAll(stickers) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${stickers.size} stickers from API in DB...")
                }
    }

}