package com.tsfapps.myapplication.network

import kotlinx.serialization.json.Json

data class ModelClass(val userID: String, val password: String)

data class ResponseData(val success: Boolean, val sessionKey: String)
data class SendData(val recordID: String, val dateTimeStamp: String, val sessionKey: String, val userID: String,
                    val data: Json)