package com.example.englishbee.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishbee.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTwo(
    onNavigateToDictionary: () -> Unit,
    onNavigateToGrammar: () -> Unit,
    onNavigateToVerbs: () -> Unit,
    onNavigateToVocabulary: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.choose),
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onNavigateToGrammar,
                modifier = Modifier
                    .padding(16.dp)
                    .width(220.dp)
                    .height(100.dp)
            ) {
                Text(
                    text = stringResource(R.string.grammar),
                    fontSize = 24.sp
                )
            }
            Button(
                onClick = onNavigateToVerbs,
                modifier = Modifier
                    .padding(16.dp)
                    .width(220.dp)
                    .height(100.dp)
            ) {
                Text(
                    text = stringResource(R.string.verbs),
                    fontSize = 24.sp
                )
            }
            Button(
                onClick = onNavigateToVocabulary,
                modifier = Modifier
                    .padding(16.dp)
                    .width(220.dp)
                    .height(100.dp)
            ) {
                Text(
                    text = stringResource(R.string.vocabulary),
                    fontSize = 24.sp
                )
            }
            Button(
                onClick = onNavigateToDictionary,
                modifier = Modifier
                    .padding(16.dp)
                    .width(220.dp)
                    .height(100.dp)
            ) {
                Text(
                    text = stringResource(R.string.dictionary),
                    fontSize = 24.sp
                )
            }
        }
    }
}
// This file defines the second screen of the app, which contains a button to navigate back to ScreenOne.