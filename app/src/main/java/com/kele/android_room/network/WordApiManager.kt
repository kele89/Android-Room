package com.kele.android_room.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WordApiManager {

    companion object {
        private const val URL = "https://api.openweathermap.org/data/2.5/"
    }

    fun getService(): Api {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(WordInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(Api::class.java)
    }

}