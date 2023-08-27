package com.example.screenapp.repositories

import com.example.screenapp.api.DotaApi
import com.example.screenapp.models.HeroResponse
import com.example.screenapp.util.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HeroResponseRepositoryImpl @Inject constructor(val api: DotaApi) : HeroResponseRepository {

    override suspend fun getHeroes(): Flow<ApiState> {
        val response = api.getHeroes()

        return if (response.isSuccessful) {
            val data = response.body() as List<HeroResponse>
            flow {
                println("Flowed Success 12 item = ${data[12]}")
                emit(ApiState.Success(data))
            }
        } else {
            flow {
                emit(ApiState.Failure(response.errorBody().toString()))
            }
        }
    }
}