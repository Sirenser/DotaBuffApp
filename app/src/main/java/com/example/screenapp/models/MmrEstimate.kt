package com.example.screenapp.models

import com.google.gson.annotations.SerializedName

data class MmrEstimate(
    @SerializedName("estimate")
    val estimate: Int
)