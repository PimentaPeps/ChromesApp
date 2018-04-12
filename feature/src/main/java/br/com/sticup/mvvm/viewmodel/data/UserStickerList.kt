package br.com.sticup.mvvm.viewmodel.data

import br.com.sticup.mvvm.repository.data.UserSticker

data class UserStickerList(val userStickers: List<UserSticker>, val message: String, val error: Throwable? = null)