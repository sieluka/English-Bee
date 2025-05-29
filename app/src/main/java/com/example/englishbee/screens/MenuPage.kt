package com.example.englishbee.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishbee.R


@Composable
fun ScreenTwo(
    onNavigateToScreen1: () -> Unit,
    onNavigateToScreen3: () -> Unit,
    onNavigateToGrammar: () -> Unit,
    onNavigateToVerbs: () -> Unit,
    onNavigateToVocabulary: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.choose),
            fontSize = 24.sp
        )
        Button(
            onClick =  onNavigateToGrammar,
            modifier = Modifier
                .padding(16.dp)
                .width(220.dp)
                .height(100.dp)
        ) {
            Text(
                text = stringResource(R.string.grammar)
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
            )
        }
        Button(
            onClick = onNavigateToScreen3,
            modifier = Modifier
                .padding(16.dp)
                .width(220.dp)
                .height(100.dp)
        ) {
            Text(
                text = stringResource(R.string.dictionary),
            )
        }
    }
}
// This file defines the second screen of the app, which contains a button to navigate back to ScreenOne.