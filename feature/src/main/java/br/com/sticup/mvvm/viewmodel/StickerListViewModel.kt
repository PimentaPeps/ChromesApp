package br.com.sticup.mvvm.viewmodel

import br.com.sticup.mvvm.repository.StickerRepository
import br.com.sticup.mvvm.viewmodel.data.StickerList
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class StickerListViewModel(val stickerRepository: StickerRepository) {

    fun getStickers(): Observable<StickerList> {
        //Create the data for your UI, the stickers lists and maybe some additional data needed as well
        return stickerRepository.getStickers()
                //Drop DB data if we can fetch item fast enough from the API
                //to avoid UI flickers
                .debounce(400, TimeUnit.MILLISECONDS)
                .map {
                    Timber.d("Mapping stickers to UIData...")
                    StickerList(it.take(10), "Top 10 Stickers")
                }
                .onErrorReturn {
                    StickerList(emptyList(), "An error occurred", it)
                }
    }
}