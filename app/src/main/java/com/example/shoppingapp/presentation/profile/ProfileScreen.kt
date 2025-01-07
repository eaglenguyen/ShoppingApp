package com.example.shoppingapp.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.R
import com.example.shoppingapp.util.Resource

@Composable
fun ProfileScreen (
    scaffFoldPadding: PaddingValues,
    onClickToSignUp: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current


    LaunchedEffect(viewModel, context) {
        viewModel.profileResult.collect { result ->
            when(result) {
                is Resource.Success-> {
                    Unit
                }
                is Resource.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "You have signed out",
                        Toast.LENGTH_LONG
                    ).show()
                    onClickToSignUp()
                }
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is Resource.Loading -> {
                    Unit
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffFoldPadding)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Profile Picture
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder), // Replace with your image resource
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Name
        Text(
            text = "Mark Adam",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Email
        Text(
            text = "Sunny_Koelpin45@hotmail.com",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Options List
        val options = listOf(
            "Profile" to Icons.Default.Person,
            "Setting" to Icons.Default.Settings,
            "Contact" to Icons.Default.Email,
            "Share App" to Icons.Default.Share,
            "Help" to Icons.Default.Info
        )

        options.forEach { (title, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { /* Handle click */ }
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Arrow",
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Sign Out Text
        Text(
            text = "Sign Out",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable { viewModel.showDialogState() },
            style = MaterialTheme.typography.bodyLarge
        )
        if (state.showDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.showDialogState() },
                title = {
                    Text(text = "Sign Out")
                },
                text = {
                    Text("Are you sure you want to sign out?")
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.onEvent(ProfileScreenUiEvent.SignOut)
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
}
