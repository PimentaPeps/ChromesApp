package br.com.sticup.mvvm.repository.data.converter

import android.arch.persistence.room.TypeConverter
import br.com.sticup.mvvm.repository.data.AlbumPage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by gusta on 10/04/2018.
 */
class AlbumConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<AlbumPage>? {
        return if (value == null) null else Gson().fromJson(value, object : TypeToken<ArrayList<AlbumPage>>() {}.type)
    }

    @TypeConverter
    fun fromArrayList(albumPage: ArrayList<AlbumPage>?): String? {
        return Gson().toJson(albumPage)
    }
}