package com.example.screenapp.repositories

import com.example.screenapp.util.ApiState
import kotlinx.coroutines.flow.Flow

interface HeroResponseRepository {

    suspend fun getHeroes(): Flow<ApiState>

}