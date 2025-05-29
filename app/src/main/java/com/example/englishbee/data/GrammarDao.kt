// data/GrammarDao.kt
package com.example.englishbee.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface GrammarDao {
    @Query("SELECT * FROM grammar_questions")
    suspend fun allQuestions(): List<GrammarQuestion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<GrammarQuestion>)
}