package com.example.screenapp.repositories


import com.example.screenapp.util.ApiState
import kotlinx.coroutines.flow.Flow


interface AccountResponseRepository {

    suspend fun fetchAccountById(accountId: String): Flow<ApiState>

    suspend fun fetchAccountInfoList(accountInfo: String): Flow<ApiState>

}