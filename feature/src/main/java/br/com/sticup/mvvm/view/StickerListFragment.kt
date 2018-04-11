package br.com.sticup.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.sticup.mvvm.App
import br.com.sticup.mvvm.R
import br.com.sticup.mvvm.viewmodel.data.StickerList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.stickers_fragment.*
import timber.log.Timber
import java.net.ConnectException

class StickerListFragment : MvvmFragment() {

    val stickerListViewModel = App.injectStickerListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.stickers_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        subscribe(stickerListViewModel.getStickers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Received UIModel with ${it.stickers.size} stickers.")
                    showStickers(it)
                }, {
                    Timber.w(it)
                    showError()
                }))
    }

    fun showStickers(data: StickerList) {
        if (data.error == null) {
                stickersList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, data.stickers)
        } else if (data.error is ConnectException) {
            Timber.d("No connection, maybe inform stickers that data loaded from DB.")
        } else {
            showError()
        }
    }

    fun showError() {
        Toast.makeText(context, "An error occurred :(", Toast.LENGTH_SHORT).show()
    }
}