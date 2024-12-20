package com.example.shoppingapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.auth.AuthResult
import com.example.shoppingapp.presentation.authscreen.AuthUiEvent
import com.example.shoppingapp.presentation.authscreen.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onClickToSignUpScreen: () -> Unit
) {

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.authResult.collect { result ->
            when(result) {
                is AuthResult.Authorized -> {
                    Unit
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "You have signed out",
                        Toast.LENGTH_LONG
                    ).show()
                    onClickToSignUpScreen()
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
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.bodyMedium
        )
        Button( onClick = { viewModel.showDialogState() }
                ,modifier = Modifier
                .fillMaxWidth()
            .height(56.dp)
            ,
            shape = CircleShape
        )  {
            if(state.showDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.showDialogState() },
                    title = {
                        Text(text = "Sign Out")
                    },
                    text = {
                        Text("Are you sure you want ot sign out?")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.onEvent(AuthUiEvent.SignOut)
                            viewModel.showDialogState()
                        }) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {viewModel.showDialogState()}) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
        Text(text = "Sign Out", style = MaterialTheme.typography.bodyMedium)

    }
}