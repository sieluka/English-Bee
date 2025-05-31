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
import com.example.englishbee.viewmodel.LoginVMFactory
import com.example.englishbee.viewmodel.LoginViewModel
import androidx.compose.ui.res.stringResource
@Composable
fun LoginPage(
    onLoggedIn: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val ctx = LocalContext.current
    val vm: LoginViewModel = viewModel(factory = LoginVMFactory(ctx))
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
            onClick = { vm.login(login, password, onLoggedIn) },
            modifier = Modifier.fillMaxWidth()
        ) { Text(stringResource(R.string.login)) }

        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.register),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onRegisterClick() }
        )

        if (error != null) {
            Spacer(Modifier.height(12.dp))
            Text(error!!, color = MaterialTheme.colorScheme.error)
        }
    }
}
