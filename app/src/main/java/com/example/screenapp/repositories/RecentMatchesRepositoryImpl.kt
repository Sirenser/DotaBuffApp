package com.example.screenapp.repositories

import com.example.screenapp.api.DotaApi
import com.example.screenapp.models.AccountBaseInfoResponse
import com.example.screenapp.models.RecentMatchesResponse
import com.example.screenapp.util.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecentMatchesRepositoryImpl @Inject constructor(private val api: DotaApi) :
    RecentMatchesRepository {


    override suspend fun fetchRecentMatches(accountId: String): Flow<ApiState> {
        val response = api.getRecentMatches(accountId = accountId)

        return if (response.isSuccessful) {

            val data = response.body() as List<RecentMatchesResponse>
            flow {
             //   println("Data flowed : ${data[0]}")
                emit(ApiState.Success(data))
            }
        } else {
            flow {
                emit(ApiState.Failure(response.errorBody().toString()))
            }
        }
    }

}