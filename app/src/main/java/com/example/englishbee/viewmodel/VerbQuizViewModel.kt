package com.example.englishbee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishbee.data.IrregularVerb
import com.example.englishbee.data.IrregularVerbDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.englishbee.util.ScoreManager
data class VerbQuizUiState(
    val current: IrregularVerb? = null,
    val pastInput: String = "",
    val partInput: String = "",
    val feedback: Boolean? = null
)
class VerbQuizViewModel(
    private val dao: IrregularVerbDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerbQuizUiState())
    val uiState: StateFlow<VerbQuizUiState> = _uiState

    /** Talia potasowanych czasowników */
    private var deck: MutableList<IrregularVerb> = mutableListOf()

    init {
        reloadDeck()
    }

    private fun reloadDeck() = viewModelScope.launch {
        deck = dao.all().shuffled().toMutableList()
        drawNext()
    }

    private fun drawNext() {
        if (deck.isEmpty()) {
            reloadDeck()  // nowa runda
        } else {
            val next = deck.removeAt(deck.lastIndex)
            _uiState.value = VerbQuizUiState(current = next)
        }
    }

    fun onPastChange(value: String) =
        _uiState.update { it.copy(pastInput = value) }

    fun onPartChange(value: String) =
        _uiState.update { it.copy(partInput = value) }

    fun check() {
        val q = _uiState.value.current ?: return
        val correct = q.past.equals(_uiState.value.pastInput.trim(), true) &&
                q.participle.equals(_uiState.value.partInput.trim(), true)
        _uiState.update { it.copy(feedback = correct) }
        if (correct) ScoreManager.addPoint()
    }

    fun loadNext() {
        drawNext()
    }
}