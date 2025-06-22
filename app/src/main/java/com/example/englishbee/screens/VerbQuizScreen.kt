package com.example.englishbee.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.englishbee.viewmodel.VerbQuizViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import com.example.englishbee.viewmodel.VerbQuizViewModelFactory
import com.example.englishbee.util.ScoreManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.englishbee.R

import com.example.englishbee.viewmodel.VerbQuizUiState
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun VerbQuizScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val vm: VerbQuizViewModel = viewModel(factory = VerbQuizViewModelFactory(context))
    val state: VerbQuizUiState = vm.uiState.collectAsState().value
    val points by ScoreManager.points.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.irregular_verbs)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
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
    ) { innerPadding ->

        val q = state.current

        if (q == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text("Infinitive: ${q.base}", style = MaterialTheme.typography.headlineMedium)

                OutlinedTextField(
                    value = state.pastInput,
                    onValueChange = vm::onPastChange,
                    label = { Text(stringResource(R.string.past_simple)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.partInput,
                    onValueChange = vm::onPartChange,
                    label = { Text(stringResource(R.string.past_participle)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = vm::check) {
                        Text(stringResource(R.string.check))
                    }

                    Button(onClick = vm::loadNext) {
                        Text(stringResource(R.string.next))
                    }
                }

                state.feedback?.let { correct ->
                    val msg = if (correct) stringResource(R.string.correct) else stringResource(R.string.wrong)
                    val color =
                        if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    Text(text = msg, color = color)
                }
            }
        }
    }
}