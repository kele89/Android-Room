package com.kele.android_room.repository

import androidx.lifecycle.LiveData
import com.kele.android_room.database.Word
import com.kele.android_room.database.WordDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class WordRepositoryLocal(private val wordDao: WordDao) {

    fun getWordsFromLocal(): LiveData<List<Word>> {
        return wordDao.getAlphabetizedWords()
    }

    fun insert(word: Word) {
        GlobalScope.launch {
            try {
                wordDao.insert(word)
            } catch (e: Exception) {

            }
        }
    }

    fun delete() {
        GlobalScope.launch {
            try {
                wordDao.deleteAll()
            } catch (e: Exception) {

            }
        }
    }

}