package com.absar.cataliftapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.absar.cataliftapp.Chat.ChatScreen

@Composable
fun CataliftApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopBar(navController = navController)
        },
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("chat_screen") {
                ChatScreen()
            }
            composable("notifications_screen") {
                NotificationScreen()
            }
            composable("profile") {
                MyProfileScreen()
            }
            composable("explore") {
                ExploreMentorsScreen()
            }
            composable("courses") {
                CoursesScreen()
            }

        }
    }
}
