package com.tsfapps.myapplication.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tsfapps.myapplication.db.entity.SendDataEntity

@Dao
interface SendDataDao {

    @Query("SELECT * FROM my_data_to_send")
    suspend fun getAllSendData(): List<SendDataEntity>

    @Insert
    suspend fun insertSendData(sendDataEntity: SendDataEntity)

    @Delete
    suspend fun deleteSendData(sendDataEntity: SendDataEntity)

    @Update
    suspend fun updateSendData(sendDataEntity: SendDataEntity)
}