package com.example.screenapp.models

import com.google.gson.annotations.SerializedName

data class AccountSearchResponse(
    @SerializedName("account_id")
    val account_id: Int,
    @SerializedName("avatarfull")
    val avatarFull: String,
    @SerializedName("last_match_time")
    val lastMatchTime: String,
    @SerializedName("personaname")
    val personaName: String,
    @SerializedName("similarity")
    val similarity: Double
)