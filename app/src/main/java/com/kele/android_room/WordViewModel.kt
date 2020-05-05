package com.kele.android_room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.kele.android_room.database.Word
import com.kele.android_room.database.WordRoomDatabase
import com.kele.android_room.model.WordResponse
import com.kele.android_room.network.ApiResponse
import com.kele.android_room.network.WordApiManager
import com.kele.android_room.repository.WordRepositoryLocal
import com.kele.android_room.repository.WordRepositoryRemote

class WordViewModel(context: Application) : ViewModel() {

    private val _wordsResponse = MediatorLiveData<ApiResponse<WordResponse>>()
    private val _wordsLocal = MediatorLiveData<List<Word>>()
    val wordsResponse: LiveData<ApiResponse<WordResponse>> = _wordsResponse
    val wordsLocal: LiveData<List<Word>> = _wordsLocal

    private var repositoryRemote: WordRepositoryRemote
    private var repositoryLocal: WordRepositoryLocal

    init {
        val wordsDao = WordRoomDatabase.getDatabase(context).wordDao()
        val manager = WordApiManager()
        val api = manager.getService()
        repositoryRemote = WordRepositoryRemote(api)
        repositoryLocal = WordRepositoryLocal(wordsDao)
    }

    fun getWordsLocally() {
        _wordsLocal.addSource(
            repositoryLocal.getWordsFromLocal()
        ){
            _wordsLocal.value = it
        }
    }

    fun insertWordLocally(word: Word) {
        repositoryLocal.insert(word)
    }

    fun deleteAllWordsLocally() {
        repositoryLocal.delete()
    }

    fun getWordsRemote(date: String) {
        _wordsResponse.addSource(
            repositoryRemote.getWordsFromBackEnd(date)
        ){
            _wordsResponse.value = it
        }
    }

    fun sendWordsRemote(date: String, words: List<Word>) {
        repositoryRemote.updateWordsInBackEnd(date, words)
    }
}