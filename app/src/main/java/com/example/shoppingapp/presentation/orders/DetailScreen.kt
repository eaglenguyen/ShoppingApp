package com.example.shoppingapp.presentation.orders

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppingapp.domain.model.cart.Cart
import com.example.shoppingapp.domain.model.orders.OrderDetailEntity
import com.example.shoppingapp.navigation.DetailScreen
import io.github.composegears.valkyrie.BaselineRemoveCircle24

@Composable
fun DetailScreen(
    item: OrderDetailEntity,
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = item.image,
            contentDescription = item.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(100.dp).border(1.dp, Color.Gray, RoundedCornerShape(8.dp)).padding(4.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title, fontWeight = FontWeight.Medium, fontSize = 15.sp)
            Text(text = "$${item.price}", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            Text(text = "In Stock", fontWeight = FontWeight.Normal, fontSize = 15.sp, color = Color(0xFF006400) ) // dark green
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Gray, shape = CircleShape) // Outlined oval shape
                    .padding(1.dp) // Padding inside the border
            ) {

                }
            }
        }
    }
