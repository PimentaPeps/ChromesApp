package br.com.sticup.mvvm.viewmodel.data

import br.com.sticup.mvvm.repository.data.UserAlbum

data class UserAlbumList(val userAlbums: List<UserAlbum>, val message: String, val error: Throwable? = null)