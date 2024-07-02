package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val loginUseCase: LoginUseCase

): ViewModel() {

    var showAlert by mutableStateOf(false)
    var usercreated  by mutableStateOf(false)


    fun login(email: String, password: String, onSuccess: () -> Unit) {

        viewModelScope.launch {
           val result = withContext(Dispatchers.IO){
               loginUseCase(email,password)
           }
            if(result != null){
                onSuccess()
            }

        }
    }
}