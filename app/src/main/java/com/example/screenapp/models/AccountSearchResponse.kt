package com.example.screenapp.models

data class AccountSearchResponse(
    val account_id: Int,
    val avatarfull: String,
    val last_match_time: String,
    val personaname: String,
    val similarity: Double
)