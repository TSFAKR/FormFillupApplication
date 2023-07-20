package com.tsfapps.myapplication.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "general_table")
data class GeneralEntity(@PrimaryKey(autoGenerate = true)
                         var generalId: Int,
                         @ColumnInfo(name = "questionnaireNo") var questionnaireNo: String?,
                         @ColumnInfo(name = "villageName") var villageName: String?,
                         @ColumnInfo(name = "blockName") var blockName: String?,
                         @ColumnInfo(name = "districtName") var districtName: String?,
                         @ColumnInfo(name = "thanaNo") var thanaNo: String?,
                         @ColumnInfo(name = "plotNo") var plotNo: String?,
                         @ColumnInfo(name = "affectedLand") var affectedLand: String?)
