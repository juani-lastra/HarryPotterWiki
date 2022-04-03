package com.mobiletandil.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobiletandil.data.database.dao.HarryPotterDao
import com.mobiletandil.data.database.entity.DBRoomElixirs
import com.mobiletandil.data.database.entity.DBRoomHeads
import com.mobiletandil.data.database.entity.DBRoomHouses
import com.mobiletandil.data.database.entity.DBRoomSpells
import com.mobiletandil.data.database.entity.DBRoomTraits
import com.mobiletandil.data.database.entity.DBRoomWizards
import com.mobiletandil.data.mapper.database.transformToHouse
import com.mobiletandil.data.mapper.database.transformToRoomHouse
import com.mobiletandil.data.mapper.database.transformToRoomSpells
import com.mobiletandil.data.mapper.database.transformToRoomWizards
import com.mobiletandil.data.mapper.database.transformToSpells
import com.mobiletandil.data.mapper.database.transformToWizards
import com.mobiletandil.data.utils.Converter
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.entity.Spells
import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.domain.service.HarryPotterDatabase

@Database(
    entities = [
        DBRoomSpells::class,
        DBRoomWizards::class,
        DBRoomElixirs::class,
        DBRoomHouses::class,
        DBRoomTraits::class,
        DBRoomHeads::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class HarryPotterDatabaseImpl : RoomDatabase(), HarryPotterDatabase {

    abstract fun harryPotterDao(): HarryPotterDao

    override fun getHouse(houseId: String): House = harryPotterDao().getHouse(houseId).transformToHouse()

    override fun insertHouse(house: House) {
        harryPotterDao().insertHouse(house.transformToRoomHouse())
    }

    override fun getAllWizards(): List<Wizards> = harryPotterDao().getAllWizards().map { it.transformToWizards() }

    override fun insertWizards(wizards: List<Wizards>) {
        harryPotterDao().insertWizards(wizards.map { it.transformToRoomWizards() })
    }

    override fun getSpells(): List<Spells> = harryPotterDao().getSpells().map { it.transformToSpells() }

    override fun insertSpells(spells: List<Spells>) {
        harryPotterDao().insertSpells(spells.map { it.transformToRoomSpells() })
    }
}
