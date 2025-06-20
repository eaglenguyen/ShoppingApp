package com.example.shoppingapp.presentation.cart

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
import io.github.composegears.valkyrie.BaselineRemoveCircle24

@Composable
fun CartItem(
    item: Cart,
    onDelete: () -> Unit,
    removeQuantity: () -> Unit,
    addQuantity: () -> Unit
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { removeQuantity() },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = if (item.quantity <= 1) Icons.Default.Delete else BaselineRemoveCircle24,
                            contentDescription = "Decrease quantity"
                        )
                    }

                    Text(text = item.quantity.toString(), fontSize = 14.sp, modifier = Modifier.padding(horizontal = 8.dp))

                    IconButton(
                        onClick = { addQuantity() },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Increase quantity")
                    }
                }
            }
            val buttonModifier = Modifier
                .size(width = 80.dp, height = 45.dp) // Adjust size
                .padding(2.dp)

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(
                    onClick = { onDelete() },
                    modifier = buttonModifier,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.DarkGray),
                    border = BorderStroke(1.dp, Color.DarkGray)

                ) { Text("Delete", fontSize = 13.sp) }
                TextButton(
                    onClick = { /* TODO: Save for later */ },
                    modifier = buttonModifier,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.DarkGray),
                    border = BorderStroke(1.dp, Color.DarkGray))
                { Text("Save") }
                TextButton(
                    onClick = { /* TODO: Share */ },
                    modifier = buttonModifier,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.DarkGray),
                    border = BorderStroke(1.dp, Color.DarkGray))
                { Text("Share") }
            }
        }
    }
}