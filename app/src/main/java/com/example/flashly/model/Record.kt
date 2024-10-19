package com.example.flashly.model

data class Record(
    val authtoken: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val phoneNo: String,
    val profileImg: String
)