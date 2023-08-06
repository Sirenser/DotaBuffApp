package com.example.screenapp.repositories


import com.example.screenapp.util.ApiState
import kotlinx.coroutines.flow.Flow


interface RecentMatchesRepository {

   suspend fun fetchRecentMatches(accountId: String): Flow<ApiState>

}