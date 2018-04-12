package br.com.sticup.mvvm.repository.data.converter

import android.arch.persistence.room.TypeConverter
import br.com.sticup.mvvm.repository.data.AlbumPage
import br.com.sticup.mvvm.repository.data.UserAlbumPage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by gusta on 10/04/2018.
 */
class UserAlbumPageConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<UserAlbumPage>? {
        return if (value == null) null else Gson().fromJson(value, object : TypeToken<ArrayList<UserAlbumPage>>() {}.type)
    }

    @TypeConverter
    fun fromArrayList(userAlbumPage: ArrayList<UserAlbumPage>?): String? {
        return Gson().toJson(userAlbumPage)
    }
}