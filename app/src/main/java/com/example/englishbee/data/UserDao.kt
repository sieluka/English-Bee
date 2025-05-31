// data/UserDao.kt
package com.example.englishbee.data

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    @Query("SELECT * FROM User WHERE login = :login LIMIT 1")
    suspend fun find(login: String): User?
}
