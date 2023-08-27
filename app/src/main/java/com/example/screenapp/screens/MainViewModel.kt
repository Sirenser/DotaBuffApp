package com.example.screenapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.screenapp.models.HeroResponse
import com.example.screenapp.repositories.*
import com.example.screenapp.util.ApiState
import com.example.screenapp.util.HeroesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(
    private val accountRepo: AccountResponseRepository,
    private val recentMatchRepo: RecentMatchesRepository,
    private val heroesRepo: HeroResponseRepository,
    private val heroesData: HeroesData
) : ViewModel() {

    private val _accountBaseInfoStateFlow: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)

    val accountBaseInfoStateFlow: StateFlow<ApiState> = _accountBaseInfoStateFlow

    private val _recentMatchesStateFlow: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)

    val recentMatchesStateFlow: StateFlow<ApiState> = _recentMatchesStateFlow

    private val _heroesListStateFlow: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)

    fun getAccountBaseInfo(accountId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepo.fetchAccountById(accountId = accountId.toString())
                .onStart {
                    _accountBaseInfoStateFlow.value = ApiState.Loading
                }.catch {
                    _accountBaseInfoStateFlow.value = ApiState.Failure("Error")
                }.collectLatest {
                    _accountBaseInfoStateFlow.value = it
                }
        }
    }

    fun getRecentMatchesById(accountId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            recentMatchRepo.fetchRecentMatches(accountId = accountId.toString())
                .onStart {
                    _recentMatchesStateFlow.value = ApiState.Loading
                }.catch {
                    _recentMatchesStateFlow.value = ApiState.Failure("Error")
                }.collectLatest {
                    _recentMatchesStateFlow.value = it
                }
        }
    }

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
class MainViewModelFactory @Inject constructor(
    private val accountRepo: AccountResponseRepositoryImpl,
    private val recentMatchRepo: RecentMatchesRepositoryImpl,
    private val heroesRepo: HeroResponseRepositoryImpl,
    private val heroesData: HeroesData
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(accountRepo, recentMatchRepo, heroesRepo, heroesData) as T
    }
}