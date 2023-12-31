package com.tsfapps.myapplication.db.entity

import android.widget.CheckBox
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
                         @ColumnInfo(name = "strOwnerShipLand") var strOwnerShipLand: String?,
                         @ColumnInfo(name = "arrTypeOfLand") var arrTypeOfLand: String?,
                         @ColumnInfo(name = "arrUseOfLand") var arrUseOfLand: String?,
                         @ColumnInfo(name = "affectedLand") var affectedLand: String?,
                         @ColumnInfo(name = "strTotalLand") var strTotalLand: String?,
                         @ColumnInfo(name = "strIrrigated") var strIrrigated: String?,
                         @ColumnInfo(name = "strNonIrrigated") var strNonIrrigated: String?,
                         @ColumnInfo(name = "strOtherLand") var strOtherLand: String?,
                         @ColumnInfo(name = "strTotal") var strTotal: String?,
                         @ColumnInfo(name = "strOwnershipSpecify") var strRbOwnerShipLand: String?,
                         @ColumnInfo(name = "strOwnerName") var strOwnerName: String?,
                         @ColumnInfo(name = "strProofOfIdentity") var strProofOfIdentity: String?,
                         @ColumnInfo(name = "strNameOfBank") var strNameOfBank: String?,
                         @ColumnInfo(name = "strAccountNo") var strAccountNo: String?,
                         @ColumnInfo(name = "strIfscCode") var strIfscCode: String?,
                         @ColumnInfo(name = "strFatherName") var strFatherName: String?,
                         @ColumnInfo(name = "strMarketRate") var strMarketRate: String?,
                         @ColumnInfo(name = "strRevenueRate") var strRevenueRate: String?,
                         @ColumnInfo(name = "strAgriculturalLaborerName1") var strAgriculturalLaborerName1: String?,
                         @ColumnInfo(name = "strAgriculturalLaborerName2") var strAgriculturalLaborerName2: String?,
                         @ColumnInfo(name = "strTenantLesseeName1") var strTenantLesseeName1: String?,
                         @ColumnInfo(name = "strTenantLesseeName2") var strTenantLesseeName2: String?,
                         @ColumnInfo(name = "strSharecropperName1") var strSharecropperName1: String?,
                         @ColumnInfo(name = "strSharecropperName2") var strSharecropperName2: String?,

                         // Fields from the second form

//                         @ColumnInfo(name = "marketValue") var marketValue: String?,
//
//                         @ColumnInfo(name = "typeOfBusiness") var typeOfBusiness: String?,
//
//                         @ColumnInfo(name = "tenantName1") var tenantName1: String?,
//                         @ColumnInfo(name = "tenantName2") var tenantName2: String?,
//                         @ColumnInfo(name = "tenantName3") var tenantName3: String?,
//                         @ColumnInfo(name = "tenantName4") var tenantName4: String?,
//
//                         @ColumnInfo(name = "commercialEmployeeName1") var commercialEmployeeName1: String?,
//                         @ColumnInfo(name = "commercialEmployeeName2") var commercialEmployeeName2: String?,
//                         @ColumnInfo(name = "commercialEmployeeName3") var commercialEmployeeName3: String?,
//                         @ColumnInfo(name = "commercialEmployeeName4") var commercialEmployeeName4: String?,
//
//                         @ColumnInfo(name = "residentialEmployeeName1") var residentialEmployeeName1: String?,
//                         @ColumnInfo(name = "residentialEmployeeName2") var residentialEmployeeName2: String?,
//
//                         @ColumnInfo(name = "fruitBearing") var fruitBearing: String?,
//                         @ColumnInfo(name = "nonFruitBearing") var nonFruitBearing: String?,

                         )
