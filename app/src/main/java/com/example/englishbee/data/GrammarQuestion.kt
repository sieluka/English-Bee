// data/GrammarQuestion.kt
package com.example.englishbee.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grammar_questions")
data class GrammarQuestion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sentence: String,      // "I ____ to school yesterday."
    val answer: String         // "went"
)