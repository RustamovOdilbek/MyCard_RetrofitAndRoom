package com.example.imtihon6_modul.netWorking.server

import com.example.imtihon6_modul.modul.CardSer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CardServer {

    @GET("card")
    fun getAllCards(): Call<ArrayList<CardSer>>

    @POST("card")
    fun createCard(@Body cardServer: CardSer): Call<CardSer>
}