package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.IsUserLoggedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartUpViewModel @Inject constructor(
    private val isUserLoggedUseCase: IsUserLoggedUseCase,
) : ViewModel() {


    fun isUserLogged(): Boolean {
        return isUserLoggedUseCase()
    }


}