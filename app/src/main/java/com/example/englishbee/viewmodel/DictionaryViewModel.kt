package com.example.englishbee.viewmodel
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishbee.model.DictionaryEntry
import com.example.englishbee.repository.DictionaryRepository
import kotlinx.coroutines.launch

data class DictionaryUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val entries: List<DictionaryEntry> = emptyList(),
    val error: String? = null
)

class DictionaryViewModel(
    private val repo: DictionaryRepository = DictionaryRepository()
) : ViewModel() {

    var uiState by mutableStateOf(DictionaryUiState())
        private set

    fun onQueryChange(text: String) {
        uiState = uiState.copy(query = text)
    }

    fun search() {
        // ignorujemy puste wpisy
        if (uiState.query.isBlank()) return

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            Log.d("DictionaryVM", "Szukam: ${uiState.query.trim()}")

            repo.search(uiState.query.trim()).fold(
                onSuccess = {
                    Log.d("DictionaryVM", "OK – rekordów: ${it.size}")
                    uiState = uiState.copy(
                        entries = it,
                        isLoading = false
                    )
                },
                onFailure = { e ->
                    Log.e("DictionaryVM", "Błąd: ${e.message}", e)
                    uiState = uiState.copy(
                        error = e.message ?: "Unknown error",
                        entries = emptyList(),
                        isLoading = false
                    )
                }
            )
        }
    }
}