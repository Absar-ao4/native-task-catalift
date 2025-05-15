package com.absar.cataliftapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

@Composable
fun SearchAndAddBar(
    onSearch: (String) -> Unit,
    onAddClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchBar(
            query = query,
            onQueryChange = {
                query = it
                onSearch(it)
            },
            modifier = Modifier.weight(1f)
        )

        AddPostButton(onClick = onAddClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search", color = Color.Gray) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        modifier = modifier
            .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(18.dp)),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF0F0F0),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun AddPostButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .background(
                Color(0xFF001E6C),
                shape = RoundedCornerShape(12.dp)
            )
            .size(48.dp)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "Add Post",
            tint = Color.White
        )
    }
}
