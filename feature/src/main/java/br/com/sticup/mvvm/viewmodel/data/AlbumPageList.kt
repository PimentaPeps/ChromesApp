package br.com.sticup.mvvm.viewmodel.data

import br.com.sticup.mvvm.repository.data.AlbumPage


data class AlbumPageList(val albumPages: List<AlbumPage>, val message: String, val error: Throwable? = null)