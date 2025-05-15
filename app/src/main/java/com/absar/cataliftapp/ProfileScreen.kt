package com.absar.cataliftapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton

@Composable
fun MyProfileScreen() {
    var user by remember {
        mutableStateOf(
            UserProfile(
                name = "Absar Ali",
                role = "Product Manager",
                email = "absar@example.com",
                bio = "Passionate about building products that matter. 🚀\nLoves Kotlin, Compose, and great coffee."
            )
        )
    }

    var showEditDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181A20))
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(Color(0xFF245CB5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Avatar",
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(user.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Text(user.role, color = Color(0xFF20E0D0), fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Email, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(user.email, color = Color.Gray, fontSize = 14.sp)
        }

        Spacer(Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF23262F)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                user.bio,
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier.padding(20.dp)
            )
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = { showEditDialog = true },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF245CB5))
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text("Edit Profile", color = Color.White)
        }
    }

    if (showEditDialog) {
        EditProfileDialog(
            user = user,
            onDismiss = { showEditDialog = false },
            onSave = { updatedUser ->
                user = updatedUser
                showEditDialog = false
            }
        )
    }
}

@Composable
fun EditProfileDialog(
    user: UserProfile,
    onDismiss: () -> Unit,
    onSave: (UserProfile) -> Unit
) {
    var name by remember { mutableStateOf(user.name) }
    var role by remember { mutableStateOf(user.role) }
    var email by remember { mutableStateOf(user.email) }
    var bio by remember { mutableStateOf(user.bio) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onSave(UserProfile(name, role, email, bio))
            }) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        title = { Text("Edit Profile") },
        text = {
            Column {
                OutlinedTextField(
                    value = name, onValueChange = { name = it },
                    label = { Text("Name") }, singleLine = true
                )
                OutlinedTextField(
                    value = role, onValueChange = { role = it },
                    label = { Text("Role") }, singleLine = true
                )
                OutlinedTextField(
                    value = email, onValueChange = { email = it },
                    label = { Text("Email") }, singleLine = true
                )
                OutlinedTextField(
                    value = bio, onValueChange = { bio = it },
                    label = { Text("Bio") }, maxLines = 4
                )
            }
        }
    )
}
