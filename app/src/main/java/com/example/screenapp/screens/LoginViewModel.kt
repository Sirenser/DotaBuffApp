package com.example.screenapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.screenapp.models.AccountBaseInfoResponse
import com.example.screenapp.models.AccountSearchResponse
import com.example.screenapp.repositories.AccountResponseRepository
import com.example.screenapp.repositories.AccountResponseRepositoryImpl
import com.example.screenapp.util.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(private val repo: AccountResponseRepository) : ViewModel() {

    private val _accountInfoStateFlow: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)

    val accountInfoStateFlow: StateFlow<ApiState> = _accountInfoStateFlow


    fun getAccountSearchResponse(accountInfo: String) {

        viewModelScope.launch(Dispatchers.IO) {

            repo.fetchAccountInfoList(accountInfo = accountInfo)
                .onStart {
                    _accountInfoStateFlow.value = ApiState.Loading
                }.catch {
                    _accountInfoStateFlow.value = ApiState.Failure("Error")
                }.collectLatest {
                    _accountInfoStateFlow.value = it
                }

        }

    }


}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(
    private val repo: AccountResponseRepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repo) as T
    }

}
