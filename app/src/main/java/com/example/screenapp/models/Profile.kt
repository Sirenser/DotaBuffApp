package com.example.screenapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Profile(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("avatarfull")
    val avatarFull: String,
    @SerializedName("avatarmedium")
    val avatarMedium: String,
    @SerializedName("cheese")
    val cheese: Int,
    @SerializedName("is_contributor")
    val isContributor: Boolean,
    @SerializedName("is_subscriber")
    val isSubscriber: Boolean,
    @SerializedName("last_login")
    val lastLogin: String,
    @SerializedName("loccountrycode")
    val locCountryCode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("personaname")
    val personaName: String,
    @SerializedName("plus")
    val plus: Boolean,
    @SerializedName("profileurl")
    val profileURL: String,
    @SerializedName("steamid")
    val steamId: String
) : Serializable