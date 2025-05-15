package com.absar.cataliftapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.absar.cataliftapp.Chat.ChatScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Courses : Screen("courses")
    object Chat: Screen("chat_screen")
    object Notifications : Screen("notifications_screen")
    object Profile : Screen("profile")
}


@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Explore.route) { ExploreMentorsScreen() }
        composable(Screen.Courses.route) { CoursesScreen() }
        composable(Screen.Chat.route) { ChatScreen() }
        composable(Screen.Notifications.route) { NotificationScreen() }
        composable(Screen.Profile.route) { MyProfileScreen() }

    }
}
