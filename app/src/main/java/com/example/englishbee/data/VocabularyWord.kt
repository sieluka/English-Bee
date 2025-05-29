package com.example.englishbee.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VocabularyWord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val english: String,
    val polish: String
)