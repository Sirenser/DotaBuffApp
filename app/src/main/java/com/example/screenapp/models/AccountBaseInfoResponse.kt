package com.example.screenapp.models

import java.io.Serializable


data class AccountBaseInfoResponse(
    val competitive_rank: Int,
    val leaderboard_rank: Int,
    val mmr_estimate: MmrEstimate,
    val profile: Profile?,
    val rank_tier: Int,
    val solo_competitive_rank: Int
) : Serializable