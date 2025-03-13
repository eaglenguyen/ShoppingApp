package com.example.shoppingapp.presentation.authscreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.auth.AuthResult

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onClickPrevious: () -> Unit,
    onClickToHome: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.authResult.collect { result ->
            when(result) {
                is AuthResult.Authorized -> {
                    onClickToHome()
                }
                is AuthResult.Unauthorized -> {
                    Unit
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Sign In",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton( onClickPrevious ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },

        content = { paddingValues -> paddingValues

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(95.dp))



                Spacer(modifier = Modifier.height(150.dp))


                // Email Field
                AuthTextField(
                    value = state.signInEmail,
                    onValueChange = { viewModel.onEvent(AuthUiEvent.SignInEmailChanged(it))},
                    label = "Email"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                AuthTextField(
                    value = state.signInPassword,
                    onValueChange = { viewModel.onEvent(AuthUiEvent.SignInPasswordChanged(it))},
                    label = "Email"
                )


                Spacer(modifier = Modifier.height(24.dp))

                // Sign In Button
                Button(
                    onClick = { viewModel.onEvent(AuthUiEvent.SignIn) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = CircleShape
                ) {
                    Text(text = "SIGN IN", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    )
}

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        maxLines = 1,
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}