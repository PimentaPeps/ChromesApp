package br.com.sticup.mvvm.repository.data.converter

import android.arch.persistence.room.TypeConverter
import br.com.sticup.mvvm.repository.data.Sticker
import br.com.sticup.mvvm.repository.data.User
import br.com.sticup.mvvm.repository.data.UserSticker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by gusta on 10/04/2018.
 */
class UserConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<User>? {
        return if (value == null) null else Gson().fromJson(value, object : TypeToken<ArrayList<User>>() {}.type)
    }

    @TypeConverter
    fun fromArrayList(user: ArrayList<User>?): String? {
        return Gson().toJson(user)
    }
}