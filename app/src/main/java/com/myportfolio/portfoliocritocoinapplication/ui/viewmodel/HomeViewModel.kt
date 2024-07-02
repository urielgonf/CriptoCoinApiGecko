package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.GetUserNameUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.IsUserLoggedUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val isUserLoggedUseCase: IsUserLoggedUseCase,
    private val logOutUseCase: LogOutUseCase,
) : ViewModel() {

    private val _userName = MutableLiveData<String?>()
    val userName: MutableLiveData<String?> get() = _userName

    private val _isUserLogged = MutableLiveData<Boolean>()
    val isUserLogged = MutableLiveData<Boolean>()


    fun fetchUserName() {
        viewModelScope.launch {
            try {
                val name = withContext(Dispatchers.IO){
                    getUserNameUseCase()
                }
                _userName.value = name
                Log.d("username","${name}")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching user name", e)
            }
        }
    }
    fun isUserLogged() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO){
                    isUserLoggedUseCase()
                }
                _isUserLogged.value=result
            } catch (e: Exception) {

            }
        }
    }


    fun signOut() {
        viewModelScope.launch {
            try{
                withContext(Dispatchers.IO){
                    logOutUseCase()
                }
            }catch (e:Exception){

            }
        }

    }

}



