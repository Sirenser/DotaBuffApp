package com.example.screenapp.models

data class RecentMatchesResponse(
    val assists: Int,
    val average_rank: Int,
    val cluster: Int,
    val deaths: Int,
    val duration: Int,
    val game_mode: Int,
    val gold_per_min: Int,
    val hero_damage: Int,
    val hero_healing: Int,
    val hero_id: Int,
    val is_roaming: Any,
    val kills: Int,
    val lane: Any,
    val lane_role: Any,
    val last_hits: Int,
    val leaver_status: Int,
    val lobby_type: Int,
    val match_id: Long,
    val party_size: Int,
    val player_slot: Int,
    val radiant_win: Boolean,
    val skill: Any,
    val start_time: Int,
    val tower_damage: Int,
    val version: Any,
    val xp_per_min: Int
)