package br.com.sticup.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.sticup.mvvm.App
import br.com.sticup.mvvm.R
import br.com.sticup.mvvm.viewmodel.data.UserAlbumPageList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.stickers_fragment.*
import timber.log.Timber
import java.net.ConnectException

class StickerListFragment : MvvmFragment() {

    val stickerListViewModel = App.injectStickerListViewModel()
    val userAlbumPageListViewModel = App.injectAlbumPageListViewModel()

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
                    //showStickers(it)
                }, {
                    Timber.w(it)
                    showError()
                }))

        subscribe(userAlbumPageListViewModel.getUserAlbumPages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Received UIModel with ${it.userAlbumPages.size} stickers.")
                    showStickers(it)
                }, {
                    Timber.w(it)
                    showError()
                }))
    }

    fun showStickers(data: UserAlbumPageList) {
        if (data.error == null) {
            if (!data.userAlbumPages.get(0).stickerList.get(0).count.equals(1)) framelayout_stickers_fragment_sticker1.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(1).count.equals(1)) framelayout_stickers_fragment_sticker2.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(2).count.equals(1)) framelayout_stickers_fragment_sticker3.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(3).count.equals(1)) framelayout_stickers_fragment_sticker4.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(4).count.equals(1)) framelayout_stickers_fragment_sticker5.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(5).count.equals(1)) framelayout_stickers_fragment_sticker6.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(6).count.equals(1)) framelayout_stickers_fragment_sticker7.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(7).count.equals(1)) framelayout_stickers_fragment_sticker8.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(8).count.equals(1)) framelayout_stickers_fragment_sticker9.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(9).count.equals(1)) framelayout_stickers_fragment_sticker10.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(10).count.equals(1)) framelayout_stickers_fragment_sticker11.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(11).count.equals(1)) framelayout_stickers_fragment_sticker12.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(12).count.equals(1)) framelayout_stickers_fragment_sticker1.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(13).count.equals(1)) framelayout_stickers_fragment_sticker13.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(14).count.equals(1)) framelayout_stickers_fragment_sticker14.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(15).count.equals(1)) framelayout_stickers_fragment_sticker15.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(16).count.equals(1)) framelayout_stickers_fragment_sticker16.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(17).count.equals(1)) framelayout_stickers_fragment_sticker17.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(18).count.equals(1)) framelayout_stickers_fragment_sticker18.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(19).count.equals(1)) framelayout_stickers_fragment_sticker19.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
            if (!data.userAlbumPages.get(0).stickerList.get(20).count.equals(1)) framelayout_stickers_fragment_sticker20.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))

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