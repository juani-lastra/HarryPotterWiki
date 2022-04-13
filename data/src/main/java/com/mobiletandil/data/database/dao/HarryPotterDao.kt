package com.mobiletandil.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mobiletandil.data.database.entity.DBRoomHouses
import com.mobiletandil.data.database.entity.DBRoomSpells
import com.mobiletandil.data.database.entity.DBRoomWizards

@Dao
interface HarryPotterDao {

    @Insert(onConflict = REPLACE)
    fun insertHouse(house: DBRoomHouses)

    @Insert(onConflict = REPLACE)
    fun insertWizards(wizards: List<DBRoomWizards>)

    @Insert(onConflict = REPLACE)
    fun insertSpells(wizards: List<DBRoomSpells>)

    @Query("SELECT * FROM DBRoomHouses WHERE id = :houseId")
    fun getHouse(houseId: String): DBRoomHouses

    @Query("SELECT * FROM DBRoomWizards")
    fun getAllWizards(): List<DBRoomWizards>

    @Query("SELECT * FROM DBRoomWizards WHERE id = :wizardID")
    fun getOneWizard(wizardID: String): DBRoomWizards

    @Query("SELECT * FROM DBRoomSpells")
    fun getSpells(): List<DBRoomSpells>
}
