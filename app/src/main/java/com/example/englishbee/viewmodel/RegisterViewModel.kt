package com.example.englishbee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishbee.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class RegisterViewModel(private val repo: AuthRepository) : ViewModel() {

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error          // obserwowane w UI

    fun register(login: String, pw: String, onSuccess: () -> Unit) =
        viewModelScope.launch {
            _error.value = repo.register(login, pw) ?: run {
                onSuccess()          // przenosimy się np. do login screen
                null
            }
        }
}
