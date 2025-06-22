package com.example.englishbee.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishbee.R
import com.example.englishbee.model.DictionaryEntry
import com.example.englishbee.viewmodel.DictionaryViewModel


@Composable
fun ScreenThree(
    onNavigateToScreen2: () -> Unit,
    onNavigateToScreen1: () -> Unit,
    viewModel: DictionaryViewModel = viewModel()
) {
    val state by remember { viewModel::uiState }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 55.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onNavigateToScreen2
                )
                {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                TextField(
                    value = state.query,
                    onValueChange = viewModel::onQueryChange,
                    placeholder = { Text(stringResource(R.string.enter_word)) },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = viewModel::search,
                    enabled = state.query.isNotBlank()
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(
                    text = state.error ?: stringResource(R.string.error),
                    color = MaterialTheme.colorScheme.error
                )
                state.entries.isNotEmpty() -> LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.entries) { EntryCard(it) }
                }
            }
        }
    }
}

@Composable
private fun EntryCard(entry: DictionaryEntry) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text(entry.word, style = MaterialTheme.typography.headlineSmall)
            entry.phonetic?.let { Text(it) }

            entry.meanings.forEach { m ->
                m.partOfSpeech?.let {
                    Text(it, style = MaterialTheme.typography.labelLarge)
                }
                m.definitions.forEachIndexed { i, d ->
                    Text("${i + 1}. ${d.definition}")
                    d.example?.let { Text("• $it", style = MaterialTheme.typography.bodySmall) }
                }
            }
        }
    }
}
