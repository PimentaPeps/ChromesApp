package br.com.sticup.mvvm.repository.data.converter

import android.arch.persistence.room.TypeConverter
import br.com.sticup.mvvm.repository.data.Album
import br.com.sticup.mvvm.repository.data.AlbumPage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by gusta on 10/04/2018.
 */
class AlbumPageConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<Album>? {
        return if (value == null) null else Gson().fromJson(value, object : TypeToken<ArrayList<Album>>() {}.type)
    }

    @TypeConverter
    fun fromArrayList(album: ArrayList<Album>?): String? {
        return Gson().toJson(album)
    }
}