package com.myportfolio.portfoliocritocoinapplication.ui.views.splashscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myportfolio.portfoliocritocoinapplication.R
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.Screens
import com.myportfolio.portfoliocritocoinapplication.ui.views.Home.StartupScreen
import com.myportfolio.portfoliocritocoinapplication.util.Constants.Companion.IMAGE_DESCRIPTION
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.CUSTOM_GREEN_BANNER

@Composable
fun SplashScreen(
    navController: NavController
) {
    val visible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { -1000 },
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { -1000 },
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
    ) {

        SplashUI(navController)

    }
}


@Composable
fun SplashUI(navController: NavController) {
    Surface(
        color = Color(CUSTOM_GREEN_BANNER),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.main),
                contentDescription = IMAGE_DESCRIPTION,
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 16.dp)
            )
        }

    }
}


