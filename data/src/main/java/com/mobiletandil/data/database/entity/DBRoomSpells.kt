package com.mobiletandil.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBRoomSpells(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "incantation") val incantation: String?,
    @ColumnInfo(name = "effect") val effect: String?,
    @ColumnInfo(name = "canBeVerbal") val canBeVerbal: Boolean?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "light") val light: String?,
    @ColumnInfo(name = "creator") val creator: String?,
)
