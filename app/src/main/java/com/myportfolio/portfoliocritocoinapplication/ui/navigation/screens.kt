package com.myportfolio.portfoliocritocoinapplication.ui.navigation



sealed class Screens(val route: String){
    data object  SplashScreen:Screens("splash-screen")
    data object  Startup:Screens("start-up")

    data object  LoginScreen: Screens("login-screen")
    data object  SingUp: Screens("signUp-screen")
    data object  HomeScreen: Screens("home-screen")

    data object  CoinListScreen: Screens("coin-list-screen")
    data object  DetailCoinScreen: Screens("detail-coins-creen/{id}"){
        fun createRoute(id:String) = "detail-coins-creen/$id"
    }


    data object  FavoritesScreen: Screens("favourite-screen")
    data object  FavoritesScreenRoom: Screens("favourite-screen-room")
    data object  NewsScreen: Screens("news-screen")
}


