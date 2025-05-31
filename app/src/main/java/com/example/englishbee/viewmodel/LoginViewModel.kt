package com.example.englishbee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishbee.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.englishbee.util.UserSession

class LoginViewModel(private val repo: AuthRepository) : ViewModel() {



    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(login: String, pw: String, onSuccess: () -> Unit) =
        viewModelScope.launch {
            if (repo.login(login, pw)) {
                UserSession.set(login)        //️ zapisz login
                onSuccess()
            } else {
                _error.value = "Wrong login or password"
            }
        }
}