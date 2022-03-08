package com.mobiletandil.data.database

import androidx.room.RoomDatabase
import com.mobiletandil.data.database.dao.HarryPotterDao

abstract class HarryPotterRoomDatabase : RoomDatabase() {

    abstract fun harryPotterDao(): HarryPotterDao
}
