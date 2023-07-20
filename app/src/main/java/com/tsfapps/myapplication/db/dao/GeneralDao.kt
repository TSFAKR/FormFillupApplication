package com.tsfapps.myapplication.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tsfapps.myapplication.db.entity.GeneralEntity

@Dao
interface GeneralDao {

    @Query("SELECT * FROM general_table")
    suspend fun getAll(): List<GeneralEntity>

    @Insert
    suspend fun insertGeneral(generalEntity: GeneralEntity)

    @Delete
    suspend fun delete(generalEntity: GeneralEntity)

    @Update
    suspend fun updateBook(generalEntity: GeneralEntity)
}