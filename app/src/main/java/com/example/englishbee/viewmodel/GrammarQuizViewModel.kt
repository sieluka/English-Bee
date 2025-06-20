package com.example.englishbee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishbee.data.GrammarQuestion
import com.example.englishbee.repository.GrammarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.englishbee.util.ScoreManager

data class GrammarUiState(
    val current: GrammarQuestion? = null,
    val userInput: String = "",
    val feedback: Feedback? = null,
    val score: Int = 0
) { enum class Feedback { CORRECT, WRONG } }

class GrammarQuizViewModel(
    private val repo: GrammarRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GrammarUiState())
    val uiState: StateFlow<GrammarUiState> = _uiState

    /**  Talia potasowanych pytań  */
    private var deck: MutableList<GrammarQuestion> = mutableListOf()

    init { reloadDeck() }

    /**  Wczytaj wszystkie pytania i potasuj je.  */
    private fun reloadDeck() = viewModelScope.launch {
        deck = repo.all().shuffled().toMutableList()
        drawNext()
    }

    private fun drawNext() {
        if (deck.isEmpty()) reloadDeck() else {
            val q = deck.removeLast()
            _uiState.update {
                it.copy(
                    current = q,
                    userInput = "",
                    feedback = null
                )
            }
        }
    }



    fun onInputChange(txt: String) =
        _uiState.update { it.copy(userInput = txt) }

    fun check() {
        val q = _uiState.value.current ?: return
        val ok = q.answer.equals(_uiState.value.userInput.trim(), true)

        if (ok) ScoreManager.addPoint()

        _uiState.update {
            it.copy(
                feedback = if (ok)
                    GrammarUiState.Feedback.CORRECT
                else
                    GrammarUiState.Feedback.WRONG
            )
        }
    }

    fun nextQuestion() = drawNext()
}