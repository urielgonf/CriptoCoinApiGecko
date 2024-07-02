package com.myportfolio.portfoliocritocoinapplication.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.CoinsByIdViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.CoinsListViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.FavouritesViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.HomeViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.LoginViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.NewsViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.SingUpViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.SplashScreenViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.StartUpViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.views.Coin.CoinDetailScreen
import com.myportfolio.portfoliocritocoinapplication.ui.views.Coin.CoinListScreen
import com.myportfolio.portfoliocritocoinapplication.ui.views.Coin.FavouritesScreen
import com.myportfolio.portfoliocritocoinapplication.ui.views.Coin.FavouritesScreenRoom
import com.myportfolio.portfoliocritocoinapplication.ui.views.Home.HomeScreen
import com.myportfolio.portfoliocritocoinapplication.ui.views.Home.StartupScreen
import com.myportfolio.portfoliocritocoinapplication.ui.views.MainBottomBar
import com.myportfolio.portfoliocritocoinapplication.ui.views.MainTopBar
import com.myportfolio.portfoliocritocoinapplication.ui.views.Login.TabsView
import com.myportfolio.portfoliocritocoinapplication.ui.views.MenuLateral
import com.myportfolio.portfoliocritocoinapplication.ui.views.News.NewsScreen
import kotlinx.coroutines.launch
@SuppressLint("SuspiciousIndentation")
@Composable
fun NavManager(
    coinsListViewModel: CoinsListViewModel,
    coinsByIdViewModel: CoinsByIdViewModel,
    newsViewModel: NewsViewModel,
    loginViewModel: LoginViewModel,
    splashScreenViewModel: SplashScreenViewModel,
    favouritesViewModel: FavouritesViewModel,
    homeViewModel: HomeViewModel,
    singUpViewModel: SingUpViewModel,
    startUpViewModel: StartUpViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var isSearchFieldVisible by remember { mutableStateOf(false) }
    var previousRoute by remember { mutableStateOf<String?>(null) }
    val uiConfig = splashScreenViewModel.getUiConfig(currentRoute)
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()


        if (currentRoute != Screens.Startup.route) {

            MenuLateral(drawerState = drawerState,

                onClickLogOutButton = {
                    navController.navigate(Screens.LoginScreen.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                    homeViewModel.signOut()
                }

            ) {

            Scaffold(
                topBar = {
                    if (uiConfig.showTopBar) {
                        MainTopBar(
                            showMenuButton = uiConfig.showMenuButton,
                            showBackButton = uiConfig.showBackButton,
                            showSearchButton = uiConfig.showSearchButton,
                            onClickBackButton = {
                                navController.popBackStack()
                            },
                            onClickMenuhButton = {
                                coroutineScope.launch { drawerState.open() }
                            },
                            onClickSearchButton = { isSearchFieldVisible = !isSearchFieldVisible }
                        )
                    }
                },
                bottomBar = {
                    if (uiConfig.showBottomBar) {
                        MainBottomBar(navController)
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screens.Startup.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screens.Startup.route, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                        previousRoute = currentRoute
                        StartupScreen(navController, startUpViewModel)
                    }
                    composable(Screens.LoginScreen.route, enterTransition = { fadeIn(animationSpec = tween(600)) }, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                        previousRoute = currentRoute
                        TabsView(navController, loginViewModel, singUpViewModel)
                    }
                    composable(Screens.SingUp.route, enterTransition = { fadeIn(animationSpec = tween(600)) }, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                        previousRoute = currentRoute
                        TabsView(navController, loginViewModel, singUpViewModel)
                    }
                    composable(Screens.HomeScreen.route, enterTransition = { fadeIn(animationSpec = tween(600)) }, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                        previousRoute = currentRoute
                        HomeScreen(homeViewModel)
                    }
                    composable(Screens.CoinListScreen.route, enterTransition = { fadeIn(animationSpec = tween(600)) }, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                        previousRoute = currentRoute
                        CoinListScreen(coinsListViewModel, navController, isSearchFieldVisible)
                    }
                    composable(Screens.FavoritesScreen.route, enterTransition = { fadeIn(animationSpec = tween(600)) }, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                        previousRoute = currentRoute
                        FavouritesScreen(navController, favouritesViewModel)
                    }
                    composable(Screens.FavoritesScreenRoom.route, enterTransition = { fadeIn(animationSpec = tween(600)) }, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                        previousRoute = currentRoute
                        FavouritesScreenRoom(navController, favouritesViewModel)
                    }
                    composable(Screens.NewsScreen.route, enterTransition = { fadeIn(animationSpec = tween(600)) }, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                        previousRoute = currentRoute
                        NewsScreen(newsViewModel)
                    }
                    composable(
                        Screens.DetailCoinScreen.route,
                        arguments = listOf(navArgument("id") { type = NavType.StringType }),
                        enterTransition = { fadeIn(animationSpec = tween(600)) },
                        exitTransition = { fadeOut(animationSpec = tween(100)) }
                    ) { backStackEntry ->
                        previousRoute = currentRoute
                        CoinDetailScreen(
                            coinsByIdViewModel,
                            backStackEntry.arguments?.getString("id") ?: ""
                        )
                    }
                }
            }
        }
    }
        else {
            NavHost(
                navController = navController,
                startDestination = Screens.Startup.route
            ) {
                composable(Screens.Startup.route, exitTransition = { fadeOut(animationSpec = tween(100)) }) {
                    previousRoute = currentRoute
                    StartupScreen(navController, startUpViewModel)
                }
            }
        }
}
