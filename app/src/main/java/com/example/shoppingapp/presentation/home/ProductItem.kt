package com.example.shoppingapp.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppingapp.ui.theme.funnelDisplay

@Composable
fun ProductItem(
    title: String,
    description: String,
    price: String,
    image: String,
    rating: String,
    modifier: Modifier = Modifier

) {

        Column(
            modifier = modifier.padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Image
            Column (
                modifier.fillMaxSize()
                    .border(
                    width = 4.dp, // Border thickness
                    color = Color.LightGray, // Border color
                    shape = RoundedCornerShape(8.dp) // Border shape
                ).clip(RoundedCornerShape(8.dp)).padding(8.dp)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    color = Color.Black,
                    letterSpacing = 0.8.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = funnelDisplay,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description, // New description text
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = price,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, fontWeight = FontWeight.W500),
                    color = MaterialTheme.colorScheme.primary, // Consistent color usage
                    modifier = Modifier.align(Alignment.Start) // Aligns with the title
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$rating ⭐️",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, fontWeight = FontWeight.W500),
                    color = MaterialTheme.colorScheme.primary, // Consistent color usage
                    modifier = Modifier.align(Alignment.Start) // Aligns with the title
                )
            }



        }

}
