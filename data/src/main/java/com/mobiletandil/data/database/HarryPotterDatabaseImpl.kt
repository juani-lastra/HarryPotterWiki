package com.mobiletandil.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobiletandil.data.database.dao.HarryPotterDao
import com.mobiletandil.data.database.entity.DBRoomHeads
import com.mobiletandil.data.database.entity.DBRoomHouses
import com.mobiletandil.data.database.entity.DBRoomTraits
import com.mobiletandil.data.mapper.database.transformToHouse
import com.mobiletandil.data.mapper.database.transformToRoomHouse
import com.mobiletandil.data.utils.Converter
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.service.HarryPotterDatabase

@Database(entities = [DBRoomHouses::class, DBRoomTraits::class, DBRoomHeads::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class HarryPotterDatabaseImpl : RoomDatabase(), HarryPotterDatabase {

    abstract fun harryPotterDao(): HarryPotterDao

    override fun getHouse(houseId: String): House = harryPotterDao().getHouse(houseId).transformToHouse()

    override fun insertHouse(house: House) {
        harryPotterDao().insertHouse(house.transformToRoomHouse())
    }
}
