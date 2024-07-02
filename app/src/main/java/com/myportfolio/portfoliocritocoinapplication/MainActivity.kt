package com.myportfolio.portfoliocritocoinapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.NavManager

import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.CoinsListViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.theme.PortfolioCritoCoinApplicationTheme
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.CoinsByIdViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.FavouritesViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.HomeViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.LoginViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.NewsViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.SingUpViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.SplashScreenViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.StartUpViewModel
import dagger.hilt.android.AndroidEntryPoint




/// ./keytool -list -v -keystore C:/Users/Uri/Portfolio/Cripto/PortfolioCriptoCoinpath.jks -alias CriptocoinApp
///injeccion de dependencias
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

     private val coinsListViewModel : CoinsListViewModel by viewModels()
     private val coinsByIdViewModel : CoinsByIdViewModel by viewModels()
     private val newsViewModel : NewsViewModel by viewModels()
     private val loginViewModel : LoginViewModel by viewModels()
     private val signUpViewModel: SingUpViewModel by viewModels()
     private val splashScreenViewModel : SplashScreenViewModel by viewModels()
     private val favouritesViewModel: FavouritesViewModel by viewModels()
     private val homeViewModel: HomeViewModel by viewModels()
     private val startUpViewModel:StartUpViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)///-> notifications
    override fun onCreate(savedInstanceState: Bundle?) {


        ///////////////////notifications//////////////////////////////////////////////////////
        super.onCreate(savedInstanceState)
        setContent {
            var permission by remember{
                mutableStateOf(
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    )== PackageManager.PERMISSION_GRANTED
                )
            }
            val permissionLauncher =
                rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = {isGranted ->
                        permission = isGranted
                    }
                )
            LaunchedEffect(key1 = true){
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            ////////////////////////////////////////////////////////////////////////////////////////


            PortfolioCritoCoinApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(Unit) {
                        coinsListViewModel.getAllCoins()
                        newsViewModel.getAllNews()
                        favouritesViewModel.fetchFavourites()
                        homeViewModel.fetchUserName()
                    }

                    NavManager(
                           coinsListViewModel,
                           coinsByIdViewModel,
                           newsViewModel,
                           loginViewModel,
                           splashScreenViewModel,
                           favouritesViewModel,
                           homeViewModel,
                           signUpViewModel,
                           startUpViewModel
                       )

                }

            }

        }

    }
}