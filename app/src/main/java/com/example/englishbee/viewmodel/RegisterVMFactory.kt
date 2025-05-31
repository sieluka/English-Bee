package com.example.englishbee.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.englishbee.data.AppDatabase
import com.example.englishbee.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
class RegisterVMFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db   = AppDatabase.get(context, CoroutineScope(SupervisorJob()))
        val repo = AuthRepository(db.userDao())
        return RegisterViewModel(repo) as T
    }
}
