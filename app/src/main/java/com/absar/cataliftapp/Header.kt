package com.absar.cataliftapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("CATA") }
                    withStyle(SpanStyle(fontWeight = FontWeight.Light)) { append("LIFT") }
                },
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color.White)
            }
            IconButton(onClick = {
                navController.navigate(Screen.Notifications.route)
            }) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
            }
            IconButton(onClick = {
                navController.navigate(Screen.Chat.route)
            }) {
                Icon(Icons.Default.Chat, contentDescription = null, tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF001E6C))
    )
}
