package com.example.shoppingapp.presentation.authscreen

import android.annotation.SuppressLint
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onClickPrevious: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val state = viewModel.state

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

        content = { it

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(95.dp))



                Spacer(modifier = Modifier.height(150.dp))


                // Email Field
                OutlinedTextField(
                    value = state.signInEmail,
                    onValueChange = { viewModel.onEvent(AuthUiEvent.SignInEmailChanged(it)) },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                OutlinedTextField(
                    value = state.signInPassword,
                    onValueChange = { viewModel.onEvent(AuthUiEvent.SignInPasswordChanged(it)) },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )



                Spacer(modifier = Modifier.height(24.dp))

                // Sign Up Button
                Button(
                    onClick = { /* Handle Sign Up */ },
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