package com.example.englishbee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishbee.data.VocabularyDao
import com.example.englishbee.data.VocabularyWord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.englishbee.util.ScoreManager

data class VocabularyUiState(
    val current: VocabularyWord? = null,
    val userInput: String = "",
    val feedback: Boolean? = null
)

class VocabularyViewModel(private val dao: VocabularyDao) : ViewModel() {
    private val _uiState = MutableStateFlow(VocabularyUiState())
    val uiState: StateFlow<VocabularyUiState> = _uiState

    private var deck = mutableListOf<VocabularyWord>()

    init {
        viewModelScope.launch {
            deck = dao.getAll().shuffled().toMutableList()
            drawNext()
        }
    }

    private fun drawNext() {
        if (deck.isEmpty()) {
            viewModelScope.launch {
                deck = dao.getAll().shuffled().toMutableList()
                drawNext()
            }
            return
        }

        val next = deck.removeAt(deck.lastIndex)
        _uiState.value = VocabularyUiState(current = next)
    }

    fun onInputChange(value: String) {
        _uiState.value = _uiState.value.copy(userInput = value)
    }

    fun check() {
        val current = _uiState.value.current ?: return
        val correct = current.polish.equals(_uiState.value.userInput.trim(), ignoreCase = true)
        _uiState.value = _uiState.value.copy(feedback = correct)
        if (correct) ScoreManager.addPoint()
    }

    fun next() = drawNext()
}
//change 1