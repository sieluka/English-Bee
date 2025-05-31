// data/User.kt
package com.example.englishbee.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val login: String,
    val passwordHash: String
)
