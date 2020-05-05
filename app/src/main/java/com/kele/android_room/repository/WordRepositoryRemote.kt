package com.kele.android_room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kele.android_room.database.Word
import com.kele.android_room.database.WordDao
import com.kele.android_room.model.WordResponse
import com.kele.android_room.network.Api
import com.kele.android_room.network.ApiResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class WordRepositoryRemote(private val api: Api) {

    companion object {
        private const val HTTP_STATUS_OK = 200
        private const val HTTP_STATUS_CREATED = 201
    }

    fun getWordsFromBackEnd(date: String): LiveData<ApiResponse<WordResponse>> {
        val result = MutableLiveData<ApiResponse<WordResponse>>()
        GlobalScope.launch {
            try {
                val apiResponse = api.getWords(date).awaitResponse()
                when (apiResponse.code()) {
                    HTTP_STATUS_OK, HTTP_STATUS_CREATED -> {
                        result.postValue(
                            ApiResponse.success(
                                apiResponse.body()
                            )
                        )
                    }
                    else -> {
                        result.postValue(
                            ApiResponse.error(
                                0
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                result.postValue(
                    ApiResponse.error(
                        0,
                        e.message
                    )
                )
            }
        }
        return result
    }

    fun updateWordsInBackEnd(date: String, words: List<Word>) {
        GlobalScope.launch {
            try {
                api.sendWords(date, words)
            } catch (e:Exception) {

            }
        }
    }
}