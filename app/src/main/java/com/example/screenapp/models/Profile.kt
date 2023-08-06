package com.example.screenapp.models

import java.io.Serializable

data class Profile(
    val account_id: Int,
    val avatar: String,
    val avatarfull: String,
    val avatarmedium: String,
    val cheese: Int,
    val is_contributor: Boolean,
    val is_subscriber: Boolean,
    val last_login: String,
    val loccountrycode: String,
    val name: String,
    val personaname: String,
    val plus: Boolean,
    val profileurl: String,
    val steamid: String
) : Serializable