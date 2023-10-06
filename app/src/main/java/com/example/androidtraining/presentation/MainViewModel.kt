package com.example.androidtraining.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtraining.app.Either
import com.example.androidtraining.app.errors.ErrorApp
import com.example.androidtraining.app.left
import com.example.androidtraining.domain.SaveUserUseCase
import com.example.androidtraining.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val saveUserUseCase: SaveUserUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser(user: User): Either<ErrorApp, User> {

        return saveUserUseCase(user).fold(
            { responseError(it) },
            { responseSuccess(it) }
        )
    }

    fun loadUser(){
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
        }
    }

    private fun responseError(errorApp: ErrorApp): Either<ErrorApp, User> = errorApp.left()

    private fun responseSuccess(isOk: User) = saveUserUseCase.invoke(isOk)



    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val user: User? = null
    )

}