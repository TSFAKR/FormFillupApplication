package com.tsfapps.myapplication.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tsfapps.myapplication.db.Converters
import com.tsfapps.myapplication.db.dao.GeneralDao
import com.tsfapps.myapplication.db.entity.GeneralEntity

@Database(entities = [GeneralEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun generalDao(): GeneralDao
}