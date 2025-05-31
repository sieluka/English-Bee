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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishbee.R
import com.example.englishbee.util.ScoreManager
import com.example.englishbee.util.UserSession

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTwo(
    onNavigateToDictionary: () -> Unit,
    onNavigateToGrammar: () -> Unit,
    onNavigateToVerbs: () -> Unit,
    onNavigateToVocabulary: () -> Unit,
    onLogout: () -> Unit
) {
    val points by ScoreManager.points.collectAsState()
    val user by UserSession.user.collectAsState()
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
            Spacer(Modifier.weight(1f))    // przenosi następne elementy na dół

            Text(
                text = "Logged in as: ${user ?: "-"}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            Button(
                onClick = onLogout,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.6f)
            ) { Text("Logout") }


        }
    }
}
// This file defines the second screen of the app, which contains a button to navigate back to ScreenOne.