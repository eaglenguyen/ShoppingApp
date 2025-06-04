package com.example.shoppingapp.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shoppingapp.R
import com.example.shoppingapp.navigation.HomeScreen
import com.example.shoppingapp.navigation.SignUpScreen
import com.example.shoppingapp.ui.theme.sourGummy

@Composable
fun SplashScreen(navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_shopping))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 1, // Play animation once
        speed = 1.5f, // Adjust speed if needed
        isPlaying = true
    )

    var showDialog by remember { mutableStateOf(false) }


    // Navigate to home screen when animation finishes
    LaunchedEffect(progress) {
        if (progress == 1f) {
            showDialog = true
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(250.dp)
            )
            CustomDialog(
                    onDismiss = {
                        showDialog = false
                        navController.navigate(SignUpScreen)
                    },
                    onGuest = {
                        showDialog = false
                        navController.navigate(HomeScreen)
                    },
                        visible = showDialog
                )




            }
        }

    }





@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    onGuest: () -> Unit,
    visible: Boolean
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(animationSpec = tween(durationMillis = 700)) + fadeIn(),
        exit = slideOutVertically() + fadeOut()
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6F6FF))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Thank you for installing the ShoppingApp!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontFamily = sourGummy
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Choose to make an account or continue as a guest to explore the app!",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontFamily = sourGummy,
                fontWeight = FontWeight.Light,

                )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    modifier = Modifier.border(2.dp, Color.Gray, RoundedCornerShape(18.dp)),
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE6F6FF), contentColor = Color.DarkGray)) {
                    Text("Login/Signup",
                        fontFamily = sourGummy,
                        fontWeight = FontWeight.Light
                        )


                }
                Button(
                    onClick = { onGuest() },
                    modifier = Modifier.background(Color(0xFF004965), shape = RoundedCornerShape(18.dp)),

                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004965))) {
                    Text("Continue as Guest",
                        fontFamily = sourGummy,
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    )
                }
            }

        }
    }
}
}

