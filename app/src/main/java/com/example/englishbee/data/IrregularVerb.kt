package com.example.englishbee.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "irregular_verbs")
data class IrregularVerb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val base: String,
    val past: String,
    val participle: String
)