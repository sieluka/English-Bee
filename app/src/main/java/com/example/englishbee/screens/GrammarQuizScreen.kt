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

import kotlinx.coroutines.MainScope

import com.example.englishbee.util.ScoreManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.englishbee.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarQuizScreen(
    onBack: () -> Unit
) {

    val context = LocalContext.current
    val vm: GrammarQuizViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db = AppDatabase.get(context, MainScope())
                val repo = GrammarRepository(db.grammarDao())
                @Suppress("UNCHECKED_CAST")
                return GrammarQuizViewModel(repo) as T
            }
        }
    )

    val state by vm.uiState.collectAsState()
    val points by ScoreManager.points.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.grammar_quiz)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    Text(
                        text = "Score: $points",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(end = 16.dp)
                    )
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

            state.current?.let { q ->
                Text(
                    q.sentence.replace("____", "_____"),
                    style = MaterialTheme.typography.headlineSmall
                )
            }


            OutlinedTextField(
                value = state.userInput,
                onValueChange = vm::onInputChange,
                label = { Text(stringResource(R.string.your_answer)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


            when (state.feedback) {
                GrammarUiState.Feedback.CORRECT ->
                    Text(stringResource(R.string.correct), color = MaterialTheme.colorScheme.primary)
                GrammarUiState.Feedback.WRONG ->
                    Text(stringResource(R.string.wrong), color = MaterialTheme.colorScheme.error)
                null -> {}
            }


            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = vm::check) { Text(stringResource(R.string.check)) }
                Button(onClick = vm::nextQuestion) { Text(stringResource(R.string.next)) }
            }

            Spacer(Modifier.weight(1f))

        }
    }
}