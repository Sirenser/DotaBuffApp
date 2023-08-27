package com.example.screenapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AccountBaseInfoResponse(
    @SerializedName("competitive_rank")
    val competitiveRank: Int,
    @SerializedName("leaderboard_rank")
    val leaderboardRank: Int,
    @SerializedName("mmr_estimate")
    val mmrEstimate: MmrEstimate,
    @SerializedName("profile")
    val profile: Profile?,
    @SerializedName("rank_tier")
    val rankTier: Int,
    @SerializedName("solo_competitive_rank")
    val soloCompetitiveRank: Int
) : Serializable