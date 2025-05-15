package com.absar.cataliftapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home to Icons.Default.Home,
        Screen.Explore to Icons.Default.Search,
        Screen.Courses to Icons.Default.Menu
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { (screen, icon) ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) },
                icon = { Icon(imageVector = icon, contentDescription = null) },
                label = { Text(screen.route.capitalize()) }
            )
        }
    }
}
