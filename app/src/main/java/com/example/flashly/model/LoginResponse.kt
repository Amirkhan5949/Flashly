package com.example.flashly.model

data class LoginResponse(
    val message: String,
    val record: Record,
    val status: Boolean
)