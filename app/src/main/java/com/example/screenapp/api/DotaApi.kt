package com.example.screenapp.api

import com.example.screenapp.models.AccountBaseInfoResponse
import com.example.screenapp.models.AccountSearchResponse
import com.example.screenapp.models.HeroResponse
import com.example.screenapp.models.RecentMatchesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DotaApi {

    @GET("/api/players/{account_id}")
    suspend fun getUser(@Path("account_id") accountId: String): Response<AccountBaseInfoResponse>

    @GET("api/players/{account_id}/recentMatches")
    suspend fun getRecentMatches(@Path("account_id") accountId: String): Response<List<RecentMatchesResponse>>

    @GET("api/search")
    suspend fun getProfilesById(@Query("q") accountInfo: String): Response<List<AccountSearchResponse>>

    @GET("api/heroStats")
    suspend fun getHeroes(): Response<List<HeroResponse>>

}