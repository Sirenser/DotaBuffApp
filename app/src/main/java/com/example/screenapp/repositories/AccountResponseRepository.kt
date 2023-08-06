package com.example.screenapp.repositories

import com.example.screenapp.models.AccountBaseInfoResponse
import com.example.screenapp.util.ApiState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface AccountResponseRepository {

    suspend fun fetchAccountById(accountId: String): Flow<ApiState>

    suspend fun fetchAccountInfoList(accountInfo: String): Flow<ApiState>

}