package com.example.englishbee.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishbee.R

@Composable
fun ScreenOne(
    onNavigateToScreen2: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(16.dp)
        )
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = null,
        )
        Text(
            text = stringResource(R.string.welcome1),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(16.dp)
        )
        Button(
            onClick = onNavigateToScreen2,
            modifier = Modifier
                .padding(top=35.dp)
                .width(220.dp)
                .height(80.dp)
        ) {
            Text(
                text = stringResource(R.string.start),
                fontSize = 20.sp
            )
        }
    }
}
