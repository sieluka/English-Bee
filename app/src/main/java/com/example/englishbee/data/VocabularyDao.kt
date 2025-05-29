package com.example.englishbee.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM VocabularyWord")
    suspend fun getAll(): List<VocabularyWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(words: List<VocabularyWord>)
}
