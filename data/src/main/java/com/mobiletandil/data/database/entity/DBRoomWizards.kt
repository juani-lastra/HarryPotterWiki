package com.mobiletandil.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBRoomWizards(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "elixirs") val elixirs: List<DBRoomElixirs>?,
)
