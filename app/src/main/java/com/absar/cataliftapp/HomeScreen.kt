package com.absar.cataliftapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    var posts by remember { mutableStateOf(samplePosts) }
    var searchQuery by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                SearchAndAddBar(
                    onSearch = { query ->
                        searchQuery = query
                        posts = if (query.isBlank()) {
                            samplePosts
                        } else {
                            samplePosts.filter {
                                it.title.contains(query, ignoreCase = true) ||
                                        it.summary.contains(query, ignoreCase = true)
                            }
                        }
                    },
                    onAddClick = { showAddDialog = true }
                )

                Spacer(Modifier.height(16.dp))

                PostFeed(posts = posts, snackbarHostState = snackbarHostState)

                if (showAddDialog) {
                    AddPostDialog(
                        onDismiss = { showAddDialog = false },
                        onAdd = { title, summary, details ->
                            val newId = (posts.maxOfOrNull { it.id } ?: 0) + 1
                            posts = listOf(
                                Post(
                                    id = newId,
                                    title = title,
                                    summary = summary,
                                    details = details,
                                    imageRes = R.drawable.placeholder2,
                                    author = "Absar"
                                )
                            ) + posts
                        }
                    )
                }
            }
        }
    )
}
