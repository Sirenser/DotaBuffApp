package com.example.screenapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.screenapp.models.HeroResponse
import com.example.screenapp.repositories.HeroResponseRepository
import com.example.screenapp.repositories.HeroResponseRepositoryImpl
import com.example.screenapp.util.ApiState
import com.example.screenapp.util.HeroesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel(
    private val heroesRepo: HeroResponseRepository,
    private val heroesData: HeroesData
) : ViewModel() {

    private val _heroesListStateFlow: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)

    val heroesListStateFlow: StateFlow<ApiState> = _heroesListStateFlow

    fun loadHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            heroesRepo.getHeroes()
                .onStart {
                    _heroesListStateFlow.value = ApiState.Loading
                }.catch {
                    _heroesListStateFlow.value = ApiState.Failure("Error")
                }.collectLatest {
                    val state = it as ApiState.Success<*>
                    val list = state.data as List<HeroResponse>
                    _heroesListStateFlow.value = it
                    heroesData.updateList(list)
                }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class SplashViewModelFactory @Inject constructor(
    private val heroesRepo: HeroResponseRepositoryImpl,
    private val heroesData: HeroesData
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(heroesRepo, heroesData) as T
    }

}