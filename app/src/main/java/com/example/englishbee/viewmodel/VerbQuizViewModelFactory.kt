package com.example.englishbee.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.englishbee.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class VerbQuizViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val scope = CoroutineScope(SupervisorJob())
        val dao = AppDatabase.get(context, scope).verbDao()
        return VerbQuizViewModel(dao) as T
    }
}