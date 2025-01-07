package com.example.shoppingapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val latoFontFamily = FontFamily(
    Font(R.font.lato_black, FontWeight.Black),
    Font(R.font.lato_blackitalic, FontWeight.Normal),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_bolditalic, FontWeight.SemiBold),
    )

val radioCanadaFontFamily = FontFamily(
    Font(R.font.radiocanada_condensed_semibold, FontWeight.SemiBold)
)

val ibmFontFamily = FontFamily(
    Font(R.font.ibmplexsans_medium, FontWeight.Medium)
)