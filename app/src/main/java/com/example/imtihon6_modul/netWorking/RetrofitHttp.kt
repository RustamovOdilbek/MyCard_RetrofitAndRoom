package com.example.imtihon6_modul.netWorking

import com.example.imtihon6_modul.netWorking.server.CardServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttp {

    companion object{

        private val TAG: String = RetrofitHttp::class.java.simpleName

        const val IS_TESTER: Boolean = true
        const val SERVER_DEVELOPMENT = "https://6232af0c8364d63035c194f8.mockapi.io/"
        const val SERVER_PRODUCTION = "https://6232af0c8364d63035c194f8.mockapi.io/"

        private fun server(): String{
            if (IS_TESTER){
                return SERVER_DEVELOPMENT
            }
            return SERVER_PRODUCTION
        }

        private fun getRetrofit(): Retrofit{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(server())
                .build()
        }

        val cardServer: CardServer = getRetrofit().create(CardServer::class.java)
    }
}