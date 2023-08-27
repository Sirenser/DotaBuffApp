package com.example.screenapp.repositories

import com.example.screenapp.api.DotaApi
import com.example.screenapp.models.AccountBaseInfoResponse
import com.example.screenapp.models.AccountSearchResponse
import com.example.screenapp.util.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class AccountResponseRepositoryImpl @Inject constructor(private val api: DotaApi) :
    AccountResponseRepository {

    override suspend fun fetchAccountById(accountId: String): Flow<ApiState> {
        val response = api.getUser(accountId = accountId)

        return if (response.isSuccessful) {
            val data = response.body() as AccountBaseInfoResponse
            flow {
                emit(ApiState.Success(data))
            }
        } else {
            flow {
                emit(ApiState.Failure(response.errorBody().toString()))
            }
        }
    }

    override suspend fun fetchAccountInfoList(accountInfo: String): Flow<ApiState> {
        val response = api.getProfilesById(accountInfo = accountInfo)

        return if (response.isSuccessful) {
            val data = response.body() as List<AccountSearchResponse>
            flow {
                emit(ApiState.Success(data))
            }
        } else {
            flow {
                emit(ApiState.Failure(response.errorBody().toString()))
            }
        }
    }
}