package com.kele.android_room.model

import com.google.gson.annotations.SerializedName
import com.kele.android_room.database.Word


class WordResponse {
    @SerializedName("date")
    var date: String? = null
    @SerializedName("words")
    var words: List<Word>? = null
}