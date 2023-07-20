package com.tsfapps.myapplication.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tsfapps.myapplication.db.dao.GeneralDao
import com.tsfapps.myapplication.db.entity.GeneralEntity

@Database(entities = [GeneralEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun generalDao(): GeneralDao
}