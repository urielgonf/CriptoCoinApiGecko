package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.CreateUserUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.GetCurrentUserUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.IsEmailUsedUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.IsUserNameUsedUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val isEmailUsedUseCase: IsEmailUsedUseCase,
    private val isUserNameUsedUseCase: IsUserNameUsedUseCase
) : ViewModel() {

    var userCreated by mutableStateOf(false)
        private set

    fun createUser(email: String,
                   password: String,
                   rpassword: String,
                   userName: String,
                   onSuccess: () -> Unit,
                   onError:  () -> Unit,
                   onError1: () -> Unit,
                   onError2: () -> Unit,
                   )
    {
        if (password != rpassword) {
            onError()
            return
        }

        viewModelScope.launch {
            try {
                val isEmailUsed = withContext(Dispatchers.IO) {
                    isEmailUsedUseCase(email)
                }
                if (isEmailUsed) {
                    onError1()
                    return@launch
                }

                val isUserNameUsed = withContext(Dispatchers.IO) {
                    isUserNameUsedUseCase(userName)
                }
                if (isUserNameUsed) {
                    onError2()
                    return@launch
                }

                val result = withContext(Dispatchers.IO) {
                    createUserUseCase(email, password)
                }
                result?.let {
                    withContext(Dispatchers.IO) {
                        saveUserUseCase(it.uid, email, userName)
                    }
                    onSuccess()
                    userCreated = true
                } ?: run {

                }
            } catch (e: Exception) {

            }
        }
    }


}
