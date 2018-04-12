package br.com.sticup.mvvm.viewmodel.data

import br.com.sticup.mvvm.repository.data.UserAlbumPage


data class UserAlbumPageList(val userAlbumPages: List<UserAlbumPage>, val message: String, val error: Throwable? = null)