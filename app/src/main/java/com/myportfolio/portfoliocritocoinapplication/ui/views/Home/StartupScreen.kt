package com.myportfolio.portfoliocritocoinapplication.ui.views.Home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.Screens
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.StartUpViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun StartupScreen(navController: NavController, startUpViewModel: StartUpViewModel) {
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        val isUserLogged = startUpViewModel.isUserLogged()
        isLoading.value = false
        if (isUserLogged) {
            navController.navigate(Screens.HomeScreen.route) {
                popUpTo(Screens.Startup.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screens.LoginScreen.route) {
                popUpTo(Screens.Startup.route) { inclusive = true }
            }
        }
    }

    // Puedes agregar una pantalla de carga si lo deseas
    if (isLoading.value) {
        // Mostrar alguna UI de carga, puede ser un ProgressBar o similar
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}