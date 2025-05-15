package com.absar.cataliftapp.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.absar.cataliftapp.CircleAvatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatContent(contactName: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircleAvatar()
            Spacer(Modifier.width(8.dp))
            Column {
                Text(contactName, color = Color.White, fontWeight = FontWeight.Bold)
                Text("Product Manager", color = Color.Gray, fontSize = 12.sp)
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 8.dp)
        ) {
            LazyColumn(reverseLayout = true) {
                items(5) {
                    when (it % 2) {
                        0 -> OutgoingMessage(
                            "Hello from Absar to $contactName! \nMessage ${5 - it}.",
                            "${it + 1} min ago"
                        )
                        else -> IncomingMessage(
                            "Hi Absar, it's $contactName. \nMessage ${5 - it}.",
                            "${it + 1} min ago"
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E1E1E), RoundedCornerShape(24.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            var message by remember { mutableStateOf("") }

            TextField(
                value = message,
                onValueChange = { message = it },
                placeholder = { Text("Type a message", color = Color.Gray) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    cursorColor = Color.White
                ),
                modifier = Modifier.weight(1f),
                maxLines = 2
            )

            IconButton(onClick = { /* Handle send */ }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = Color(0xFF00ACC1)
                )
            }
        }
    }
}

@Composable
fun OutgoingMessage(text: String, time: String) {
    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .background(Color(0xFF245cb5), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(text, color = Color.White)
        }
        Text(time, color = Color.Gray, fontSize = 10.sp)
    }
}


@Composable
fun IncomingMessage(text: String, time: String) {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .background(Color(0xFF1E1E1E), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(text, color = Color.White)
        }
        Text(time, color = Color.Gray, fontSize = 10.sp)
    }
}
