package com.tsfapps.myapplication.network

import org.json.JSONObject

data class RequestLogin(val userID: String, val password: String)
data class ResponseLogin(val success: Boolean, val sessionKey: String)
data class SendData(
    val recordID: String, val dateTimeStamp: String, val sessionKey: String, val userID: String,
    val data: JSONObject)

data class ResponseSendData(val success: Boolean, val errorMessage: String, val syncRecordID: String)