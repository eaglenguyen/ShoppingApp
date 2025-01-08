package com.example.shoppingapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.shoppingapp.ui.theme.ibmFontFamily

@Composable
fun ProductItem(
    title: String,
    price: String,
    image: String,
    modifier: Modifier = Modifier

) {
    Card(
        modifier = modifier
            .width(160.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = modifier.padding(bottom = 5.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Image
            Box(
                modifier.width(180.dp)
                    .height(180.dp)

            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                )
                IconButton(
                    onClick = {  },
                    modifier = modifier.align(Alignment.TopEnd)
                ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Gray
                )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = ibmFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                letterSpacing = .8.sp
            )

            Text(
                text = price,
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,


                )


        }
    }
}
