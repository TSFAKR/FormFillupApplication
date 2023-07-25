package com.tsfapps.myapplication.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.tsfapps.myapplication.db.Converters
import org.json.JSONObject


//data class SendData(
//    val recordID: String, val dateTimeStamp: String, val sessionKey: String, val userID: String,
//    val data: JSONObject)

@Entity(tableName = "my_data_to_send")
data class SendDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @SerializedName("recordID")
    val recordID: String?,
    @SerializedName("dateTimeStamp")
    val dateTimeStamp: String?,
    @SerializedName("sessionKey")
    val sessionKey: String?,
    @SerializedName("userID")
    val userID: String?,
    @TypeConverters(Converters::class)
    @SerializedName("data")
    val data: List<SendData>
)
data class SendData(
    @SerializedName("villageName") var villageName: String?,
    @SerializedName("blockName") var blockName: String?,
    @SerializedName("districtName") var districtName: String?,
    @SerializedName("thanaNo") var thanaNo: String?,
    @SerializedName("plotNo") var plotNo: String?,
    @SerializedName("strOwnerShipLand") var strOwnerShipLand: String?,
    @SerializedName("arrTypeOfLand") var arrTypeOfLand: String?,
    @SerializedName("arrUseOfLand") var arrUseOfLand: String?,
    @SerializedName("affectedLand") var affectedLand: String?,
    @SerializedName("strTotalLand") var strTotalLand: String?,
    @SerializedName("strIrrigated") var strIrrigated: String?,
    @SerializedName("strNonIrrigated") var strNonIrrigated: String?,
    @SerializedName("strOtherLand") var strOtherLand: String?,
    @SerializedName("strTotal") var strTotal: String?,
    @SerializedName("strOwnershipSpecify") var strRbOwnerShipLand: String?,
    @SerializedName("strOwnerName") var strOwnerName: String?,
    @SerializedName("strProofOfIdentity") var strProofOfIdentity: String?,
    @SerializedName("strNameOfBank") var strNameOfBank: String?,
    @SerializedName("strAccountNo") var strAccountNo: String?,
    @SerializedName("strIfscCode") var strIfscCode: String?,
    @SerializedName("strFatherName") var strFatherName: String?,
    @SerializedName("strMarketRate") var strMarketRate: String?,
    @SerializedName("strRevenueRate") var strRevenueRate: String?,
    @SerializedName("strAgriculturalLaborerName1") var strAgriculturalLaborerName1: String?,
    @SerializedName("strAgriculturalLaborerName2") var strAgriculturalLaborerName2: String?,
    @SerializedName("strTenantLesseeName1") var strTenantLesseeName1: String?,
    @SerializedName("strTenantLesseeName2") var strTenantLesseeName2: String?,
    @SerializedName("strSharecropperName1") var strSharecropperName1: String?,
    @SerializedName("strSharecropperName2") var strSharecropperName2: String?
)

