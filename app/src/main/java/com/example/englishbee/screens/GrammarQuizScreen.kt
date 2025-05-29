package com.example.englishbee.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishbee.data.AppDatabase
import com.example.englishbee.repository.GrammarRepository
import com.example.englishbee.viewmodel.GrammarQuizViewModel
import com.example.englishbee.viewmodel.GrammarUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.MainScope
import androidx.compose.material3.TopAppBar
@OptIn(ExperimentalMaterial3Api::class)             // ← DODAJ TO
@Composable
fun GrammarQuizScreen(
    onBack: () -> Unit
) {
    /* ---------- ViewModel bez Hilt ---------- */
    val context = LocalContext.current
    val vm: GrammarQuizViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db = AppDatabase.get(context, MainScope())       // 1) db
                val repo = GrammarRepository(db.grammarDao())        // 2) repo
                @Suppress("UNCHECKED_CAST")
                return GrammarQuizViewModel(repo) as T               // 3) vm
            }
        }
    )

    val state by vm.uiState.collectAsState()

    /* ---------- UI ---------- */
    Scaffold(
        topBar = {
            TopAppBar(                               // lub SmallTopAppBar
                title = { Text("Grammar Quiz") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)
        ) {
            /* Pytanie */
            state.current?.let { q ->
                Text(
                    q.sentence.replace("____", "_____"),
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            /* Pole odpowiedzi */
            OutlinedTextField(
                value = state.userInput,
                onValueChange = vm::onInputChange,
                label = { Text("Your answer") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            /* Informacja zwrotna */
            when (state.feedback) {
                GrammarUiState.Feedback.CORRECT ->
                    Text("✅ Correct!", color = MaterialTheme.colorScheme.primary)
                GrammarUiState.Feedback.WRONG ->
                    Text("❌ Wrong!", color = MaterialTheme.colorScheme.error)
                null -> {}
            }

            /* Przyciski */
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = vm::check) { Text("Check") }
                Button(onClick = vm::nextQuestion) { Text("Next") }
            }

            Spacer(Modifier.weight(1f))
            Text("Score: ${state.score}")
        }
    }
}