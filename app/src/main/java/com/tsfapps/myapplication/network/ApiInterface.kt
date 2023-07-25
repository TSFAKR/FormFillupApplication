package com.tsfapps.myapplication.network

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("login")
    suspend fun login(@Body request: RequestLogin): ResponseLogin
    @POST("pushData")
    suspend fun sendData(@Body request: SendData): ResponseSendData
}