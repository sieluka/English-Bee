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

import com.example.englishbee.viewmodel.VerbQuizUiState
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun VerbQuizScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val vm: VerbQuizViewModel = viewModel(factory = VerbQuizViewModelFactory(context))
    val state: VerbQuizUiState = vm.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Irregular Verbs") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
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
                    label = { Text("Past Simple") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.partInput,
                    onValueChange = vm::onPartChange,
                    label = { Text("Past Participle") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = vm::check) {
                        Text("Check")
                    }

                    Button(onClick = vm::loadNext) {
                        Text("Next")
                    }
                }

                state.feedback?.let { correct ->
                    val msg = if (correct) "Correct ✅" else "Incorrect ❌"
                    val color =
                        if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    Text(text = msg, color = color)
                }
            }
        }
    }
}