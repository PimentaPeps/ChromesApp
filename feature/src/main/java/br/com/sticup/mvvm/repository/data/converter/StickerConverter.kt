package br.com.sticup.mvvm.repository.data.converter

import android.arch.persistence.room.TypeConverter
import br.com.sticup.mvvm.repository.data.Sticker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by gusta on 10/04/2018.
 */
class StickerConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<Sticker>? {
        return if (value == null) null else Gson().fromJson(value, object : TypeToken<ArrayList<Sticker>>() {}.type)
    }

    @TypeConverter
    fun fromArrayList(sticker: ArrayList<Sticker>?): String? {
        return Gson().toJson(sticker)
    }
}