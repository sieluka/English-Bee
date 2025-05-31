package com.example.englishbee.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ScoreManager {
    private val _points = MutableStateFlow(0)
    val points: StateFlow<Int> = _points

    fun addPoint() { _points.value += 1 }
    fun reset()    { _points.value = 0 }
}
//zmiana