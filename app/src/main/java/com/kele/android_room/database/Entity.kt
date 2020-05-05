package com.kele.android_room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "word") val word: String) {
}