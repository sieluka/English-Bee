package com.example.englishbee.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IrregularVerbDao {

    @Query("SELECT * FROM irregular_verbs ORDER BY RANDOM() LIMIT 1")
    suspend fun random(): IrregularVerb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<IrregularVerb>)
    @Query("SELECT * FROM irregular_verbs")
    suspend fun all(): List<IrregularVerb>
}