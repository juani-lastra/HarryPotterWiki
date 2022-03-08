package com.mobiletandil.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBRoomHouses(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "houseColours") val houseColours: String,
    @ColumnInfo(name = "founder") val founder: String,
    @ColumnInfo(name = "animal") val animal: String,
    @ColumnInfo(name = "element") val element: String,
    @ColumnInfo(name = "ghost") val ghost: String,
    @ColumnInfo(name = "commonRoom") val commonRoom: String,
    @ColumnInfo(name = "heads") val heads: List<DBRoomHeads>,
    @ColumnInfo(name = "traits") val traits: List<DBRoomTraits>
)
