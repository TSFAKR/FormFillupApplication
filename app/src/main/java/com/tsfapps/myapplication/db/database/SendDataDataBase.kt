package com.tsfapps.myapplication.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tsfapps.myapplication.db.Converters
import com.tsfapps.myapplication.db.dao.SendDataDao
import com.tsfapps.myapplication.db.entity.SendDataEntity

@Database(entities = [SendDataEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class SendDataDataBase: RoomDatabase() {
    abstract fun sendDataDao(): SendDataDao
}