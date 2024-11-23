package com.example.ayushassignment.ui.theme

import androidx.compose.ui.graphics.Color


// Colors for dark theme
val dark_btn = Color(0xFF222427)
val dark_bg = Color(android.graphics.Color.parseColor("#0d1117"))
val dark_body = Color(android.graphics.Color.parseColor("#151b23f2"))
val dark_extra = Color(android.graphics.Color.parseColor("#656c76"))
val dark_title = Color(android.graphics.Color.parseColor("#0969DA"))

// Colors for dark theme
val light_btn = Color(android.graphics.Color.parseColor("#E9F0F4"))
val light_bg = Color(android.graphics.Color.parseColor("#f6f8fa"))
val light_body = Color(android.graphics.Color.parseColor("#ffffff"))
val light_extra = Color(android.graphics.Color.parseColor("#59636e"))
val light_title = Color(android.graphics.Color.parseColor("#0969DA"))

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color,
    val extra: Color,
    val onSecondary : Color
)  {
    object Night: ThemeColors(
        background = dark_bg,
        surface = dark_btn,
        primary = dark_body,
        text = Color.White,
        extra = dark_extra,
        onSecondary = dark_title
    )
    object Day: ThemeColors(
        background = light_bg,
        surface = light_btn,
        primary = light_body,
        text = Color.Black,
        extra = light_extra,
        onSecondary = light_title
    )
}