package com.kele.android_room.network

import com.kele.android_room.database.Word
import com.kele.android_room.model.WordResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @GET("word?")
    fun getWords (@Query("date") date: String): Call<WordResponse>

    @POST("words?")
    fun sendWords (@Query("date") date: String,
                                  @Query("words") words: List<Word>?)
}