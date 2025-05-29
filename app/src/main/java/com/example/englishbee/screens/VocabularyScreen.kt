package com.example.englishbee.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishbee.viewmodel.VocabularyViewModel
import com.example.englishbee.viewmodel.VocabularyViewModelFactory
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val vm: VocabularyViewModel = viewModel(factory = VocabularyViewModelFactory(context))
    val state by vm.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vocabulary Quiz") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        val current = state.current
        if (current == null) {
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
                Text("Translate: ${current.english}", style = MaterialTheme.typography.headlineMedium)

                OutlinedTextField(
                    value = state.userInput,
                    onValueChange = vm::onInputChange,
                    label = { Text("Your translation") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = vm::check) {
                        Text("Check")
                    }
                    Button(onClick = vm::next) {
                        Text("Next")
                    }
                }

                state.feedback?.let { correct ->
                    val msg = if (correct) "Correct ✅" else "Incorrect ❌"
                    val color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    Text(text = msg, color = color)
                }
            }
        }
    }
}
