// util/UserSession.kt
package com.example.englishbee.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object UserSession {
    private val _user = MutableStateFlow<String?>(null)
    val user: StateFlow<String?> = _user

    fun set(login: String) { _user.value = login }
    fun clear()           { _user.value = null }
}
