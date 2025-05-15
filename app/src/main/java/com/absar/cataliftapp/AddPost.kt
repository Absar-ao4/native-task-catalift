package com.absar.cataliftapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField as OutlinedTextField1

@Composable
fun AddPostDialog(
    onDismiss: () -> Unit,
    onAdd: (title: String, summary: String, details: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var summary by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (title.isNotBlank() && summary.isNotBlank() && details.isNotBlank()) {
                        onAdd(title, summary, details)
                        onDismiss()
                    }
                }
            ) { Text("Add") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        title = { Text("Add New Post") },
        text = {
            Column{
                OutlinedTextField1(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    singleLine = true
                )
                OutlinedTextField1(
                    value = summary,
                    onValueChange = { summary = it },
                    label = { Text("Summary") },
                    singleLine = true
                )
                OutlinedTextField1(
                    value = details,
                    onValueChange = { details = it },
                    label = { Text("Details") },
                    maxLines = 4
                )
            }
        }
    )
}
