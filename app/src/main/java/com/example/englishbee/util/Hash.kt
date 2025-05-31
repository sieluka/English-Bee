// util/Hash.kt
package com.example.englishbee.util

import java.security.MessageDigest

fun hashPassword(raw: String): String =
    MessageDigest.getInstance("SHA-256")
        .digest(raw.toByteArray())
        .joinToString("") { "%02x".format(it) }
