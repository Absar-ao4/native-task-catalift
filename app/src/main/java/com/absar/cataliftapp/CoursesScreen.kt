package com.absar.cataliftapp

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

data class Course(
    val id: Int,
    val title: String,
    val instructor: String,
    val description: String
)

val sampleCourses = listOf(
    Course(1, "Introduction to Kotlin", "John Doe", "Learn the basics of Kotlin programming language."),
    Course(2, "Jetpack Compose Essentials", "Jane Smith", "Master the fundamentals of Jetpack Compose for Android UI development."),
    Course(3, "Advanced Android Development", "Emily Johnson", "Deep dive into advanced topics of Android development including performance optimization.")
)

@Composable
fun CourseCard(course: Course, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.School,
                    contentDescription = "Course Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(course.title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                    Text(course.instructor, fontSize = 14.sp, color = Color(0xFF20E0D0))
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(course.description, fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun CoursesScreen(courses: List<Course> = sampleCourses) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            "Courses",
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(courses) { course ->
                CourseCard(course = course, onClick = {
                    // TODO: onclick is left which i'll add later in a hurry of time
                })
            }
        }
    }
}
