package com.example.englishbee.repository

import com.example.englishbee.data.GrammarDao
import com.example.englishbee.data.GrammarQuestion

class GrammarRepository(private val dao: GrammarDao) {

    suspend fun all(): List<GrammarQuestion> = dao.allQuestions()
}