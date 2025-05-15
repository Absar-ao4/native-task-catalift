    package com.absar.cataliftapp.Chat

    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.width
    import androidx.compose.material3.Divider
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.unit.dp

    @Composable
    fun ChatScreen() {
        var selectedContact by remember { mutableStateOf("Bob The Builder") }

        Row(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            // Left sidebar (narrower)
            ContactListSidebar(
                selectedContact = selectedContact,
                onContactSelected = { selectedContact = it },
                modifier = Modifier
                    .width(160.dp)  // Reduced from 240.dp
                    .fillMaxHeight()
                    .background(Color(0xFF121212))
            )

            // Divider
            Divider(color = Color.Gray, modifier = Modifier.width(1.dp).fillMaxHeight())

            // Chat content (wider area)
            ChatContent(
                contactName = selectedContact,
                modifier = Modifier.weight(1f)  // Takes remaining space
            )
        }
    }
