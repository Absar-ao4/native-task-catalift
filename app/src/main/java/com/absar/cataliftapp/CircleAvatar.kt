package com.absar.cataliftapp

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CircleAvatar() {
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "User Avatar",
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(50))
            .padding(4.dp)
    )
}
