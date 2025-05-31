// repository/AuthRepository.kt
package com.example.englishbee.repository

import com.example.englishbee.data.UserDao
import com.example.englishbee.data.User
import com.example.englishbee.util.hashPassword

class AuthRepository(private val dao: UserDao) {


    suspend fun register(login: String, password: String): String? {
        if (dao.find(login) != null) return "Login already taken"
        dao.insert(User(login, hashPassword(password)))
        return null
    }


    suspend fun login(login: String, password: String): Boolean =
        dao.find(login)?.passwordHash == hashPassword(password)
}
