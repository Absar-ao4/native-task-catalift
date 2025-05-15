package com.absar.cataliftapp

import androidx.compose.foundation.background
        import androidx.compose.foundation.layout.*
        import androidx.compose.foundation.lazy.LazyColumn
        import androidx.compose.foundation.lazy.items
        import androidx.compose.foundation.shape.RoundedCornerShape
        import androidx.compose.material.icons.Icons
        import androidx.compose.material.icons.filled.ThumbUp
        import androidx.compose.material.icons.filled.Archive
        import androidx.compose.material.icons.outlined.ChatBubbleOutline
        import androidx.compose.material3.*
        import androidx.compose.runtime.*
        import androidx.compose.ui.Alignment
        import androidx.compose.ui.Modifier
        import androidx.compose.ui.graphics.Color
        import androidx.compose.ui.text.font.FontWeight
        import androidx.compose.ui.unit.dp
        import androidx.compose.ui.unit.sp
        import androidx.lifecycle.ViewModel
        import androidx.lifecycle.viewmodel.compose.viewModel

data class Notification(
            val id: Int,
            val icon: @Composable () -> Unit,
            val title: String,
            val source: String,
            val time: String,
            var isRead: Boolean,
            val content: String
        )

class NotificationViewModel : ViewModel() {
    private val _notifications = mutableStateListOf(
        Notification(
                    id = 1,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = "Like",
                            tint = Color(0xFF245cb5),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    title = "New Feature Update",
                    source = "System",
                    time = "5 minutes ago",
                    isRead = false,
                    content = "We've released a new dashboard feature..."
                ),
                Notification(
                    id = 2,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.ChatBubbleOutline,
                            contentDescription = "Comment",
                            tint = Color(0xFF245cb5),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    title = "Task Assigned",
                    source = "Jira",
                    time = "30 minutes ago",
                    isRead = false,
                    content = "New task in Jira dashboard"
                ),
                Notification(
                    id = 3,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Archive,
                            contentDescription = "Archive",
                            tint = Color(0xFF245cb5),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    title = "Weekly Report",
                    source = "Confluence",
                    time = "2 hours ago",
                    isRead = true,
                    content = "Q3 performance report published"
                )
            )

            val notifications: List<Notification> get() = _notifications
            val unreadCount get() = _notifications.count { !it.isRead }

            fun markAsRead(id: Int) {
                val index = _notifications.indexOfFirst { it.id == id }
                if (index != -1) {
                    _notifications[index] = _notifications[index].copy(isRead = true)
                }
            }

            fun markAllRead() {
                _notifications.replaceAll { it.copy(isRead = true) }
            }
        }

@Composable
fun NotificationPanel(viewModel: NotificationViewModel = viewModel()) {
            val notifications by remember { derivedStateOf { viewModel.notifications } }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF181A20))
                    .padding(24.dp)
            ) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Notifications",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = viewModel::markAllRead,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFffffff))
                    ) {
                        Text("Mark all read (${viewModel.unreadCount})")
                    }
                }

                Spacer(Modifier.height(24.dp))

                LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    items(notifications) { notification ->
                        NotificationCard(
                            notification = notification,
                            onMarkRead = { viewModel.markAsRead(notification.id) }
                        )
                    }
                }
            }
}

@Composable
fun NotificationCard(notification: Notification, onMarkRead: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF20232A)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) { notification.icon()
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = notification.title,
                        color = if (notification.isRead) Color.Gray else Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "${notification.source} • ${notification.time}",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
                if (!notification.isRead) {
                    Badge(containerColor = Color(0xFF20E0D0)) {
                        Text("NEW", color = Color.Black, fontSize = 10.sp)
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            Text(notification.content, color = Color.White, fontSize = 15.sp)

            Spacer(Modifier.height(16.dp))

            Row {
                OutlinedButton(
                    onClick = onMarkRead,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text("Mark Read")
                }
            }
        }
    }
}

 @Composable
fun NotificationScreen() {
    val viewModel: NotificationViewModel = viewModel()
     NotificationPanel(viewModel = viewModel)
}
