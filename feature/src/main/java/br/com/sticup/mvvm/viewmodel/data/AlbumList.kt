package br.com.sticup.mvvm.viewmodel.data

import br.com.sticup.mvvm.repository.data.Album

data class AlbumList(val albums: List<Album>, val message: String, val error: Throwable? = null)