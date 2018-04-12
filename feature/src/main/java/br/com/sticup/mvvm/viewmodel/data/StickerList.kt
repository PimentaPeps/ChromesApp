package br.com.sticup.mvvm.viewmodel.data

import br.com.sticup.mvvm.repository.data.Sticker

data class StickerList(val stickers: List<Sticker>, val message: String, val error: Throwable? = null)