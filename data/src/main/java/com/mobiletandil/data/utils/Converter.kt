package com.mobiletandil.data.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobiletandil.data.database.entity.DBRoomElixirs
import com.mobiletandil.data.database.entity.DBRoomHeads
import com.mobiletandil.data.database.entity.DBRoomTraits

class Converter {
    @TypeConverter
    fun List<DBRoomHeads>.fromRoomHeads(): String = Gson().toJson(this, object : TypeToken<List<DBRoomHeads>>() {}.type)

    @TypeConverter
    fun String.toRoomHeads(): List<DBRoomHeads> = Gson().fromJson(this, object : TypeToken<List<DBRoomHeads>>() {}.type)

    @TypeConverter
    fun List<DBRoomTraits>.fromRoomTraits(): String = Gson().toJson(this, object : TypeToken<List<DBRoomTraits>>() {}.type)

    @TypeConverter
    fun String.toRoomTraits(): List<DBRoomTraits> = Gson().fromJson(this, object : TypeToken<List<DBRoomTraits>>() {}.type)

    @TypeConverter
    fun List<DBRoomElixirs>.fromRoomElixirs(): String = Gson().toJson(this, object : TypeToken<List<DBRoomElixirs>>() {}.type)

    @TypeConverter
    fun String.toRoomElixirs(): List<DBRoomElixirs> = Gson().fromJson(this, object : TypeToken<List<DBRoomElixirs>>() {}.type)
}
