package com.absar.cataliftapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Mentor(
    val id: Int,
    val name: String,
    val role: String,
    val bio: String,
    val avatarUrl: String? = null
)

val sampleMentors = listOf(
    Mentor(
        id = 1,
        name = "Priya Sharma",
        role = "AI Researcher",
        bio = "Passionate about machine learning and data science. Loves teaching and mentoring the next generation."
    ),
    Mentor(
        id = 2,
        name = "Rahul Verma",
        role = "Android Developer",
        bio = "Building beautiful apps with Jetpack Compose. Kotlin enthusiast and open source contributor."
    ),
    Mentor(
        id = 3,
        name = "Aisha Khan",
        role = "Product Manager",
        bio = "Bridging the gap between tech and business. Here to help you grow your product mindset."
    )
)

@Composable
fun MentorCard(mentor: Mentor, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF245CB5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Mentor Avatar",
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(mentor.name, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(mentor.role, color = Color(0xFF20E0D0), fontSize = 14.sp)
                Spacer(Modifier.height(4.dp))
                Text(mentor.bio, color = Color.DarkGray, fontSize = 13.sp, maxLines = 2)
            }
        }
    }
}

@Composable
fun ExploreMentorsScreen(mentors: List<Mentor> = sampleMentors) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            "Explore Mentors",
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(mentors) { mentor ->
                MentorCard(mentor = mentor, onClick = {
                    // TODO: mentor section can be expanded but will do later
                })
            }
        }
    }
}
