package com.absar.cataliftapp

data class UserProfile(
    val name: String,
    val role: String,
    val email: String,
    val bio: String,
    val avatarUrl: String? = null
)
