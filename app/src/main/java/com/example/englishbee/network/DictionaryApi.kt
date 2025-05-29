package com.example.englishbee.network

import com.example.englishbee.model.DictionaryEntry
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun getEntries(@Path("word") word: String): List<DictionaryEntry>
}