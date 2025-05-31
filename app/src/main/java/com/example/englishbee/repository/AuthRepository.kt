// repository/AuthRepository.kt
package com.example.englishbee.repository

import com.example.englishbee.data.UserDao
import com.example.englishbee.data.User
import com.example.englishbee.util.hashPassword

class AuthRepository(private val dao: UserDao) {

    /** returns null if login is free, otherwise error text */
    suspend fun register(login: String, password: String): String? {
        if (dao.find(login) != null) return "Login already taken"
        dao.insert(User(login, hashPassword(password)))
        return null
    }

    /** returns true if credentials ok */
    suspend fun login(login: String, password: String): Boolean =
        dao.find(login)?.passwordHash == hashPassword(password)
}
