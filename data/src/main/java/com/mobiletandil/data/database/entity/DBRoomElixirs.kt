package com.mobiletandil.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBRoomElixirs(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String?
)
