package com.example.englishbee.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishbee.R
import com.example.englishbee.viewmodel.RegisterVMFactory
import com.example.englishbee.viewmodel.RegisterViewModel
import androidx.compose.ui.res.stringResource
@Composable
fun RegisterPage(
    onRegistered: () -> Unit,
    onBackToLogin: () -> Unit
) {
    val ctx = LocalContext.current
    val vm: RegisterViewModel = viewModel(factory = RegisterVMFactory(ctx))
    val error by vm.error.collectAsState()

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(stringResource(R.string.login)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { vm.register(login, password, onRegistered) },
            modifier = Modifier.fillMaxWidth()
        ) { Text(stringResource(R.string.register)) }

        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.login),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onBackToLogin() }
        )

        if (error != null) {
            Spacer(Modifier.height(12.dp))
            Text(error!!, color = MaterialTheme.colorScheme.error)
        }
    }
}
