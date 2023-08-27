package com.example.screenapp.models

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @SerializedName("hero_id")
    val heroId: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("localized_name")
    val localizedName: String,
    @SerializedName("name")
    val name: String,
)