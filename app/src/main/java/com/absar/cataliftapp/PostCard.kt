package com.absar.cataliftapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.absar.cataliftapp.CircleAvatar
import com.absar.cataliftapp.R
import kotlinx.coroutines.launch


data class Post(
    val id: Int,
    val author: String,
    val title: String,
    val summary: String,
    val details: String,
    val imageRes: Int
)

val samplePosts = listOf(
    Post(
        id = 1,
        author = "Akhilesh Yadav",
        title = "The Briggs–Rauscher Reaction: A Mesmerizing Chemical Dance 🌈",
        summary = "This captivating process uses hydrogen peroxide...",
        details = "This captivating process uses hydrogen peroxide, iodate, malonic acid, manganese sulfate, and starch to create oscillating color changes. The reaction is a classic example of a chemical oscillator and is often used in chemistry demonstrations to illustrate non-equilibrium thermodynamics and reaction kinetics. The solution cycles between colorless, amber, and deep blue in a mesmerizing dance, captivating audiences and illustrating the beauty of chemistry in action.\n💡 Follow @Science for more",
        imageRes = R.drawable.placeholder2
    ),
    Post(
        id = 2,
        author = "Priya Sharma",
        title = "AI in Healthcare: Transforming the Future",
        summary = "AI is revolutionizing diagnostics and patient care...",
        details = "AI is revolutionizing diagnostics and patient care by enabling faster, more accurate analysis and personalized medicine. From predictive analytics to robotic surgery, the possibilities are endless.\n💡 Follow @HealthTech for more",
        imageRes = R.drawable.placeholder2
    ),
)

@Composable
fun PostFeed(posts: List<Post>, snackbarHostState: SnackbarHostState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFffffff)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(posts, key = { it.id }) { post ->
            PostCardWithState(post, snackbarHostState)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCardWithState(post: Post, snackbarHostState: SnackbarHostState) {
    var expanded by rememberSaveable(post.id) { mutableStateOf(false) }
    var showComments by remember { mutableStateOf(false) }
    var commentInput by remember { mutableStateOf("") }
    var comments by rememberSaveable(post.id) {
        mutableStateOf(listOf("Great post!", "Wow, I didn’t know that!", "Awesome 🤯"))
    }
    var isStarred by rememberSaveable(post.id) { mutableStateOf(false) }
    var starCount by rememberSaveable(post.id) { mutableStateOf(1546) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 500.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircleAvatar()
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(post.author, fontWeight = FontWeight.Bold)
                        Text("1d • Edited", fontSize = 12.sp)
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Friend request sent!")
                        }
                    }) {
                        Icon(Icons.Default.PersonAdd, contentDescription = "Add Friend")
                    }
                }

                Spacer(Modifier.height(8.dp))

                Text(post.title, fontWeight = FontWeight.SemiBold)
                Text(post.details)

                Spacer(Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = post.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            isStarred = !isStarred
                            starCount += if (isStarred) 1 else -1
                        }) {
                            Icon(
                                imageVector = if (isStarred) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Star",
                                tint = if (isStarred) Color.Yellow else Color.Gray
                            )
                        }
                        Spacer(Modifier.width(4.dp))
                        Text("$starCount Stars")
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { showComments = true }) {
                            Icon(Icons.Filled.Comment, contentDescription = "Comments")
                        }
                        Spacer(Modifier.width(4.dp))
                        Text("${comments.size} comments")
                    }
                }

                Spacer(Modifier.height(12.dp))
                Text("Comments", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(Modifier.height(8.dp))
                comments.forEach {
                    Text("• $it", modifier = Modifier.padding(vertical = 2.dp))
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = commentInput,
                        onValueChange = { commentInput = it },
                        label = { Text("Write a comment...") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (commentInput.isNotBlank()) {
                                comments = comments + commentInput
                                commentInput = ""
                            }
                        }
                    ) {
                        Text("Post")
                    }
                }
            }
        } else {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircleAvatar()
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(post.author, fontWeight = FontWeight.Bold)
                        Text("1d • Edited", fontSize = 12.sp)
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Friend request sent!")
                        }
                    }) {
                        Icon(Icons.Default.PersonAdd, contentDescription = "Add Friend")
                    }
                }

                Spacer(Modifier.height(8.dp))

                Text(post.title, fontWeight = FontWeight.SemiBold)
                Text(post.summary)

                Spacer(Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = post.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            isStarred = !isStarred
                            starCount += if (isStarred) 1 else -1
                        }) {
                            Icon(
                                imageVector = if (isStarred) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Star",
                                tint = if (isStarred) Color.Yellow else Color.Gray
                            )
                        }
                        Spacer(Modifier.width(4.dp))
                        Text("$starCount Stars")
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { showComments = true }) {
                            Icon(Icons.Filled.Comment, contentDescription = "Comments")
                        }
                        Spacer(Modifier.width(4.dp))
                        Text("${comments.size} comments")
                    }
                }
            }
        }

        if (showComments && !expanded) {
            ModalBottomSheet(
                onDismissRequest = { showComments = false },
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Comments", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(Modifier.height(8.dp))
                    comments.forEach {
                        Text("• $it", modifier = Modifier.padding(vertical = 2.dp))
                    }
                    Spacer(Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = commentInput,
                            onValueChange = { commentInput = it },
                            label = { Text("Write a comment...") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (commentInput.isNotBlank()) {
                                    comments = comments + commentInput
                                    commentInput = ""
                                }
                            }
                        ) {
                            Text("Post")
                        }
                    }
                }
            }
        }
    }
}
