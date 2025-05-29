package com.example.englishbee.repository

import com.example.englishbee.model.DictionaryEntry
import com.example.englishbee.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DictionaryRepository {
    suspend fun search(word: String): Result<List<DictionaryEntry>> = withContext(Dispatchers.IO) {
        runCatching { RetrofitInstance.api.getEntries(word.lowercase()) }
    }
}