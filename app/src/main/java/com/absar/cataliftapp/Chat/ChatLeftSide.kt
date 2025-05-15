package com.absar.cataliftapp.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListSidebar(
    selectedContact: String,
    onContactSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(12.dp)) {
        Text("Chats", color = Color.White, style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(12.dp))

        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search", color = Color.White, fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White) },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
        )


        Spacer(Modifier.height(16.dp))

        // Contact list
        ContactItem("Bob The Builder", "You: Hello from Absar", isSelected = (selectedContact == "Bob The Builder"))
        {
            onContactSelected("Bob The Builder")
        }
        ContactItem("Harry Potter", "You: Hello from Absar", isSelected = (selectedContact == "Harry Potter"))
        {
            onContactSelected("Harry Potter")
        }
        ContactItem("WalterWhite", "You: Hello from Absar", isSelected = (selectedContact == "WalterWhite"))
        {
            onContactSelected("WalterWhite")
        }
    }
}


@Composable
fun ContactItem(name: String, lastMsg: String, isSelected: Boolean = false, onClick: () -> Unit) {
    val bgColor = if (isSelected) Color(0xFF245cb5) else Color.Transparent
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(12.dp)
            .clickable { onClick() }
    ) {
        Text(name, color = Color.White, fontWeight = FontWeight.Bold)
        Text(lastMsg, color = Color.Gray, fontSize = 12.sp)
    }
}

