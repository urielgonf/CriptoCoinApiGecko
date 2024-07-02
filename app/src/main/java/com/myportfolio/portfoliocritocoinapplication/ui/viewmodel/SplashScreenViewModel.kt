package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel


import androidx.lifecycle.ViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.Screens
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.UiConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(

):ViewModel(){


    // Function to get the UI configuration based on the current route
    fun getUiConfig(route: String?): UiConfig {
        return when (route) {
            Screens.Startup.route -> UiConfig(
                showMenuButton= false,
                showTopBar = false,
                showBottomBar = false,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = false,
            )
            Screens.LoginScreen.route-> UiConfig(
                showMenuButton= false,
                showTopBar = true,
                showBottomBar = false,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = false,
            )
            Screens.SingUp.route-> UiConfig(
                showMenuButton= false,
                showTopBar = true,
                showBottomBar = false,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = false,
            )
            Screens.HomeScreen.route -> UiConfig(
                showMenuButton= true,
                showTopBar = true,
                showBottomBar = true,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = true,

                )

            Screens.CoinListScreen.route -> UiConfig(
                showMenuButton= true,
                showTopBar = true,
                showBottomBar = true,
                showBackButton = false,
                showSearchButton = true,
                showLogOutButton = false,
            )
            Screens.FavoritesScreen.route -> UiConfig(
                showMenuButton= true,
                showTopBar = true,
                showBottomBar = true,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = false,
            )
            Screens.FavoritesScreenRoom.route -> UiConfig(
                showMenuButton= true,
                showTopBar = true,
                showBottomBar = true,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = false,
            )
            Screens.NewsScreen.route -> UiConfig(
                showMenuButton= true,
                showTopBar = true,
                showBottomBar = true,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = false,
            )
            Screens.DetailCoinScreen.route -> UiConfig(
                showMenuButton= true,
                showTopBar = true,
                showBottomBar = true,
                showBackButton = true,
                showSearchButton = false,
                showLogOutButton = false,
            )


            Screens.SplashScreen.route -> UiConfig(
                showMenuButton= false,
                showTopBar = false,
                showBottomBar = false,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = false,
            )
            else -> UiConfig(
                showMenuButton = true,
                showTopBar = true,
                showBottomBar = true,
                showBackButton = false,
                showSearchButton = false,
                showLogOutButton = false,

                )
        }
    }
}